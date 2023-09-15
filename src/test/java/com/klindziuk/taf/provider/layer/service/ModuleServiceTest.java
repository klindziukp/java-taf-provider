/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.data.ModuleItemConverter;
import com.klindziuk.taf.provider.data.ModuleItemTestData;
import com.klindziuk.taf.provider.data.ModuleTestDataStorage;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ModuleServiceTest extends AbstractModuleServiceTest {

  @Test
  void getModuleItemsServiceTest() {
    // GIVEN
    final List<ModuleItem> moduleItems = ModuleTestDataStorage.moduleItems();
    final List<TafModuleItemResponse> expected =
        ModuleItemConverter.toModuleItemResponseList(moduleItems);
    Mockito.when(moduleRepository.findAll()).thenReturn(moduleItems);
    Mockito.when(wizardMapper.toTafModuleItemsResponse(moduleItems)).thenReturn(expected);
    // WHEN
    final List<TafModuleItemResponse> actual = moduleService.getTafModuleItems();
    // THEN
    Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void getModuleItemsResponseServiceTest() {
    // GIVEN
    // TODO(pklindziuk): add tests with tafModules
    // WHEN
    // THEN
  }

  @Test
  void addModuleServiceTest() {
    // GIVEN
    final ModuleItemRequest moduleItemRequest = ModuleItemTestData.createModuleItemRequest();
    final ModuleItem expectedModuleItem = ModuleItemConverter.toModuleItem(moduleItemRequest);
    final ModuleItem createdModuleItem = ModuleItemTestData.moduleItem();
    final TafModuleItemResponse expected =
        ModuleItemConverter.toTafModuleItemResponse(createdModuleItem);
    Mockito.when(wizardMapper.toModuleItem(moduleItemRequest)).thenReturn(expectedModuleItem);
    Mockito.when(moduleRepository.save(expectedModuleItem)).thenReturn(createdModuleItem);
    Mockito.when(wizardMapper.toTafModuleItemResponse(createdModuleItem)).thenReturn(expected);
    // WHEN
    TafModuleItemResponse actual = moduleService.addModule(moduleItemRequest);
    // THEN
    Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void deleteModuleServiceTest() {
    // GIVEN
    final ModuleItem moduleItem = ModuleTestDataStorage.getRandomModuleItem();
    final TafModuleItemResponse expected = ModuleItemConverter.toTafModuleItemResponse(moduleItem);
    Mockito.when(moduleRepository.findByUuid(moduleItem.getUuid()))
        .thenReturn(Optional.of(moduleItem));
    Mockito.doNothing().when(moduleRepository).deleteByUuid(moduleItem.getUuid());
    Mockito.when(wizardMapper.toTafModuleItemResponse(moduleItem)).thenReturn(expected);
    // WHEN
    final TafModuleItemResponse actual = moduleService.deleteModule(moduleItem.getUuid());
    // THEN
    Assertions.assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
  }
}
