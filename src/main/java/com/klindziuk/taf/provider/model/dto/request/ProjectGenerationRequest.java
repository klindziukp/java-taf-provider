/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.request;

import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProjectGenerationRequest {

  private String name;
  private String description;
  private String groupId;
  private String artifactId;
  private String javaVersion;
  private String buildTool;
  private String testEngine;
  private Set<String> webDriverModuleNames;
  private Set<String> apiModuleNames;
  private Set<String> reportingModuleNames;
}
