/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool;

import com.klindziuk.taf.provider.layer.service.buildtool.java.JavaBuildToolGenerationServiceFactory;
import com.klindziuk.taf.provider.model.dto.request.ProjectLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuildToolGenerationServiceFactory {

  private final JavaBuildToolGenerationServiceFactory javaBuildToolGenerationServiceFactory;

  @Autowired
  public BuildToolGenerationServiceFactory(
      JavaBuildToolGenerationServiceFactory javaBuildToolGenerationServiceFactory) {
    this.javaBuildToolGenerationServiceFactory = javaBuildToolGenerationServiceFactory;
  }

  public BuildToolServiceFactory buildToolServiceFactory(ProjectLanguage projectLanguage) {
    return switch (projectLanguage) {
      case JAVA, SCALA, KOTLIN -> javaBuildToolGenerationServiceFactory;
    };
  }
}
