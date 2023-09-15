/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.data;

import com.klindziuk.taf.provider.exception.PreventInstantiationException;
import com.klindziuk.taf.provider.model.domain.ModuleItem;
import com.klindziuk.taf.provider.model.dto.request.ModuleItemRequest;
import com.klindziuk.taf.provider.model.response.TafModuleItemResponse;
import java.util.ArrayList;
import java.util.List;

public final class ModuleItemConverter {

  private ModuleItemConverter() {
    throw new PreventInstantiationException();
  }

  public static ModuleItem toModuleItem(ModuleItemRequest moduleItemRequest) {
    return new ModuleItem()
        .setModuleGroup(moduleItemRequest.getModuleGroup())
        .setDescription(moduleItemRequest.getDescription())
        .setVersion(moduleItemRequest.getVersion())
        .setDisplayName(moduleItemRequest.getDisplayName())
        .setGroupId(moduleItemRequest.getGroupId());
  }

  public static TafModuleItemResponse toTafModuleItemResponse(ModuleItem moduleItem) {
    return new TafModuleItemResponse()
        .setUuid(moduleItem.getUuid())
        .setArtifactId(moduleItem.getArtifactId())
        .setModuleGroup(moduleItem.getModuleGroup())
        .setDisplayName(moduleItem.getDisplayName());
  }

  public static List<TafModuleItemResponse> toModuleItemResponseList(List<ModuleItem> moduleItems) {
    final List<TafModuleItemResponse> result = new ArrayList<>();
    for (ModuleItem moduleItem : moduleItems) {
      result.add(toTafModuleItemResponse(moduleItem));
    }
    return result;
  }
}
