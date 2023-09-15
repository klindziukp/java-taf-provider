/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.exception;

/** Generic exception for 'Taf Provider' service */
public class FreemarkerConfigurationException extends RuntimeException {

  public FreemarkerConfigurationException() {
    super();
  }

  public FreemarkerConfigurationException(String message) {
    super(message);
  }

  public FreemarkerConfigurationException(String message, Throwable cause) {
    super(message, cause);
  }

  public FreemarkerConfigurationException(Throwable cause) {
    super(cause);
  }

  public FreemarkerConfigurationException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
