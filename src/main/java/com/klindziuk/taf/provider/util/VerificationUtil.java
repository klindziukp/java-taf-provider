/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.util;

import com.klindziuk.taf.provider.constant.TafProviderKey;
import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import com.klindziuk.taf.provider.exception.TafProviderZipException;
import java.util.Objects;
import java.util.zip.ZipEntry;
import org.springframework.web.multipart.MultipartFile;

/** Utility class to perform verifications */
public final class VerificationUtil {

  private VerificationUtil() {
    throw new PreventInstantiationException();
  }

  /**
   * Verifies if Multipart file is `zip` file
   *
   * @param multipartFile multipart file
   */
  public static void verifyIfZipFile(MultipartFile multipartFile) {
    final String contentType = multipartFile.getContentType();
    if (!"application/zip".equals(contentType) || !ifAppropriateExtension(multipartFile, ".zip")) {
      throw new TafProviderZipException(
          String.format("Only '.zip' files are allowed, received '%s'.", contentType));
    }
  }

  /**
   * Verifies `zip` root folder
   *
   * @param zipEntry multipart file
   */
  public static void verifyZipRootFolder(ZipEntry zipEntry) {
    if (Objects.isNull(zipEntry) || !zipEntry.getName().startsWith(TafProviderKey.EXAMPLE)) {
      throw new TafProviderZipException(
          String.format("Root folder for zip archive should be '%s'.", TafProviderKey.EXAMPLE));
    }
  }

  /**
   * Verifies if Multipart file is `md` file
   *
   * @param multipartFile multipart file
   */
  public static void verifyIfMdFile(MultipartFile multipartFile) {
    final String contentType = multipartFile.getContentType();
    if (!"text/markdown".equals(contentType) || !ifAppropriateExtension(multipartFile, ".md")) {
      throw new TafProviderZipException(
          String.format("Only '.md' files are allowed, received '%s'.", contentType));
    }
  }

  private static boolean ifAppropriateExtension(MultipartFile multipartFile, String extension) {
    final String originalFileName = multipartFile.getOriginalFilename();
    return Objects.nonNull(originalFileName) && originalFileName.endsWith(extension);
  }
}
