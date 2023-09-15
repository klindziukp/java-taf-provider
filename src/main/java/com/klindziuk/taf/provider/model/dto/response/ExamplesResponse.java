/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.dto.response;

import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ExamplesResponse {

  private List<String> folders;
}
