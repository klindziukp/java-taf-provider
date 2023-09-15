/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BuildTool {
  GRADLE("gradle"),
  MAVEN("maven");

  BuildTool(String value) {
    this.value = value;
  }

  private String value;
}
