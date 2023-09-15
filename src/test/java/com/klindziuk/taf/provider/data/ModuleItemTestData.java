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
  //
  //  public static List<ModuleItem> moduleItemList() {
  //    return ModuleItemList(userId());
  //  }
  //
  //  public static List<ModuleItem> moduleItemList(long userId) {
  //    int ModuleItemsNumber = RANDOM.nextInt(2, 5);
  //    return ModuleItemList(userId, ModuleItemsNumber);
  //  }

  //  public static List<ModuleItem> moduleItemList(long userId, int ModuleItemsNumber) {
  //    List<ModuleItem> ModuleItemList = new ArrayList<>(ModuleItemsNumber);
  //    for (int i = 0; i < ModuleItemsNumber; ++i) {
  //      ModuleItemList.add(ModuleItem().setUserId(userId));
  //    }
  //    return ModuleItemList;
  //  }
  //
  //

  public static ModuleItemRequest createModuleItemRequest() {
    return new ModuleItemRequest()
        .setModuleGroup("test-module-group-" + RandomString.make(3))
        .setDescription("test-description-" + RandomString.make(3))
        .setArtifactId("test-artifact-id-" + RandomString.make(3));
  }
  //
  //  public static UpdateModuleItemRequest updateModuleItemRequest() {
  //    return new UpdateModuleItemRequest().setUserId(userId()).setTitle(title());
  //  }
  //
  //  public static ModuleItemResponse ModuleItemResponse() {
  //    return new
  // ModuleItemResponse().setId(id()).setUuid(uuid()).setUserId(userId()).setTitle(title());
  //  }
  //
  //  public static ItemList<ModuleItemResponse> ModuleItemListResponse(long userId) {
  //    int ModuleItemsNumber = RANDOM.nextInt(2, 5);
  //    return ModuleItemListResponse(userId, ModuleItemsNumber);
  //  }

  //  public static ItemList<ModuleItemResponse> ModuleItemListResponse(long userId, int
  // ModuleItemsNumber) {
  //    List<ModuleItemResponse> ModuleItemList = new ArrayList<>(ModuleItemsNumber);
  //    for (int i = 0; i < ModuleItemsNumber; ++i) {
  //      ModuleItemList.add(ModuleItemResponse().setUserId(userId));
  //    }
  //    return new ItemList<>(ModuleItemList, total());
  //  }

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
