/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.layer.repository.ModuleRepository;
import com.klindziuk.taf.provider.layer.service.mapper.WizardMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public abstract class AbstractModuleServiceTest {

  protected ModuleRepository moduleRepository;
  protected WizardMapper wizardMapper;
  protected ModuleService moduleService;

  @BeforeEach
  public void init() {
    moduleRepository = Mockito.mock(ModuleRepository.class);
    wizardMapper = Mockito.mock(WizardMapper.class);
    moduleService = new ModuleService(wizardMapper, moduleRepository);
  }
}
