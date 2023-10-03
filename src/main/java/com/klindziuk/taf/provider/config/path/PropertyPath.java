/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config.path;

import java.util.List;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

// TODO(pklindziuk): Add functionality to upload '.properties' files during taf-module release
@Getter
@RequiredArgsConstructor
public enum PropertyPath {
  ALLURE(List.of("allure.properties", "configuration.properties")),
  REST_ASSURED(List.of("restassured.properties", "configuration.properties")),
  REPORT_PORTAL(List.of("reportportal.properties", "configuration.properties")),
  SELENIDE(List.of("selenide.properties", "crypto.properties", "configuration.properties")),
  SERENITY(List.of("serenity.properties", "configuration.properties"));

  private final List<String> paths;

  public static Optional<PropertyPath> getPropertyPath(String moduleName) {
    for (PropertyPath propertyPath : PropertyPath.values()) {
      if (propertyPath.toString().equalsIgnoreCase(moduleName)) {
        return Optional.of(propertyPath);
      }
    }
    return Optional.empty();
  }
}
