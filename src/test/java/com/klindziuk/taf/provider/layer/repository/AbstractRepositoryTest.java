/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
@ComponentScan("com.klindziuk.taf.wizard.repository")
@Sql(scripts = {"classpath:sql/clean.sql", "classpath:sql/data.sql"})
public class AbstractRepositoryTest {

  @Autowired ModuleRepository moduleRepository;
}
