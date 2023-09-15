/*
 * Copyright (c) Dandelion development.
 */

package com.klindziuk.taf.provider.layer.service;

import com.klindziuk.taf.provider.config.FreemarkerConfig;
import com.klindziuk.taf.provider.constant.FreemarkerConstant;
import com.klindziuk.taf.provider.exception.TafProviderException;
import freemarker.template.Template;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Path;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** 'Freemarker' service */
@Service
public class FreemarkerService {

  @Autowired private final FreemarkerConfig freemarkerConfig;

  @Autowired
  public FreemarkerService(FreemarkerConfig freemarkerConfig) {
    this.freemarkerConfig = freemarkerConfig;
  }

  public Template createTemplate(String ftlFilePath) {
    return freemarkerConfig.getTemplate(ftlFilePath);
  }

  public void createFile(
      Template template, Path path, String fileName, Map<String, Object> params) {
    try {
      final String fileToWrite = path.toAbsolutePath() + fileName;

      BufferedWriter out =
          new BufferedWriter(
              new OutputStreamWriter(
                  new FileOutputStream(fileToWrite), FreemarkerConstant.CHARSET_ISO_8859_9));

      template.process(params, out);
      out.flush();
      out.close();

    } catch (Exception ex) {
      throw new TafProviderException(ex);
    }
  }
}
