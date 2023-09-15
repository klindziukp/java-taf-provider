/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.exception;

/** Generic exception for 'Taf Provider' service */
public class TafProviderSingleModuleException extends RuntimeException {

  public TafProviderSingleModuleException() {
    super();
  }

  public TafProviderSingleModuleException(String message) {
    super(message);
  }

  public TafProviderSingleModuleException(String message, Throwable cause) {
    super(message, cause);
  }

  public TafProviderSingleModuleException(Throwable cause) {
    super(cause);
  }

  public TafProviderSingleModuleException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
