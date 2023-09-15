/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.buildtool;

import com.klindziuk.taf.provider.model.generation.GenerationData;
import java.nio.file.Path;

public interface BuildToolGenerationService {

  void copyWrapperFolder(Path path);

  void createProjectFile(GenerationData generationData);
}
