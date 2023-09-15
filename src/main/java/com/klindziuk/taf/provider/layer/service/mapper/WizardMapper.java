/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service.mapper;

import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.project.TafModule;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface WizardMapper {

  ModuleItem toModuleItem(final ModuleItemRequest moduleItemRequest);

  TafModuleItemResponse toTafModuleItemResponse(final ModuleItem moduleItem);

  TafModule toTafModule(final ModuleItem moduleItem);

  List<TafModuleItemResponse> toTafModuleItemsResponse(final List<ModuleItem> moduleItems);
}
