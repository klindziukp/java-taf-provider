/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.controller.handler;

import com.klindziuk.taf.provider.exception.TafProviderSingleModuleException;
import com.klindziuk.taf.provider.exception.TafProviderZipException;
import com.klindziuk.taf.provider.model.dto.response.WizardErrorResponse;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class TafProviderExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(SizeLimitExceededException.class)
  public ResponseEntity<Object> handleSizeLimitException(
      RuntimeException runtimeException, WebRequest request) {
    WizardErrorResponse wizardErrorResponse =
        new WizardErrorResponse()
            .setTime(Instant.now())
            .setHttpStatus(HttpStatus.BAD_REQUEST)
            .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .setRequestPath(((ServletWebRequest) request).getRequest().getRequestURI())
            .setErrorMessages(List.of(extractMessage(runtimeException)));
    return new ResponseEntity<>(wizardErrorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(TafProviderZipException.class)
  public ResponseEntity<WizardErrorResponse> handleWizardZipException(
      RuntimeException runtimeException, WebRequest request) {
    WizardErrorResponse wizardErrorResponse =
        new WizardErrorResponse()
            .setTime(Instant.now())
            .setHttpStatus(HttpStatus.BAD_REQUEST)
            .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .setRequestPath(((ServletWebRequest) request).getRequest().getRequestURI())
            .setErrorMessages(List.of(extractMessage(runtimeException)));
    return new ResponseEntity<>(wizardErrorResponse, HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ExceptionHandler(TafProviderSingleModuleException.class)
  public ResponseEntity<WizardErrorResponse> handleProviderSingleModuleException(
      RuntimeException runtimeException, WebRequest request) {
    WizardErrorResponse wizardErrorResponse =
        new WizardErrorResponse()
            .setTime(Instant.now())
            .setHttpStatus(HttpStatus.BAD_REQUEST)
            .setError(HttpStatus.BAD_REQUEST.getReasonPhrase())
            .setRequestPath(((ServletWebRequest) request).getRequest().getRequestURI())
            .setErrorMessages(List.of(extractMessage(runtimeException)));
    final HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(wizardErrorResponse, headers, HttpStatus.BAD_REQUEST);
  }

  private String extractMessage(RuntimeException runtimeException) {
    if (Objects.nonNull(runtimeException.getCause())) {
      return runtimeException.getCause().getMessage();
    }
    return runtimeException.getMessage();
  }
}
