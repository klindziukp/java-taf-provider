/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.constant.PathConstant;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import com.klindziuk.taf.provider.util.WizardFileUtil;
import java.nio.file.Path;
import java.nio.file.Paths;
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

  // TODO(pklindziuk) refactor to generate docs based on provided TAF modules
  /**
   * Generates 'Doc' items
   *
   * @param generationData generation data
   * @return path to generated docs
   */
  public Path generateDocsFolder(GenerationData generationData) {
    final String projectDocsPath = generationData.getProjectPath().toAbsolutePath() + "/doc";
    Path docDirectories = WizardFileUtil.createDirectories(projectDocsPath);
    WizardFileUtil.copyFile(
        PathConstant.WIZARD_DOC_API_PATH, Paths.get(projectDocsPath + PathConstant.API_MD));
    WizardFileUtil.copyFile(
        PathConstant.WIZARD_DOC_MOBILE_PATH, Paths.get(projectDocsPath + PathConstant.MOBILE_MD));
    WizardFileUtil.copyFile(
        PathConstant.WIZARD_DOC_WEB_PATH, Paths.get(projectDocsPath + PathConstant.WEB_MD));
    return docDirectories;
  }
}
