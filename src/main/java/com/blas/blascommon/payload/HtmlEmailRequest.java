package com.blas.blascommon.payload;

import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HtmlEmailRequest extends EmailRequest {

  public HtmlEmailRequest(String emailTo, String title, String emailTemplateName,
      Map<String, String> data, String reasonSendFailed) {
    super(emailTo, title, emailTemplateName, data, reasonSendFailed);
  }
}
