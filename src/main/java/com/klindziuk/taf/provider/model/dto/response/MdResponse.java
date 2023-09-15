/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MdResponse {

  private String message;
}
