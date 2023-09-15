/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.controller;

import com.klindziuk.taf.provider.layer.service.ModuleService;
import com.klindziuk.taf.provider.model.response.TafModulesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProviderModuleRestController {

  private final ModuleService moduleService;

  @Autowired
  public ProviderModuleRestController(ModuleService moduleService) {
    this.moduleService = moduleService;
  }

  @GetMapping(path = "api/v1/provider/modules")
  public TafModulesResponse getTafModules() {
    return moduleService.tafModulesResponse();
  }
}
