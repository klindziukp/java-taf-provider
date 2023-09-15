/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.project;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TafModuleGroup {

  private String id;
  private String name;
  private String description;
  private List<TafModule> tafModules;
}
