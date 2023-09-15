/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config.generation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** 'Gradle' generation configuration */
@Data
@Accessors(chain = true)
@Configuration
public class GradleConfig {

  @Value("${com.klindziuk.taf.provider.gradle.wrapper.version:}")
  private String wrapperVersion;
}
