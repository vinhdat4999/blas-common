package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.fileutils.FileUtils.convertInputStreamToString;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.enums.EmailTemplate;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Slf4j
@Component
public class TemplateUtils {

  /**
   * This method use to generate HTML string from thymeleaf file in Spring framework
   *
   * @param emailTemplate Email template to send email
   * @param data          A map of data to send email
   * @return HTML string
   */
  public String generateHtmlContent(EmailTemplate emailTemplate, Map<String, String> data)
      throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(
        "templates/" + emailTemplate.getTemplateName() + ".html");
    assert inputStream != null;
    String template = IOUtils.toString(inputStream, UTF_8);
    Context context = new Context();
    for (Entry<String, String> entry : data.entrySet()) {
      context.setVariable(entry.getKey(), entry.getValue());
    }
    log.debug("Generating HTML template: {}", emailTemplate);
    return new TemplateEngine().process(template, context);
  }

  public Set<String> getAllVariableOfThymeleafTemplate(EmailTemplate emailTemplate)
      throws IOException {
    ClassLoader classLoader = getClass().getClassLoader();
    InputStream inputStream = classLoader.getResourceAsStream(
        "templates/" + emailTemplate.getTemplateName() + ".html");
    String content = convertInputStreamToString(inputStream);
    Pattern pattern = Pattern.compile("\\$\\{([^{!#}]+)}");
    Matcher matcher = pattern.matcher(content);
    Set<String> variables = new HashSet<>();
    while (matcher.find()) {
      for (int index = 1; index <= matcher.groupCount(); index++) {
        variables.add(matcher.group(index));
      }
    }
    return variables;
  }
}
