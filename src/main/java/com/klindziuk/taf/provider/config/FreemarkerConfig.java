/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.config;

import static freemarker.template.Configuration.SQUARE_BRACKET_INTERPOLATION_SYNTAX;

import com.klindziuk.taf.provider.exception.FreemarkerConfigurationException;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** 'Freemarker' configuration */
@Configuration
@Slf4j
public class FreemarkerConfig {

  private static final String FREEMARKER_BASE_PACKAGE_PATH = "template/freemarker";

  /**
   * Provides 'Freemarker' configuration
   *
   * @return Configuration class
   */
  @Bean
  public freemarker.template.Configuration freemarkerConfiguration() {
    try {
      final freemarker.template.Configuration freemarkerConfig =
          new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_32);
      final FileTemplateLoader loader =
          new FileTemplateLoader(new File(FREEMARKER_BASE_PACKAGE_PATH), false);
      freemarkerConfig.setTemplateLoader(loader);
      freemarkerConfig.setDefaultEncoding(StandardCharsets.UTF_8.name());
      freemarkerConfig.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
      freemarkerConfig.setInterpolationSyntax(SQUARE_BRACKET_INTERPOLATION_SYNTAX);

      return freemarkerConfig;
    } catch (IOException ioEx) {
      throw new FreemarkerConfigurationException();
    }
  }

  public Template getTemplate(String ftlPath) {
    try {
      return freemarkerConfiguration().getTemplate(ftlPath);
    } catch (IOException ioEx) {
      log.error("Unable to get template with '{}' path", ftlPath, ioEx);
      throw new FreemarkerConfigurationException(ioEx);
    }
  }
}
