/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.TestValue;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.util.Optional;
import java.util.function.Function;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ModuleItemSizeRepositoryTest extends AbstractRepositoryTest {

  @Autowired private ModuleRepository moduleItemRepository;

  @Test
  void addModuleItemWithMinGroupIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setGroupId(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxGroupIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setGroupId(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMinArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setArtifactId(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setArtifactId(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMinDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setDisplayName(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setDisplayName(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMinDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setDescription(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        1000, (moduleItemGroup) -> ModuleItemTestData.moduleItem().setDescription(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMinModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setModuleGroup(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setModuleGroup(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMinVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setVersion(moduleItemGroup));
  }

  @Test
  void addModuleItemWithMaxVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_ALLOWED_LENGTH,
        (moduleItemGroup) -> ModuleItemTestData.moduleItem().setVersion(moduleItemGroup));
  }

  private void verifyAddModuleItemRepository(
      int length, Function<String, ModuleItem> moduleItemFunction) {
    // GIVEN
    final String fieldValue = RandomString.make(length);
    // WHEN
    final ModuleItem savedModuleItem =
        moduleItemRepository.save(moduleItemFunction.apply(fieldValue));
    // THEN
    final Optional<ModuleItem> moduleItem =
        moduleItemRepository.findByUuid(savedModuleItem.getUuid());
    Assertions.assertThat(moduleItem)
        .usingRecursiveComparison()
        .ignoringCollectionOrder()
        .isEqualTo(Optional.of(savedModuleItem));
  }
}
