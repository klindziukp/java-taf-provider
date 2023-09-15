/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.constant.ConfigurationProperty;
import com.klindziuk.taf.provider.constant.ModuleGroupInfo;
import com.klindziuk.taf.provider.layer.repository.ModuleRepository;
import com.klindziuk.taf.provider.layer.service.mapper.WizardMapper;
import com.klindziuk.taf.provider.model.ProjectPlatform;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.project.ProjectConfiguration;
import com.klindziuk.taf.provider.model.project.TafModuleGroup;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import com.klindziuk.taf.provider.model.response.TafModulesResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ModuleService {

  private final WizardMapper wizardMapper;
  private final ModuleRepository moduleItemRepository;

  @Autowired
  public ModuleService(WizardMapper wizardMapper, ModuleRepository moduleItemRepository) {
    this.wizardMapper = wizardMapper;
    this.moduleItemRepository = moduleItemRepository;
  }

  /**
   * Adds appropriate module
   *
   * @param moduleItemRequest module item request
   * @return ModuleItemResponse
   */
  public TafModuleItemResponse addModule(ModuleItemRequest moduleItemRequest) {
    final ModuleItem moduleItem =
        moduleItemRepository.save(wizardMapper.toModuleItem(moduleItemRequest));
    return wizardMapper.toTafModuleItemResponse(moduleItem);
  }

  /**
   * Deletes appropriate module by it's uuid
   *
   * @param uuid uuid
   * @return TafModuleItemResponse
   */
  public TafModuleItemResponse deleteModule(String uuid) {
    final Optional<ModuleItem> moduleItem = moduleItemRepository.findByUuid(uuid);
    if (moduleItem.isPresent()) {
      moduleItemRepository.delete(moduleItem.get());
      return wizardMapper.toTafModuleItemResponse(moduleItem.get());
    }
    return new TafModuleItemResponse();
  }

  public List<TafModuleItemResponse> getTafModuleItems() {
    return wizardMapper.toTafModuleItemsResponse(moduleItemRepository.findAll());
  }

  public TafModulesResponse tafModulesResponse() {
    final TafModulesResponse result = tafModulesEmptyResponse();
    for (ModuleItem moduleItem : moduleItemRepository.findAll()) {
      addModuleToAppropriateGroup(result, moduleItem);
    }
    result.setProjectConfigurations(projectConfigurations());
    return result;
  }

  private void addModuleToAppropriateGroup(
      TafModulesResponse tafModulesResponse, ModuleItem moduleItem) {
    for (TafModuleGroup tafModuleGroup : tafModulesResponse.getTafModuleGroups()) {
      if (tafModuleGroup.getName().equalsIgnoreCase(moduleItem.getModuleGroup())) {
        tafModuleGroup.getTafModules().add(wizardMapper.toTafModule(moduleItem));
        return;
      }
    }
  }

  private TafModulesResponse tafModulesEmptyResponse() {
    final TafModulesResponse result =
        new TafModulesResponse()
            .setPlatform(ProjectPlatform.JAVA.name())
            .setProjectConfigurations(new ArrayList<>())
            .setTafModuleGroups(new ArrayList<>());
    for (ModuleGroupInfo moduleGroupInfo : ModuleGroupInfo.values()) {
      result.getTafModuleGroups().add(tafModuleGroup(moduleGroupInfo));
    }
    return result;
  }

  private TafModuleGroup tafModuleGroup(ModuleGroupInfo moduleGroupInfo) {
    return new TafModuleGroup()
        .setId(moduleGroupInfo.getModuleGroupId())
        .setName(moduleGroupInfo.getModuleGroupName())
        .setDescription(moduleGroupInfo.getModuleGroupDescription())
        .setTafModules(new ArrayList<>());
  }

  private List<ProjectConfiguration> projectConfigurations() {
    return Arrays.stream(ConfigurationProperty.values())
        .map(v -> new ProjectConfiguration().setValue(v.getValue()).setRequired(v.isRequired()))
        .collect(Collectors.toList());
  }
}
