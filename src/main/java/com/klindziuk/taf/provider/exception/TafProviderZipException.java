/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.exception;

/** Exception for 'Taf Provider' service zip/unzip functionality */
public class TafProviderZipException extends RuntimeException {

  public TafProviderZipException(String message) {
    super(message);
  }

  public TafProviderZipException(String message, Throwable cause) {
    super(message, cause);
  }

  public TafProviderZipException(Throwable cause) {
    super(cause);
  }

  public TafProviderZipException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
