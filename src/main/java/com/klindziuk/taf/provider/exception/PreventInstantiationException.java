/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.exception;

/** Exception to prevent instantiation of the object */
public class PreventInstantiationException extends RuntimeException {

  public PreventInstantiationException() {
    super("Instantiation of the class is prohibited");
  }
}
