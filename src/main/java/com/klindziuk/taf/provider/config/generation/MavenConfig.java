/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config.generation;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** 'Maven' generation configuration */
@Data
@Accessors(chain = true)
@Configuration
public class MavenConfig {

  @Value("${com.klindziuk.taf.provider.maven.aspectj-version}")
  private String aspectjVersion;

  @Value("${com.klindziuk.taf.provider.maven.compiler-version}")
  private String compilerVersion;

  @Value("${com.klindziuk.taf.provider.maven.surefire-version}")
  private String surefireVersion;
}
