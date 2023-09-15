/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.TestValue;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import jakarta.validation.ConstraintViolationException;
import java.util.function.Function;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class ModuleItemSizeErrorRepositoryTest extends AbstractRepositoryTest {

  @Test
  void addModuleItemWithMinGroupIdErrorRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        "groupId",
        (groupId) -> ModuleItemTestData.moduleItem().setGroupId(groupId));
  }

  @Test
  void addModuleItemWithMaxGroupIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_NOT_ALLOWED_LENGTH,
        "groupId",
        (groupId) -> ModuleItemTestData.moduleItem().setGroupId(groupId));
  }

  @Test
  void addModuleItemWithMinArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        "artifactId",
        (artifactId) -> ModuleItemTestData.moduleItem().setArtifactId(artifactId));
  }

  @Test
  void addModuleItemWithMaxArtifactIdRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_NOT_ALLOWED_LENGTH,
        "artifactId",
        (artifactId) -> ModuleItemTestData.moduleItem().setArtifactId(artifactId));
  }

  @Test
  void addModuleItemWithMinDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        "displayName",
        (displayName) -> ModuleItemTestData.moduleItem().setDisplayName(displayName));
  }

  @Test
  void addModuleItemWithMaxDisplayNameRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_NOT_ALLOWED_LENGTH,
        "displayName",
        (displayName) -> ModuleItemTestData.moduleItem().setDisplayName(displayName));
  }

  @Test
  void addModuleItemWithMinDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        1000,
        "description",
        (description) -> ModuleItemTestData.moduleItem().setDescription(description));
  }

  @Test
  void addModuleItemWithMaxDescriptionRepositoryTest() {
    verifyAddModuleItemRepository(
        1001,
        1000,
        "description",
        (description) -> ModuleItemTestData.moduleItem().setDescription(description));
  }

  @Test
  void addModuleItemWithMinModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        "moduleGroup",
        (moduleGroup) -> ModuleItemTestData.moduleItem().setModuleGroup(moduleGroup));
  }

  @Test
  void addModuleItemWithMaxModuleGroupRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_NOT_ALLOWED_LENGTH,
        "moduleGroup",
        (moduleGroup) -> ModuleItemTestData.moduleItem().setModuleGroup(moduleGroup));
  }

  @Test
  void addModuleItemWithMinVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MIN_NOT_ALLOWED_LENGTH,
        "version",
        (version) -> ModuleItemTestData.moduleItem().setVersion(version));
  }

  @Test
  void addModuleItemWithMaxVersionRepositoryTest() {
    verifyAddModuleItemRepository(
        TestValue.MAX_NOT_ALLOWED_LENGTH,
        "version",
        (version) -> ModuleItemTestData.moduleItem().setVersion(version));
  }

  private void verifyAddModuleItemRepository(
      int invalidSize, String paramName, Function<String, ModuleItem> moduleItemFunction) {
    verifyAddModuleItemRepository(
        invalidSize, TestValue.MAX_ALLOWED_LENGTH, paramName, moduleItemFunction);
  }

  private void verifyAddModuleItemRepository(
      int invalidSize,
      int validSize,
      String paramName,
      Function<String, ModuleItem> moduleItemFunction) {
    // GIVEN
    final String fieldValue = RandomString.make(invalidSize);
    // WHEN
    ThrowingCallable throwingCallable =
        () -> moduleRepository.save(moduleItemFunction.apply(fieldValue));
    // THEN
    Assertions.assertThatThrownBy(throwingCallable)
        .isInstanceOf(ConstraintViolationException.class)
        .hasMessageContaining("size must be between 3 and %s", validSize)
        .hasMessageContaining(paramName);
  }
}
