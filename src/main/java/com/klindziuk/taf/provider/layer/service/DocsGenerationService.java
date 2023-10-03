/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.config.path.MdPath;
import com.klindziuk.taf.provider.config.path.PropertyPath;
import com.klindziuk.taf.provider.constant.PathConstant;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import com.klindziuk.taf.provider.util.WizardFileUtil;
import java.io.File;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DocsGenerationService {

  /**
   * Generates 'Git' items
   *
   * @param generationData generation data
   */
  public void generateGitItems(GenerationData generationData) {
    WizardFileUtil.copyFile(
        PathConstant.WIZARD_GIT_IGNORE_PATH,
        Paths.get(generationData.getProjectPath() + PathConstant.GIT_IGNORE));
  }

  /**
   * Generates '.properties' files for appropriate modules
   *
   * @param generationData generation data
   */
  public void generatePropertyItems(GenerationData generationData) {
    generatePropertiesForModule(generationData, generationData.getModuleItems());
  }

  /**
   * Generates 'Doc' items
   *
   * @param generationData generation data
   */
  public void generateDocItems(GenerationData generationData) {
    final String projectDocsPath =
        generationData.getProjectPath().toAbsolutePath() + PathConstant.NEW_PROJECT_DOC_PATH;
    WizardFileUtil.createDirectories(projectDocsPath);

    // Generate 'README.md' in the project root directory
    WizardFileUtil.copyFile(
        Paths.get(PathConstant.WIZARD_DOC_PATH + MdPath.README.getPath()),
        Paths.get(generationData.getProjectPath() + File.separator + MdPath.README.getPath()));

    // Generate 'md' file for appropriate module in the 'doc' folder
    generateDocsForModule(generationData, generationData.getModuleItems());
  }

  private void generatePropertiesForModule(
      GenerationData generationData, Set<ModuleItem> moduleItems) {
    for (ModuleItem apiModuleItem : moduleItems) {
      Optional<PropertyPath> propertyPath =
          PropertyPath.getPropertyPath(apiModuleItem.getDisplayName());
      propertyPath.ifPresent(
          path ->
              path.getPaths()
                  .forEach(
                      p ->
                          WizardFileUtil.copyFile(
                              Paths.get(PathConstant.WIZARD_PROPERTY_PATH + p),
                              Paths.get(
                                  generationData.getProjectPath()
                                      + PathConstant.NEW_PROJECT_SRC_MAIN_RESOURCES
                                      + File.separator
                                      + p))));
    }
  }

  private void generateDocsForModule(GenerationData generationData, Set<ModuleItem> moduleItems) {
    for (ModuleItem apiModuleItem : moduleItems) {
      Optional<MdPath> mdPath = MdPath.getMdPath(apiModuleItem.getDisplayName());
      mdPath.ifPresent(
          path ->
              WizardFileUtil.copyFile(
                  Paths.get(PathConstant.WIZARD_DOC_PATH + path.getPath()),
                  Paths.get(
                      generationData.getProjectPath()
                          + PathConstant.NEW_PROJECT_DOC_PATH
                          + File.separator
                          + path.getPath())));
    }
  }
}
