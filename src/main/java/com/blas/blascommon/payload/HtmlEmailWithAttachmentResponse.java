package com.blas.blascommon.payload;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HtmlEmailWithAttachmentResponse {

  private int failedEmailNum;
  private List<EmailRequest> failedEmailList;
  private int sentEmailNum;
  private List<EmailRequest> sentEmailList;
  private LocalDateTime generatedTime;
  private String generatedBy;
}
