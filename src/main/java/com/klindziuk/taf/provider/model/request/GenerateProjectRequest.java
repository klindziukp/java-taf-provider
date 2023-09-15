/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.request;

import com.klindziuk.taf.provider.model.ProjectPlatform;
import com.klindziuk.taf.provider.model.project.TafModuleGroup;
import java.util.Map;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GenerateProjectRequest {

  private ProjectPlatform projectPlatform;
  private Map<String, String> projectConfigurations;
  private Set<TafModuleGroup> tafModuleGroups;
}
