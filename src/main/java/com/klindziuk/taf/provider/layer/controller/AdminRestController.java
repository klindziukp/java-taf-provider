/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.controller;

import com.klindziuk.taf.provider.layer.service.ModuleService;
import com.klindziuk.taf.provider.layer.service.example.java.JavaExampleGenerationService;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.dto.response.ExamplesResponse;
import com.klindziuk.taf.provider.model.dto.response.MdResponse;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class AdminRestController {

  private final ModuleService moduleService;
  private final JavaExampleGenerationService exampleService;

  @Autowired
  public AdminRestController(
      ModuleService moduleService, JavaExampleGenerationService exampleService) {
    this.moduleService = moduleService;
    this.exampleService = exampleService;
  }

  @PostMapping(path = "admin/api/v1/module/save")
  public TafModuleItemResponse saveModule(@RequestBody ModuleItemRequest moduleItemRequest) {
    return moduleService.addModule(moduleItemRequest);
  }

  @PostMapping(path = "admin/api/v1/example/upload")
  public ExamplesResponse saveExample(@RequestParam("file") MultipartFile file) {
    return exampleService.saveArchiveToLocalSystem(file);
  }

  @PostMapping(path = "admin/api/v1/readme/upload")
  public MdResponse saveReadme(@RequestParam("file") MultipartFile file) {
    return exampleService.saveModuleMdToLocalSystem(file);
  }

  @DeleteMapping(path = "admin/api/v1/module/delete/{uuid}")
  public TafModuleItemResponse delete(@PathVariable String uuid) {
    return moduleService.deleteModule(uuid);
  }
}
