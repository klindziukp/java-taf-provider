/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.data;

import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public final class ModuleTestDataStorage {

  private static final List<ModuleItem> MODULE_ITEMS = new ArrayList<>();
  private static final AtomicLong COUNTER = new AtomicLong(0L);

  static {
    MODULE_ITEMS.add(
        new ModuleItem()
            .setId(COUNTER.incrementAndGet())
            .setUuid("baa82496-8f99-4092-bcc8-f55163b33f95")
            .setGroupId("com.klindziuk")
            .setArtifactId("module-taf-ui")
            .setDisplayName("UI")
            .setDescription("Description For UI Module")
            .setModuleGroup("taf-ui")
            .setVersion("1.0-SNAPSHOT")
            .setCreatedBy("wizard-user-0")
            .setUpdatedBy("wizard-user-0"));
    MODULE_ITEMS.add(
        new ModuleItem()
            .setId(COUNTER.incrementAndGet())
            .setUuid("68837202-d124-4251-bbfa-5469fa536d92")
            .setGroupId("com.klindziuk")
            .setArtifactId("module-taf-testng")
            .setDisplayName("TestNG")
            .setDescription("Description For TestNG Module")
            .setModuleGroup("taf-engine")
            .setVersion("1.0-SNAPSHOT")
            .setCreatedBy("wizard-user-1")
            .setUpdatedBy("wizard-user-1"));
    MODULE_ITEMS.add(
        new ModuleItem()
            .setId(COUNTER.incrementAndGet())
            .setUuid("e021d6c9-66d8-4715-9879-47c84f5a3466")
            .setGroupId("com.klindziuk")
            .setArtifactId("module-taf-junit")
            .setDisplayName("Junit")
            .setDescription("Description For Junit Module")
            .setModuleGroup("taf-engine")
            .setVersion("1.0-SNAPSHOT")
            .setCreatedBy("wizard-user-2")
            .setUpdatedBy("wizard-user-2"));
    MODULE_ITEMS.add(
        new ModuleItem()
            .setId(COUNTER.incrementAndGet())
            .setUuid("e021d6c9-62x8-4902-9879-fv281f5a34dd")
            .setGroupId("com.klindziuk")
            .setArtifactId("module-taf-reportportal")
            .setDisplayName("ReportPortal")
            .setDescription("Description For Report Portal Module")
            .setModuleGroup("taf-reporting")
            .setVersion("1.0-SNAPSHOT")
            .setCreatedBy("wizard-user-3")
            .setUpdatedBy("wizard-user-3"));
  }

  private ModuleTestDataStorage() {}

  public static List<ModuleItem> moduleItems() {
    return List.copyOf(MODULE_ITEMS);
  }

  public static List<ModuleItem> getModuleItemsByModuleGroup(String moduleGroup) {
    return MODULE_ITEMS.stream()
        .filter(module -> module.getModuleGroup().equals(moduleGroup))
        .collect(Collectors.toList());
  }

  public static Optional<ModuleItem> getModuleItemById(Long id) {
    return MODULE_ITEMS.stream().filter(moduleItem -> moduleItem.getId().equals(id)).findFirst();
  }

  public static Optional<ModuleItem> getModuleItemByUuid(String uuid) {
    return MODULE_ITEMS.stream()
        .filter(moduleItem -> moduleItem.getUuid().equals(uuid))
        .findFirst();
  }

  public static ModuleItem getRandomModuleItem() {
    return MODULE_ITEMS.get(new Random().nextInt(MODULE_ITEMS.size()));
  }

  public static long getRandomId() {
    return getRandomModuleItem().getId();
  }

  public static String getRandomModuleUuid() {
    return getRandomModuleItem().getUuid();
  }

  public static String getRandomModuleGroup() {
    return getRandomModuleItem().getModuleGroup();
  }
}
