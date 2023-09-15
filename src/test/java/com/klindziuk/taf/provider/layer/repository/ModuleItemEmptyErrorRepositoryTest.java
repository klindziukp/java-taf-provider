/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.TestValue;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ModuleItemEmptyErrorRepositoryTest extends AbstractRepositoryTest {

  @Test
  void addModuleItemWithEmptyGroupIdErrorRepositoryTest() {
    verifyAddModuleItemRepository(
        "groupId", ModuleItemTestData.moduleItem().setGroupId(TestValue.NULL_STRING));
  }

  @Test
  void addModuleItemWithMaxGroupIdRepositoryTest() {
    verifyAddModuleItemRepository(
        "groupId", ModuleItemTestData.moduleItem().setGroupId(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMinArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        "artifactId", ModuleItemTestData.moduleItem().setArtifactId(TestValue.NULL_STRING));
  }

  @Test
  void addModuleItemWithMaxArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        "artifactId", ModuleItemTestData.moduleItem().setArtifactId(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMinDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        "displayName", ModuleItemTestData.moduleItem().setDisplayName(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMaxDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        "displayName", ModuleItemTestData.moduleItem().setDisplayName(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMinDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        "description", ModuleItemTestData.moduleItem().setDescription(TestValue.NULL_STRING));
  }

  @Test
  void addModuleItemWithMaxDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        "description", ModuleItemTestData.moduleItem().setDescription(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMinModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        "moduleGroup", ModuleItemTestData.moduleItem().setModuleGroup(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMaxModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        "moduleGroup", ModuleItemTestData.moduleItem().setModuleGroup(TestValue.EMPTY_STRING));
  }

  @Test
  void addModuleItemWithMinVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        "version", ModuleItemTestData.moduleItem().setVersion(TestValue.NULL_STRING));
  }

  @Test
  void addModuleItemWithMaxVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        "version", ModuleItemTestData.moduleItem().setVersion(TestValue.EMPTY_STRING));
  }

  private void verifyAddModuleItemRepository(String paramName, ModuleItem moduleItem) {
    // WHEN
    ThrowingCallable throwingCallable = () -> moduleRepository.save(moduleItem);
    // THEN
    Assertions.assertThatThrownBy(throwingCallable)
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessageContaining("must not be empty")
        .hasMessageContaining(paramName);
  }
}
