/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool.java;

import com.klindziuk.taf.provider.constant.FreemarkerConstant;
import com.klindziuk.taf.provider.layer.service.FreemarkerService;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolGenerationService;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import freemarker.template.Template;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradleGenerationService implements BuildToolGenerationService {

  private final FreemarkerService freemarkerService;

  @Autowired
  public GradleGenerationService(FreemarkerService freemarkerService) {
    this.freemarkerService = freemarkerService;
  }

  @Override
  public void copyWrapperFolder(Path path) {
    // do nothing club
  }

  @Override
  public void createProjectFile(GenerationData generationData) {
    final Map<String, Object> params = new HashMap<>();
    final Template template = freemarkerService.createTemplate("/gradle/build-gradle.ftl");

    freemarkerService.createFile(
        template, generationData.getProjectPath(), FreemarkerConstant.BUILD_GRADLE, params);
  }
}
