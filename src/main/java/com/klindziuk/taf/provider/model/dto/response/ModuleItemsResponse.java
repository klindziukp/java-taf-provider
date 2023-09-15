/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.response;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ModuleItemsResponse {

  private List<ModuleItemResponse> webDriverModules;
  private List<ModuleItemResponse> apiModules;
  private List<ModuleItemResponse> reportingModules;
  private List<ModuleItemResponse> testEngineModules;
}
