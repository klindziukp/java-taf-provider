/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.controller;

import com.klindziuk.taf.provider.layer.service.GenerationService;
import com.klindziuk.taf.provider.model.request.GenerateProjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ProjectGenerationController {

  private final GenerationService generationService;

  @Autowired
  public ProjectGenerationController(GenerationService generationService) {
    this.generationService = generationService;
  }

  @PostMapping(path = "api/v1/provider/zip", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
  public ResponseEntity<byte[]> zip1(@RequestBody GenerateProjectRequest generateProjectRequest) {
    log.info("Project generation request: '{}'", generateProjectRequest);
    // TODO(pklindziuk): Handle project name and validate GenerateProjectRequest
    final String disposition =
        "attachment; filename=%s.zip"
            .formatted(generateProjectRequest.getProjectConfigurations().get("projectName"));

    return ResponseEntity.ok()
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .header(HttpHeaders.CONTENT_DISPOSITION, disposition)
        .body(generationService.generateProject(generateProjectRequest));
  }
}
