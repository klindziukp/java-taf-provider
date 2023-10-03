/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.example.java;

import com.klindziuk.taf.provider.constant.FreemarkerConstant;
import com.klindziuk.taf.provider.constant.PathConstant;
import com.klindziuk.taf.provider.constant.TafProviderKey;
import com.klindziuk.taf.provider.exception.TafProviderException;
import com.klindziuk.taf.provider.layer.repository.ModuleRepository;
import com.klindziuk.taf.provider.layer.service.FreemarkerService;
import com.klindziuk.taf.provider.layer.service.example.ExampleGenerationService;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.response.ExamplesResponse;
import com.klindziuk.taf.provider.model.dto.response.MdResponse;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import com.klindziuk.taf.provider.util.NewProjectPathUtil;
import com.klindziuk.taf.provider.util.VerificationUtil;
import com.klindziuk.taf.provider.util.WizardFileUtil;
import com.klindziuk.taf.provider.util.ZipUtil;
import freemarker.template.Template;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/** Service to perform work with modules examples */
@Service
@Slf4j
public class JavaExampleGenerationService implements ExampleGenerationService {

  private final FreemarkerService freemarkerService;
  private final ModuleRepository moduleItemRepository;

  @Autowired
  public JavaExampleGenerationService(
      FreemarkerService freemarkerService, ModuleRepository moduleItemRepository) {
    this.freemarkerService = freemarkerService;
    this.moduleItemRepository = moduleItemRepository;
  }

  // TODO(pklindziuk) extract to appropriate service with checks and verifications

  /**
   * Unzips archive and saves files to local system
   *
   * @param multipartFile multipart file
   * @return list of saved directories
   */
  public ExamplesResponse saveArchiveToLocalSystem(MultipartFile multipartFile) {
    VerificationUtil.verifyIfZipFile(multipartFile);
    final List<String> folders =
        ZipUtil.unzipFolder(multipartFile, PathConstant.WIZARD_FREEMARKER_PATH);
    return new ExamplesResponse().setFolders(folders);
  }

  /**
   * Unzips archive and saves files to local system
   *
   * @param multipartFile multipart file
   * @return list of saved directories
   */
  // TODO(pklindziuk) extract to appropriate service with checks and verifications
  public MdResponse saveModuleMdToLocalSystem(MultipartFile multipartFile) {
    VerificationUtil.verifyIfMdFile(multipartFile);

    // TODO(pklindziuk) implement real saving
    if (getPresentModule(multipartFile).isEmpty()) {
      return new MdResponse().setMessage("Oh noes!");
    }
    return new MdResponse().setMessage("OK");
  }

  private Set<ModuleItem> getPresentModule(MultipartFile multipartFile) {
    String mdFileName = StringUtils.substringBefore(multipartFile.getOriginalFilename(), ".md");
    return moduleItemRepository.findAll().stream()
        .filter(moduleItem -> moduleItem.getDisplayName().equalsIgnoreCase(mdFileName))
        .collect(Collectors.toSet());
  }

  /**
   * Generates examples classes based on selected modules
   *
   * @param generationData generation data
   */
  public void generateExamples(GenerationData generationData) {
    final Path newProjectSrcMainPath = NewProjectPathUtil.getSrcMainPath(generationData);
    final Path newProjectSrcTestPath = NewProjectPathUtil.getSrcTestPath(generationData);
    final String newProjectPackageName = NewProjectPathUtil.getPackageName(generationData);

    generateExampleClassesFolder(
        newProjectSrcMainPath, newProjectSrcTestPath, newProjectPackageName, generationData);
    generateSrcResourcesFolder(generationData);
    generateTestResourcesTestFolder(generationData);
  }

