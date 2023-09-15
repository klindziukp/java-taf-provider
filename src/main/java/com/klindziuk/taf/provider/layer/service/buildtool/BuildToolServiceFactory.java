/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool;

import com.klindziuk.taf.provider.model.dto.request.BuildTool;

public interface BuildToolServiceFactory {

  BuildToolGenerationService buildToolGenerationService(BuildTool buildTool);
}
