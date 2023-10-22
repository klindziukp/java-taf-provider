/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.model.generation;

import com.klindziuk.taf.provider.config.generation.GenerationConfig;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.nio.file.Path;
import java.util.Set;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GenerationData {

  private String artifactId;
  private String groupId;
  private String projectName;
  private String description;
  private String buildTool;
  private String javaVersion;
  private String randomFolder;
  private GenerationConfig generationConfig;
  private Path randomDirectoryPath;
  private Path projectPath;
  private ModuleItem testEngine;
  private Set<String> moduleDisplayNames;
  private Set<ModuleItem> moduleItems;
}
