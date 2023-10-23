/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;

/** Represents Wizard property keys */
public final class TafProviderKey {

  public static final String EXAMPLE = "example";
  public static final String PACKAGE = "package";
  public static final String SUREFIRE_PROVIDER = "surefireProvider";
  public static final String GENERATION_DATA = "generationData";

  private TafProviderKey() {
    throw new PreventInstantiationException();
  }
}