  /**
   * Generates examples classes into appropriate packages
   *
   * @param mainPath main folder path
   * @param testPath test path
   * @param packageName new project's package name
   * @param generationData generation data
   */
  // TODO(pklindziuk): Fix issues with empty folder generation
  private void generateExampleClassesFolder(
      Path mainPath, Path testPath, String packageName, GenerationData generationData) {
    final Map<String, String> examplePaths = collectExamplePathForModules(generationData);

    examplePaths.forEach(
        (key, value) -> {
          // Create 'src/main/{moduleName}' and 'src/test/{moduleName}' directories
          Path mainPathToWrite =
              createDirectoryAndGetPath(
                  mainPath, NewProjectPathUtil.updateExamplesDirectoryNames(value));
          Path testPathToWrite =
              createDirectoryAndGetPath(
                  testPath, NewProjectPathUtil.updateExamplesDirectoryNames(value));
          if (!value.isEmpty()) {
            // Iterate over `example` directory  for appropriate module and write Java classes
            try {
              List<Path> collect =
                  Files.walk(Paths.get(key))
                      .filter(filePath -> filePath.toString().endsWith(".ftl"))
                      .peek(
                          filePath -> {
                            final String examplePath =
                                StringUtils.substringAfterLast(
                                    filePath.toString(),
                                    PathConstant.WIZARD_FREEMARKER_PATH.toString());
                            final Template template = freemarkerService.createTemplate(examplePath);
                            final Path pathToGenerate =
                                examplePath.contains(PathConstant.NEW_PROJECT_SRC_MAIN)
                                    ? mainPathToWrite
                                    : testPathToWrite;
                            final String ftlFileName =
                                StringUtils.substringAfterLast(examplePath, File.separator);
                            final String ftlClassName =
                                StringUtils.substringBefore(
                                    ftlFileName, FreemarkerConstant.FTL_EXTENSION);
                            final String packagePath =
                                NewProjectPathUtil.generateClassFilePackage(packageName, value);
                            final Map<String, Object> params =
                                Map.of(TafProviderKey.PACKAGE, packagePath);
                            final String className =
                                File.separator + ftlClassName + FreemarkerConstant.JAVA_EXTENSION;
                            freemarkerService.createFile(
                                template, pathToGenerate, className, params);
                          })
                      .toList();
            } catch (IOException ioEx) {
              log.error("Unable to create appropriate examples for provided modules.", ioEx);
            }
          }
        });
  }

  private Map<String, String> collectExamplePathForModules(GenerationData generationData) {
    final Map<String, String> result = new HashMap<>();
    for (ModuleItem moduleItem : generationData.getModuleItems()) {
      result.putAll(
          getFileStream(PathConstant.WIZARD_FREEMARKER_EXAMPLE_PATH)
              .filter(ftlFilter())
              .filter(pathFilter(generationData.getModuleDisplayNames()))
              .flatMap(this::getFileStream)
              .filter(ftlFilter())
              .map(path -> StringUtils.substringBeforeLast(path.toString(), File.separator))
              .collect(
                  Collectors.toMap(
                      pathFrom -> pathFrom,
                      pathTo -> StringUtils.substringAfter(pathTo, PathConstant.JAVA),
                      (oldValue, newValue) -> oldValue)));
    }
    return result;
  }

  private void generateSrcResourcesFolder(GenerationData generationData) {
    WizardFileUtil.createDirectories(NewProjectPathUtil.getSrcMainResourcesPath(generationData));
  }

  private void generateTestResourcesTestFolder(GenerationData generationData) {
    WizardFileUtil.createDirectories(NewProjectPathUtil.getSrcTestResourcesPath(generationData));
  }

  private Stream<Path> getFileStream(Path path) {
    try {
      return Files.walk(path);
    } catch (IOException ioEx) {
      throw new TafProviderException(
          "Unable to create Stream for path: - " + PathConstant.WIZARD_FREEMARKER_EXAMPLE_PATH);
    }
  }

  private Path createDirectoryAndGetPath(Path directoryToCreatePath, String exampleFolderName) {
    Path pathToWrite =
        Paths.get(String.join(File.separator), directoryToCreatePath.toString(), exampleFolderName);
    WizardFileUtil.createDirectories(pathToWrite);
    return pathToWrite;
  }

  private Predicate<Path> pathFilter(Set<String> moduleNames) {
    return path -> {
      // Module names size should be at least 2: module + required test engine
      if (moduleNames.size() < 2) {
        return false;
      }
      // Path should end with .ftl extension
      final String filePath = path.toString();
      // We should not create multiple examples for two or more modules
      if (StringUtils.countMatches(filePath, PathConstant.DOUBLE_UNDERSCORE)
          != moduleNames.size() - 1) {
        return false;
      }
      // Path should contain module names from request
      for (String module : moduleNames) {
        if (!filePath.contains(module)) {
          return false;
        }
      }
      return true;
    };
  }

  private Predicate<Path> ftlFilter() {
    return path -> path.toString().endsWith(FreemarkerConstant.FTL_EXTENSION);
  }
}
