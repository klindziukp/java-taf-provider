/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool.java;

import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolGenerationService;
import com.klindziuk.taf.provider.layer.service.buildtool.BuildToolServiceFactory;
import com.klindziuk.taf.provider.model.dto.request.BuildTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JavaBuildToolGenerationServiceFactory implements BuildToolServiceFactory {

  private final GradleGenerationService gradleGenerationService;
  private final MavenGenerationService mavenGenerationService;

  @Autowired
  public JavaBuildToolGenerationServiceFactory(
      GradleGenerationService gradleGenerationService,
      MavenGenerationService mavenGenerationService) {
    this.gradleGenerationService = gradleGenerationService;
    this.mavenGenerationService = mavenGenerationService;
  }

  public BuildToolGenerationService buildToolGenerationService(BuildTool buildTool) {
    return switch (buildTool) {
      case MAVEN -> mavenGenerationService;
      case GRADLE -> gradleGenerationService;
    };
  }
}
