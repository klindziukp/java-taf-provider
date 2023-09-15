/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.project;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ProjectConfiguration {

  private String value;
  private boolean required;
}
