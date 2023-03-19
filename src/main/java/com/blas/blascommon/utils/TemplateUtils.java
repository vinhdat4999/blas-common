package com.blas.blascommon.utils;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@UtilityClass
public class TemplateUtils {

  public String generateHtmlContent(TemplateEngine templateEngine, String emailTemplateName,
      Map<String, String> data) {
    try {
      Context context = new Context();
      for (Entry<String, String> entry : data.entrySet()) {
        context.setVariable(entry.getKey(), entry.getValue());
      }
      return templateEngine.process(emailTemplateName, context);
    } catch (Exception e) {
      return EMPTY;
    }
  }
}
