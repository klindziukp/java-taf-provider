/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.util;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import com.klindziuk.taf.provider.exception.TafProviderZipException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

/** Utility class to perform zip/unzip shenanigans */
@Slf4j
public final class ZipUtil {

  private ZipUtil() {
    throw new PreventInstantiationException();
  }

  /**
   * Creates zip archive for project
   *
   * @param projectPath path for project
   * @return Path to generated archive
   */
  public static Path zipProject(Path projectPath) {
    final Path zipPathTo = Paths.get(projectPath + ".zip");
    return zip(projectPath, zipPathTo);
  }

  /**
   * Creates zip archive
   *
   * @param sourcePath source path
   * @param zipFilePath path to zip
   * @return Path to generated archive
   */
  public static Path zip(final Path sourcePath, final Path zipFilePath) {
    try {
      final Path zipFile = WizardFileUtil.generateFile(zipFilePath);
      try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
          Stream<Path> paths = Files.walk(sourcePath)) {
        paths
            .filter(path -> !Files.isDirectory(path))
            .forEach(
                path -> {
                  ZipEntry zipEntry = new ZipEntry(sourcePath.relativize(path).toString());
                  try {
                    zipOutputStream.putNextEntry(zipEntry);
                    Files.copy(path, zipOutputStream);
                    zipOutputStream.closeEntry();
                  } catch (IOException ioEx) {
                    log.error("Unable to create zip archive in path: '{}", sourcePath);
                    throw new TafProviderZipException(ioEx);
                  }
                });
      }
      log.info("Zip archive created at path: '{}'", zipFile);
    } catch (Exception ex) {
      log.error("Unable to create zip archive in path: '{}", sourcePath);
      throw new TafProviderZipException(ex);
    }
    return zipFilePath;
  }

  /**
   * Unzips folder
   *
   * @param multipartFile multipart file
   * @param targetPath path to unzip folder
   * @return list of paths to unarchived files
   */
  public static List<String> unzipFolder(MultipartFile multipartFile, Path targetPath) {
    final List<String> result = new ArrayList<>();
    try (ZipInputStream zis =
        new ZipInputStream(new ByteArrayInputStream(multipartFile.getBytes()))) {

      ZipEntry zipEntry = zis.getNextEntry();
      VerificationUtil.verifyZipRootFolder(zipEntry);

      while (Objects.nonNull(zipEntry)) {
        boolean isDirectory = zipEntry.getName().endsWith(File.separator);
        final Path newPath = zipSlipProtect(zipEntry, targetPath);

        if (isDirectory) {
          Files.createDirectories(newPath);
          result.add(zipEntry.getName());

        } else {
          if (Objects.nonNull(newPath.getParent())) {
            if (Files.notExists(newPath.getParent())) {
              Files.createDirectories(newPath.getParent());
            }
          }
          Files.copy(zis, newPath, StandardCopyOption.REPLACE_EXISTING);
        }
        zipEntry = zis.getNextEntry();
      }
      zis.closeEntry();
    } catch (Exception ex) {

      throw new TafProviderZipException(ex);
    }
    return result;
  }

  /**
   * Protects from 'zip sleep' attack
   *
   * @param zipEntry zip entry
   * @param targetPath target path
   * @return verified path
   */
  public static Path zipSlipProtect(ZipEntry zipEntry, Path targetPath) {
    // test zip slip vulnerability
    // Path targetDirResolved = targetPath.resolve("../../" + zipEntry.getName());
    final Path targetDirResolved = targetPath.resolve(zipEntry.getName());

    // make sure normalized file still has targetPath as its prefix
    // else throws exception
    Path normalizedPath = targetDirResolved.normalize();
    if (!normalizedPath.startsWith(targetPath)) {
      log.error("Bad zip entry: '{}'", zipEntry.getName());
      throw new TafProviderZipException(String.format("Bad zip entry: '%s'", zipEntry.getName()));
    }

    return normalizedPath;
  }
}
