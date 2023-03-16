package com.blas.blascommon.payload;

import java.util.Map;
import lombok.Data;

@Data
public class HtmlEmailRequest {

  private String emailTo;
  private String title;
  private String emailTemplateName;
  private Map<String, String> data;
}
