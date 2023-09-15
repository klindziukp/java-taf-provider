/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.exception;

/** Generic exception for 'Taf Provider' service */
public class TafProviderException extends RuntimeException {

  public TafProviderException() {
    super();
  }

  public TafProviderException(String message) {
    super(message);
  }

  public TafProviderException(String message, Throwable cause) {
    super(message, cause);
  }

  public TafProviderException(Throwable cause) {
    super(cause);
  }

  public TafProviderException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
