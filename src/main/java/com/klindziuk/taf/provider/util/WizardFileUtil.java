/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.util;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import com.klindziuk.taf.provider.exception.TafProviderException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

/** Utility class to work with files */
@Slf4j
public final class WizardFileUtil {

  private WizardFileUtil() {
    throw new PreventInstantiationException();
  }

  /**
   * Generates folder along the specified path
   *
   * @param path path
   * @return Path
   */
  public static Path createDirectories(String path) {
    return createDirectories(Paths.get(path));
  }

  /**
   * Generates folder along the specified path
   *
   * @param path path
   * @return Path
   */
  public static Path createDirectories(Path path) {
    try {
      return Files.createDirectories(path);
    } catch (IOException ioEx) {
      log.error("Unable to generate folder with path : '{}'", path);
      throw new TafProviderException(ioEx);
    }
  }

  /**
   * Generates file along the specified path
   *
   * @param path path
   * @return Path
   */
  public static Path generateFile(String path) {
    return generateFile(Paths.get(path));
  }

  /**
   * Generates file along the specified path
   *
   * @param path path
   * @return Path
   */
  public static Path generateFile(Path path) {
    try {
      return Files.createFile(path);
    } catch (IOException ioEx) {
      log.error("Unable to generate folder with path : '{}'", path);
      throw new TafProviderException(ioEx);
    }
  }

  /**
   * Copies file
   *
   * @param from source
   * @param to destination
   */
  public static void copyFile(Path from, Path to) {
    try {
      Files.copy(from.toAbsolutePath(), to, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ioEx) {
      log.error("Unable to copy file from '{}' to '{}'", from, to);
      throw new TafProviderException(ioEx);
    }
  }

  /**
   * Copies directory
   *
   * @param from source
   * @param to destination
   */
  public static void copyDirectory(Path from, Path to) {
    File sourceDirectory = new File(from.toAbsolutePath().toString());
    File destinationDirectory = new File(to.toString());
    try {
      FileUtils.copyDirectory(sourceDirectory, destinationDirectory);
    } catch (IOException ioEx) {
      log.error("Unable to copy directory from '{}' to '{}'", from, to);
      throw new TafProviderException(ioEx);
    }
  }

  /**
   * Deletes directory
   *
   * @param path path of the directory
   */
  public static void deleteDirectory(Path path) {
    try {
      log.info("Deleting folder with project: '{}'", path);
      FileUtils.deleteDirectory(path.toFile());
    } catch (IOException ioEx) {
      log.error("Unable to delete folder from path: '{}'", path);
      throw new TafProviderException(ioEx);
    }
  }

  /**
   * Reads all bytes from provided path
   *
   * @param path path
   * @return array of bytes
   */
  public static byte[] readAllBytes(Path path) {
    try {
      return Files.readAllBytes(path);
    } catch (IOException ex) {
      log.error("Unable to read bytes from path: '{}'", path);
      throw new TafProviderException(ex);
    }
  }
}
