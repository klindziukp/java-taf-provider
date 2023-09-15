/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import com.klindziuk.taf.provider.model.domain.ModuleItem;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModuleRepository extends JpaRepository<ModuleItem, Long> {

  Optional<ModuleItem> findByUuid(String uuid);

  void deleteByUuid(String uuid);

  List<ModuleItem> findByModuleGroup(String modelGroup);
}
