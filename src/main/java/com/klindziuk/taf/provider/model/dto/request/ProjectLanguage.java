/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProjectLanguage {
  JAVA("java"),
  SCALA("scala"),
  KOTLIN("kotlin");

  ProjectLanguage(String value) {
    this.value = value;
  }

  private String value;
}
