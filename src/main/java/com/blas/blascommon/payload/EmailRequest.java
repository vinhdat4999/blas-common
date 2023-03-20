package com.blas.blascommon.payload;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

  protected String emailTo;
  protected String title;
  protected String emailTemplateName;
  protected Map<String, String> data;
  protected String reasonSendFailed;
}
