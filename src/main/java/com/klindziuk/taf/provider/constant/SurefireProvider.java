/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/** Represents 'Surefire Provider' */
@Getter
@RequiredArgsConstructor
public enum SurefireProvider {
  SUREFIRE_TEST_NG("TESTNG", "surefire-testng"),
  SUREFIRE_JUNIT("JUNIT", "surefire-junit-platform");

  private final String testEngine;
  private final String surefireProvider;
}
