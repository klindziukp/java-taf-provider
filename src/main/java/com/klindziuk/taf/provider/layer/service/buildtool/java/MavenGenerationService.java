/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool.java;

import com.klindziuk.taf.provider.constant.FreemarkerConstant;
import com.klindziuk.taf.provider.constant.PathConstant;
import com.klindziuk.taf.provider.constant.TafProviderKey;
import com.klindziuk.taf.provider.layer.service.FreemarkerService;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolGenerationService;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import com.klindziuk.taf.provider.util.WizardFileUtil;
import freemarker.template.Template;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MavenGenerationService implements BuildToolGenerationService {

  private final FreemarkerService freemarkerService;

  @Autowired
  public MavenGenerationService(FreemarkerService freemarkerService) {
    this.freemarkerService = freemarkerService;
  }

  @Override
  public void copyWrapperFolder(Path path) {
    final String mavenWrapperLocation = PathConstant.WIZARD_BUILD_TOOL_MAVEN_PATH;
    WizardFileUtil.copyDirectory(Paths.get(mavenWrapperLocation), path);
  }

  @Override
  public void createProjectFile(GenerationData generationData) {
    final Map<String, Object> params = new HashMap<>();
    params.put(TafProviderKey.GENERATION_DATA, generationData);
    final Template template = freemarkerService.createTemplate("/maven/pom.ftl");
    freemarkerService.createFile(
        template, generationData.getProjectPath(), FreemarkerConstant.POM_XML, params);
  }
}
