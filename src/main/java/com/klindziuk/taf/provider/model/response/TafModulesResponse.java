/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.response;

import com.klindziuk.taf.provider.model.project.ProjectConfiguration;
import com.klindziuk.taf.provider.model.project.TafModuleGroup;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class TafModulesResponse {

  private String platform;
  private List<ProjectConfiguration> projectConfigurations;
  private List<TafModuleGroup> tafModuleGroups;
}
