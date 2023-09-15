/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.ModuleTestDataStorage;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import net.bytebuddy.utility.RandomString;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.springframework.dao.InvalidDataAccessApiUsageException;

public class ModuleItemErrorRepositoryTest extends AbstractRepositoryTest {

  @Test
  void findModuleItemByNonExistedIdErrorRepositoryTest() {
    // GIVEN
    final long userId = ModuleTestDataStorage.getRandomId() + ModuleItemTestData.userId();
    // WHEN
    final Optional<ModuleItem> moduleItemById = moduleRepository.findById(userId);
    // THEN
    Assertions.assertThat(moduleItemById).isEqualTo(Optional.empty());
  }

  @Test
  void findModuleItemByNonExistedUuidErrorRepositoryTest() {
    // GIVEN
    final String uuid = UUID.randomUUID().toString();
    // WHEN
    final Optional<ModuleItem> moduleItemById = moduleRepository.findByUuid(uuid);
    // THEN
    Assertions.assertThat(moduleItemById).isEqualTo(Optional.empty());
  }

  @Test
  void findModuleItemsByNonExistedModuleGroupErrorRepositoryTest() {
    // GIVEN
    final String uuid = UUID.randomUUID().toString();
    // WHEN
    final List<ModuleItem> moduleItemsById =
        moduleRepository.findByModuleGroup(RandomString.make());
    // THEN
    Assertions.assertThat(moduleItemsById).isEqualTo(Collections.emptyList());
  }

  @Test
  void deleteModuleItemByNonExistedIdErrorRepositoryTest() {
    // GIVEN
    final long moduleItemId = ModuleTestDataStorage.getRandomId() + ModuleItemTestData.id();
    // WHEN
    ThrowingCallable throwingCallable = () -> moduleRepository.deleteById(moduleItemId);
    // THEN
    Assertions.assertThatNoException().isThrownBy(throwingCallable);
  }

  @Test
  @SuppressWarnings("ConstantConditions")
  void deleteModuleItemByNullIdErrorRepositoryTest() {
    // GIVEN
    final Long moduleItemId = null;
    // WHEN
    ThrowingCallable throwingCallable = () -> moduleRepository.deleteById(moduleItemId);
    // THEN
    Assertions.assertThatThrownBy(throwingCallable)
        .isInstanceOf(InvalidDataAccessApiUsageException.class)
        .hasMessageContaining("The given id must not be null");
  }
}
