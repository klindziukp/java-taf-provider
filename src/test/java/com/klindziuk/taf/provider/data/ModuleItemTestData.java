/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.data;

import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import java.util.Random;
import java.util.UUID;
import net.bytebuddy.utility.RandomString;

public final class ModuleItemTestData {

  private static final Random RANDOM = new Random();

  private ModuleItemTestData() {}

  public static ModuleItem moduleItem() {
    return new ModuleItem()
        .setUuid(ModuleItemTestData.uuid())
        .setGroupId(moduleGroupId())
        .setArtifactId(ModuleItemTestData.artifactId())
        .setDisplayName(ModuleItemTestData.displayName())
        .setDescription("Description For Test Module")
        .setModuleGroup(moduleGroup())
        .setVersion("1.0-SNAPSHOT")
        .setCreatedBy("wizard-user-2")
        .setUpdatedBy("wizard-user-2");
  }

  public static ModuleItemRequest createModuleItemRequest() {
    return new ModuleItemRequest()
        .setModuleGroup("test-module-group-" + RandomString.make(3))
        .setDescription("test-description-" + RandomString.make(3))
        .setArtifactId("test-artifact-id-" + RandomString.make(3));
  }

  public static long id() {
    return RANDOM.nextLong();
  }

  public static String uuid() {
    return UUID.randomUUID().toString();
  }

  public static String artifactId() {
    return "unit.test";
  }

  public static String displayName() {
    return "wizard-group-" + RandomString.make();
  }

  public static String moduleGroupId() {
    return "com.wizard";
  }

  public static String moduleGroup() {
    return "wizard-group-" + RandomString.make();
  }

  public static long total() {
    return RANDOM.nextLong(10_000);
  }

  public static long userId() {
    return RANDOM.nextLong();
  }
}
