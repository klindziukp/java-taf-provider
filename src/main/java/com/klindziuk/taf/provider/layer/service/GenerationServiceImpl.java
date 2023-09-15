/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.config.generation.GenerationConfig;
import com.klindziuk.taf.provider.constant.ConfigurationProperty;
import com.klindziuk.taf.provider.constant.ModuleGroupInfo;
import com.klindziuk.taf.provider.exception.TafProviderSingleModuleException;
import com.klindziuk.taf.provider.layer.repository.ModuleRepository;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolGenerationService;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolGenerationServiceFactory;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolServiceFactory;
import com.klindziuk.taf.provider.layer.service.example.ExampleGenerationService;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.BuildTool;
import com.klindziuk.taf.provider.model.dto.request.ProjectLanguage;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import com.klindziuk.taf.provider.model.project.TafModule;
import com.klindziuk.taf.provider.model.project.TafModuleGroup;
import com.klindziuk.taf.provider.model.request.GenerateProjectRequest;
import com.klindziuk.taf.provider.util.WizardFileUtil;
import com.klindziuk.taf.provider.util.ZipUtil;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GenerationServiceImpl implements GenerationService {

  private final ModuleRepository moduleItemRepository;
  private final ExampleGenerationService exampleGenerationService;
  private final DocsGenerationService docsGenerationService;
  private final BuildToolGenerationServiceFactory buildToolGenerationServiceFactory;
  private final GenerationConfig generationConfig;

  @Autowired
  public GenerationServiceImpl(
      ModuleRepository moduleItemRepository,
      ExampleGenerationService exampleGenerationService,
      DocsGenerationService docsGenerationService,
      BuildToolGenerationServiceFactory buildToolGenerationServiceFactory,
      GenerationConfig generationConfig) {
    this.moduleItemRepository = moduleItemRepository;
    this.exampleGenerationService = exampleGenerationService;
    this.docsGenerationService = docsGenerationService;
    this.buildToolGenerationServiceFactory = buildToolGenerationServiceFactory;
    this.generationConfig = generationConfig;
  }

  /**
   * Generates project
   *
   * @param generateProjectRequest project generation request
   * @return array of bytes
   */
  public byte[] generateProject(GenerateProjectRequest generateProjectRequest) {
    // TODO(pklindziuk): create appropriate util
    ConfigurationProperty.validateConfiguration(generateProjectRequest.getProjectConfigurations());
    final GenerationData generationData = generationData(generateProjectRequest);

    exampleGenerationService.generateExamples(generationData);

    docsGenerationService.generateDocsFolder(generationData);
    docsGenerationService.generateGitItems(generationData);

    final BuildToolServiceFactory buildToolServiceFactory =
        buildToolGenerationServiceFactory.buildToolServiceFactory(ProjectLanguage.JAVA);
    final BuildToolGenerationService buildToolGenerationService =
        buildToolServiceFactory.buildToolGenerationService(BuildTool.MAVEN);

    buildToolGenerationService.createProjectFile(generationData);
    buildToolGenerationService.copyWrapperFolder(generationData.getProjectPath());

    return projectZipBytes(generationData, generationConfig);
  }

  /**
   * Generates project
   *
   * @param generationData generation data
   * @param generationConfig generation config
   * @return array of bytes
   */
  private byte[] projectZipBytes(GenerationData generationData, GenerationConfig generationConfig) {
    final Path path = ZipUtil.zipProject(generationData.getProjectPath());
    byte[] projectBytes = WizardFileUtil.readAllBytes(path);
    if (generationConfig.getCleanup()) {
      WizardFileUtil.deleteDirectory(generationData.getRandomDirectoryPath());
    }
    return projectBytes;
  }

  private GenerationData generationData(GenerateProjectRequest generateProjectRequest) {
    final String randomFolder = RandomStringUtils.randomAlphanumeric(29);
    final Path randomDirectoryPath =
        Paths.get(
            String.join(File.separator, generationConfig.getProjectDirectory(), randomFolder));
    final Path projectPath = generateProjectFolder(randomFolder, generateProjectRequest);
    final Map<String, String> projectConfigurations =
        generateProjectRequest.getProjectConfigurations();
    // TODO(pklindziuk): move to mapper
    final GenerationData generationData =
        new GenerationData()
            .setProjectName(
                projectConfigurations.get(ConfigurationProperty.PROJECT_NAME.getValue()))
            .setDescription(
                projectConfigurations.get(ConfigurationProperty.PROJECT_DESCRIPTION.getValue()))
            .setArtifactId(projectConfigurations.get(ConfigurationProperty.ARTIFACT_ID.getValue()))
            .setGroupId(projectConfigurations.get(ConfigurationProperty.GROUP_ID.getValue()))
            .setJavaVersion(
                projectConfigurations.get(ConfigurationProperty.JAVA_VERSION.getValue()))
            .setBuildTool(projectConfigurations.get(ConfigurationProperty.BUILD_TOOL.getValue()))
            .setRandomDirectoryPath(randomDirectoryPath)
            .setProjectPath(projectPath)
            .setGenerationConfig(generationConfig)
            .setModuleDisplayNames(new HashSet<>())
            .setModuleItems(new HashSet<>());

    updateGenerationDataWithModuleItems(generationData, generateProjectRequest);
    return generationData;
  }

  private Path generateProjectFolder(
      String randomFolder, GenerateProjectRequest generateProjectRequest) {
    final String randomDir =
        String.join(
            File.separator,
            generationConfig.getProjectDirectory(),
            randomFolder,
            generateProjectRequest.getProjectConfigurations().get("projectName"));
    return WizardFileUtil.createDirectories(randomDir);
  }

  private void updateGenerationDataWithModuleItems(
      GenerationData generationData, GenerateProjectRequest generateProjectRequest) {
    final List<ModuleItem> moduleItems = moduleItemRepository.findAll();
    for (TafModuleGroup tafModuleGroup : generateProjectRequest.getTafModuleGroups()) {
      final ModuleGroupInfo moduleGroupInfo =
          ModuleGroupInfo.getByDisplayName(tafModuleGroup.getName());
      if (!moduleGroupInfo.isMultiValued() && tafModuleGroup.getTafModules().size() > 1) {
        // TODO(pklindziuk): ADD verification mechanism to avoid empty modules
        throw new TafProviderSingleModuleException(
            String.format(
                "Single valued module [%s] shouldn't have multiple params",
                tafModuleGroup.getName()));
      }
      for (TafModule tafModule : tafModuleGroup.getTafModules()) {
        fillModuleItems(generationData, tafModule, moduleItems);
      }
    }
  }

  private void fillModuleItems(
      GenerationData generationData, TafModule tafModule, List<ModuleItem> moduleItems) {
    for (ModuleItem moduleItem : moduleItems) {
      if (tafModule.getDisplayName().equalsIgnoreCase(moduleItem.getDisplayName())) {
        generationData.getModuleDisplayNames().add(moduleItem.getDisplayName());
        generationData.getModuleItems().add(moduleItem);
      }
    }
  }
}
