/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModuleItemResponse {

  private String uuid;
  private String groupId;
  private String artifactId;
  private String displayName;
  private String description;
  private String moduleGroup;
  private String version;
}
