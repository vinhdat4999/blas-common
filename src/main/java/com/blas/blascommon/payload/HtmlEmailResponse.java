package com.blas.blascommon.payload;

import java.util.List;
import lombok.Data;

@Data
public class HtmlEmailResponse {

  private int failedEmailNum;
  private List<HtmlEmailRequest> failedEmailList;
  private int sentEmailNum;
  private List<HtmlEmailRequest> sentEmailList;
}
