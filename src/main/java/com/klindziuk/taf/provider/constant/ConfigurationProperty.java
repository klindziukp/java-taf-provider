/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;

/** Represents 'Module Group' constant */
@RequiredArgsConstructor
@Getter
public enum ConfigurationProperty {
  PROJECT_NAME("projectName", true),
  PROJECT_DESCRIPTION("projectDescription", true),
  GROUP_ID("groupId", true),
  ARTIFACT_ID("artifactId", true),
  JAVA_VERSION("javaVersion", true),
  BUILD_TOOL("buildTool", true);

  private final String value;
  private final boolean required;

  public static void validateConfiguration(Map<String, String> properties) {
    for (ConfigurationProperty configurationProperty : ConfigurationProperty.values()) {
      if (!properties.containsKey(configurationProperty.getValue())
          || Strings.isEmpty(properties.get(configurationProperty.getValue()))) {
        final String errorMessage =
            String.format(
                "Property [%s] should not be missed or should not be empty!",
                configurationProperty.getValue());
        throw new RuntimeException(errorMessage);
      }
    }
  }
}
