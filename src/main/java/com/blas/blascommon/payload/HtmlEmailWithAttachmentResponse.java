package com.blas.blascommon.payload;

import java.util.List;
import lombok.Data;

@Data
public class HtmlEmailWithAttachmentResponse {

  private int sentEmailNum;
  private List<HtmlEmailWithAttachmentRequest> htmlEmailWithAttachmentRequestFailedList;
}