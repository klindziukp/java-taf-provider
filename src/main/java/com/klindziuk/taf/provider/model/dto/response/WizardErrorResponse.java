/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.response;

import java.time.Instant;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class WizardErrorResponse {

  private Instant time;
  private HttpStatus httpStatus;
  private String error;
  private List<String> errorMessages;
  private String requestPath;
}
