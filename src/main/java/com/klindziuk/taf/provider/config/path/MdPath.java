/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config.path;

import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO(pklindziuk): Add functionality to upload '.md' files during taf-module release
@Getter
@RequiredArgsConstructor
public enum MdPath {
  README("README.md"),

  ALLURE("ALLURE.md"),
  SELENIDE("SELENIDE.md"),
  SERENITY("SERENITY.md"),
  REST_ASSURED("REST_ASSURED.md"),
  REPORT_PORTAL("REPORT_PORTAL.md"),
  JUNIT("JUNIT.md"),
  TESTNG("TESTNG.md");

  private final String path;

  public static Optional<MdPath> getMdPath(String moduleName) {
    for (MdPath propertyPath : MdPath.values()) {
      if (propertyPath.toString().equalsIgnoreCase(moduleName)) {
        return Optional.of(propertyPath);
      }
    }
    return Optional.empty();
  }
}
