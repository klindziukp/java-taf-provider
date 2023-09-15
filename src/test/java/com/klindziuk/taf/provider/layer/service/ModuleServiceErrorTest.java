/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.data.ModuleItemConverter;
import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.dto.response.ModuleItemResponse;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Optional;
import java.util.Set;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ModuleServiceErrorTest extends AbstractModuleServiceTest {

  @Mock private Validator validator;

  @Test
  void deleteModuleItemNonExistedIdErrorServiceTest() {
    // GIVEN
    final String uuid = ModuleItemTestData.uuid();
    Mockito.when(moduleRepository.findByUuid(uuid)).thenReturn(Optional.empty());
    // WHEN
    TafModuleItemResponse tafModuleItemResponse = moduleService.deleteModule(uuid);
    // THEN
    Assertions.assertThat(tafModuleItemResponse)
        .usingRecursiveComparison()
        .isEqualTo(new ModuleItemResponse());
  }

  @Test
  void addModuleItemConstraintViolationErrorServiceTest() {
    // GIVEN
    final ModuleItemRequest moduleItemRequest = ModuleItemTestData.createModuleItemRequest();
    final ModuleItem expectedModuleItem = ModuleItemConverter.toModuleItem(moduleItemRequest);
    final Set<ConstraintViolation<ModuleItem>> validate = validator.validate(expectedModuleItem);
    Mockito.when(wizardMapper.toModuleItem(moduleItemRequest)).thenReturn(expectedModuleItem);
    Mockito.when(moduleRepository.save(expectedModuleItem))
        .thenThrow(new ConstraintViolationException(validate));
    // WHEN
    ThrowingCallable methodToExecute = () -> moduleService.addModule(moduleItemRequest);
    // THEN
    Assertions.assertThatThrownBy(methodToExecute).isInstanceOf(ConstraintViolationException.class);
  }
}
