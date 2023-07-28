package com.blas.blascommon.payload;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class HtmlEmailRequest extends EmailRequest {

  public HtmlEmailRequest(String emailTo, String title, String emailTemplateName,
      Map<String, String> data, String reasonSendFailed, String status,
      LocalDateTime localDateTime) {
    super(emailTo, title, emailTemplateName, data, reasonSendFailed, status, localDateTime);
  }
}
