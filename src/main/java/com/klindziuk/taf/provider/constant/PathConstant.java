/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.constant;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import java.nio.file.Path;
import java.nio.file.Paths;

/** Represents 'Path' constant */
public final class PathConstant {

  // Src
  public static final String NEW_PROJECT_MAIN_JAVA = "/main/java";
  public static final String NEW_PROJECT_TEST_JAVA = "/test/java";
  public static final String NEW_PROJECT_SRC_MAIN = "/src/main/java";
  public static final String NEW_PROJECT_SRC_MAIN_RESOURCES = "/src/main/resources";
  public static final String NEW_PROJECT_SRC_TEST = "/src/test/java";
  public static final String NEW_PROJECT_SRC_TEST_RESOURCES = "/src/test/resources";
  public static final String DOT = ".";
  public static final String EMPTY_STRING = "";
  public static final String DOUBLE_UNDERSCORE = "__";
  public static final String JAVA = "java/";

  // Example
  public static final Path WIZARD_FREEMARKER_PATH = Paths.get("template/freemarker");
  public static final Path WIZARD_FREEMARKER_EXAMPLE_PATH =
      Paths.get("template/freemarker/example");
  // Doc
  public static final String WIZARD_DOC_PATH = "template/file/doc/";
  public static final String NEW_PROJECT_DOC_PATH = "/doc";
  // Git
  public static final String GIT_IGNORE = "/.gitignore";
  public static final Path WIZARD_GIT_IGNORE_PATH = Paths.get("template/file/git/.gitignore");
  // Property
  public static final String WIZARD_PROPERTY_PATH = "template/file/property/";
  // Build tool
  public static final String WIZARD_BUILD_TOOL_MAVEN_PATH = "template/tool/maven";
  public static final String WIZARD_BUILD_TOOL_GRADLE_PATH = "template/tool/gradle";

  private PathConstant() {
    throw new PreventInstantiationException();
  }
}
