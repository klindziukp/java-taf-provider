/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import com.klindziuk.taf.provider.exception.TafProviderException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Represents 'Module Group' constant */
@Getter
@RequiredArgsConstructor
public enum ModuleGroupInfo {
  API("taf-api-id", "taf-api", "Module group related to 'API' testing", true),
  ENGINE("taf-engine-id", "taf-engine", "Module group related to 'TEST ENGINE'", false),
  UI("taf-ui-id", "taf-ui", "Module group related to 'UI' testing", false),
  MOBILE("taf-mobile-id", "taf-mobile", "Module group related to 'MOBILE' testing", true),
  MESSAGING(
      "taf-messaging-id", "taf-messaging", "Module group related to 'MESSAGING' testing", true),
  PERFORMANCE(
      "taf-performance-id",
      "taf-performance",
      "Module group related to 'PERFORMANCE' testing",
      true),
  REPORTING("taf-reporting-id", "taf-reporting", "Module group related to 'REPORTING'", false);

  private final String moduleGroupId;
  private final String moduleGroupName;
  private final String moduleGroupDescription;
  private final boolean isMultiValued;

  /**
   * Returns Module info based on display name
   *
   * @param displayName display name
   * @return ModuleGroupInfo
   */
  public static ModuleGroupInfo getByDisplayName(String displayName) {
    for (ModuleGroupInfo moduleGroupInfo : values()) {
      if (moduleGroupInfo.getModuleGroupName().equalsIgnoreCase(displayName)) {
        return moduleGroupInfo;
      }
    }
    throw new TafProviderException("There is no module info for displayName: " + displayName);
  }
}
