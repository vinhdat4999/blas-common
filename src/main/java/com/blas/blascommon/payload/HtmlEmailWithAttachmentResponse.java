package com.blas.blascommon.payload;

import java.util.List;
import lombok.Data;

@Data
public class HtmlEmailWithAttachmentResponse {

  private int failedEmailNum;
  private List<HtmlEmailWithAttachmentRequest> failedEmailList;
  private int sentEmailNum;
  private List<HtmlEmailWithAttachmentRequest> sentEmailList;
}
