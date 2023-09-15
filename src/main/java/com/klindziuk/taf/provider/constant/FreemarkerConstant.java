/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;

/** Represents 'Freemarker' constant */
public final class FreemarkerConstant {

  public static final String BUILD_GRADLE = "/build.gradle";
  public static final String COMMON_PROPERTIES = "/common.properties";
  public static final String POM_XML = "/pom.xml";
  public static final String FTL_EXTENSION = ".ftl";
  public static final String JAVA_EXTENSION = ".java";
  public static final String CHARSET_ISO_8859_9 = "ISO-8859-9";

  private FreemarkerConstant() {
    throw new PreventInstantiationException();
  }
}
