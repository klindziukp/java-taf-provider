/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config.generation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/** Project generation configuration */
@Data
@Accessors(chain = true)
@Configuration
@Import({GradleConfig.class, MavenConfig.class})
public class GenerationConfig {

  @Value("${com.klindziuk.taf.provider.project.directory}")
  private String projectDirectory;

  @Value("${com.klindziuk.taf.provider.project.cleanup}")
  private Boolean cleanup;

  @Autowired private GradleConfig gradleConfig;
  @Autowired private MavenConfig mavenConfig;
}
