/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.ModuleTestDataStorage;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ModuleRepositoryTest extends AbstractRepositoryTest {

  @Test
  void findAllModuleItemsRepositoryTest() {
    // GIVEN
    final List<ModuleItem> moduleItemsFromStorage = ModuleTestDataStorage.moduleItems();
    // WHEN
    final List<ModuleItem> moduleItems = moduleRepository.findAll();
    // THEN
    Assertions.assertThat(moduleItemsFromStorage)
        .usingRecursiveComparison()
        .ignoringFields("createdAt", "updatedAt")
        .isEqualTo(moduleItems);
  }

  @Test
  void findModuleItemByUuidRepositoryTest() {
    // GIVEN
    final String moduleItemUuid = ModuleTestDataStorage.getRandomModuleUuid();
    final Optional<ModuleItem> moduleItemFromStorage =
        ModuleTestDataStorage.getModuleItemByUuid(moduleItemUuid);
    // WHEN
    final Optional<ModuleItem> moduleItemByUuid = moduleRepository.findByUuid(moduleItemUuid);
    // THEN
    Assertions.assertThat(moduleItemByUuid)
        .usingRecursiveComparison()
        .ignoringFields("value.createdAt", "value.updatedAt")
        .isEqualTo(moduleItemFromStorage);
  }

  @Test
  void findModuleByModuleGroupRepositoryTest() {
    // GIVEN
    final String moduleItemGroup = ModuleTestDataStorage.getRandomModuleGroup();
    final List<ModuleItem> moduleItemsFromStorage =
        ModuleTestDataStorage.getModuleItemsByModuleGroup(moduleItemGroup);
    // WHEN
    final List<ModuleItem> moduleItemsByGroup = moduleRepository.findByModuleGroup(moduleItemGroup);
    // THEN
    Assertions.assertThat(moduleItemsByGroup)
        .usingRecursiveComparison()
        .ignoringFields("createdAt", "updatedAt")
        .isEqualTo(moduleItemsFromStorage);
  }

  @Test
  void deleteModuleItemByItemRepositoryTest() {
    // GIVEN
    final ModuleItem moduleItem = ModuleTestDataStorage.getRandomModuleItem();
    final String uuid = moduleItem.getUuid();
    final Optional<ModuleItem> moduleItemFromStorage =
        ModuleTestDataStorage.getModuleItemByUuid(uuid);
    final Optional<ModuleItem> moduleItemByUuid = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(moduleItemFromStorage)
        .usingRecursiveComparison()
        .ignoringFields("value.createdAt", "value.updatedAt")
        .isEqualTo(moduleItemByUuid);
    // WHEN
    moduleRepository.delete(moduleItem);
    // THEN
    final Optional<ModuleItem> deletedModuleItemById = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(deletedModuleItemById)
        .usingRecursiveComparison()
        .isEqualTo(Optional.empty());
  }

  @Test
  void deleteModuleItemByIdRepositoryTest() {
    // GIVEN
    final ModuleItem moduleItem = ModuleTestDataStorage.getRandomModuleItem();
    final String uuid = moduleItem.getUuid();
    final Optional<ModuleItem> moduleItemFromStorage =
        ModuleTestDataStorage.getModuleItemByUuid(uuid);
    final Optional<ModuleItem> moduleItemByUuid = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(moduleItemFromStorage)
        .usingRecursiveComparison()
        .ignoringFields("value.createdAt", "value.updatedAt")
        .isEqualTo(moduleItemByUuid);
    // WHEN
    moduleRepository.deleteById(moduleItem.getId());
    // THEN
    final Optional<ModuleItem> deletedModuleItemById = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(deletedModuleItemById)
        .usingRecursiveComparison()
        .isEqualTo(Optional.empty());
  }

  @Test
  void deleteModuleItemByUuidRepositoryTest() {
    // GIVEN
    final ModuleItem moduleItem = ModuleTestDataStorage.getRandomModuleItem();
    final String uuid = moduleItem.getUuid();
    final Optional<ModuleItem> moduleItemFromStorage =
        ModuleTestDataStorage.getModuleItemByUuid(uuid);
    final Optional<ModuleItem> moduleItemByUuid = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(moduleItemFromStorage)
        .usingRecursiveComparison()
        .ignoringFields("value.createdAt", "value.updatedAt")
        .isEqualTo(moduleItemByUuid);
    // WHEN
    moduleRepository.deleteByUuid(moduleItem.getUuid());
    // THEN
    final Optional<ModuleItem> deletedModuleItemById = moduleRepository.findByUuid(uuid);
    Assertions.assertThat(deletedModuleItemById)
        .usingRecursiveComparison()
        .isEqualTo(Optional.empty());
  }

  @Test
  void addModuleItemRepositoryTest() {
    // GIVEN
    final ModuleItem moduleItemToAdd = ModuleItemTestData.moduleItem();
    // WHEN
    final ModuleItem savedModuleItem = moduleRepository.save(moduleItemToAdd);
    // THEN
    final Optional<ModuleItem> moduleItem = moduleRepository.findByUuid(savedModuleItem.getUuid());
    Assertions.assertThat(moduleItem)
        .usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(Optional.of(savedModuleItem));
  }
}
