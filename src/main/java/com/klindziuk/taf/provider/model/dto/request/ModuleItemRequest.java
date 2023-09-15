/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModuleItemRequest {

  @NotEmpty
  @Size(min = 3, max = 30)
  private String groupId;

  @NotEmpty
  @Size(min = 3, max = 30)
  private String artifactId;

  @NotEmpty
  @Size(min = 3, max = 30)
  private String displayName;

  @NotEmpty
  @Size(min = 3, max = 1000)
  private String description;

  @NotEmpty
  @Size(min = 3, max = 30)
  private String moduleGroup;

  @NotEmpty
  @Size(min = 3, max = 30)
  private String version;
}
