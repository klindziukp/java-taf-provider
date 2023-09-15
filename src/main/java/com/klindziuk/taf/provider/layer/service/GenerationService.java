/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.model.request.GenerateProjectRequest;

public interface GenerationService {

  byte[] generateProject(GenerateProjectRequest generateProjectRequest);
}
