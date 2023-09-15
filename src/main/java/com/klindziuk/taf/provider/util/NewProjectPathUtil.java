/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.util;

import com.klindziuk.taf.provider.constant.PathConstant;
import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import com.klindziuk.taf.provider.model.generation.GenerationData;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

/** Utility class for paths construction */
public final class NewProjectPathUtil {

  private NewProjectPathUtil() {
    throw new PreventInstantiationException();
  }

  /**
   * Provides package name for new project
   *
   * @param generationData generation data
   * @return package name
   */
  public static String getPackageName(GenerationData generationData) {
    return generationData.getGroupId() + PathConstant.DOT + generationData.getArtifactId();
  }

  /**
   * Provides 'src/main/java' path for new project
   *
   * @param generationData generation data
   * @return package name
   */
  public static Path getSrcMainPath(GenerationData generationData) {
    return Paths.get(
        generationData.getProjectPath().toAbsolutePath()
            + PathConstant.NEW_PROJECT_SRC_MAIN
            + File.separator
            + getPackageName(generationData));
  }

  /**
   * Provides 'src/main/resources' path for new project
   *
   * @param generationData generation data
   * @return package name
   */
  public static Path getSrcMainResourcesPath(GenerationData generationData) {
    return Paths.get(
        generationData.getProjectPath().toAbsolutePath()
            + PathConstant.NEW_PROJECT_SRC_MAIN_RESOURCES);
  }

  /**
   * Provides 'src/test/java' path for new project
   *
   * @param generationData generation data
   * @return package name
   */
  public static Path getSrcTestPath(GenerationData generationData) {
    return Paths.get(
        generationData.getProjectPath().toAbsolutePath()
            + PathConstant.NEW_PROJECT_SRC_TEST
            + File.separator
            + getPackageName(generationData));
  }

  /**
   * Provides 'src/test/resources' path for new project
   *
   * @param generationData generation data
   * @return package name
   */
  public static Path getSrcTestResourcesPath(GenerationData generationData) {
    return Paths.get(
        generationData.getProjectPath().toAbsolutePath()
            + PathConstant.NEW_PROJECT_SRC_TEST_RESOURCES);
  }

  /**
   * Generates package to insert into .ftl file
   *
   * @param packageName package name
   * @param path path
   * @return package name
   */
  public static String generateClassFilePackage(String packageName, String path) {
    return packageName
        + PathConstant.DOT
        + path.replace(File.separator, PathConstant.DOT).toLowerCase(Locale.ROOT);
  }

  /**
   * Generates package to insert into .ftl file
   *
   * @param path path
   * @return updated directory names
   */
  public static String updateExamplesDirectoryNames(String path) {
    return path.replace(PathConstant.NEW_PROJECT_MAIN_JAVA, PathConstant.EMPTY_STRING)
        .replace(PathConstant.NEW_PROJECT_TEST_JAVA, PathConstant.EMPTY_STRING);
  }
}
