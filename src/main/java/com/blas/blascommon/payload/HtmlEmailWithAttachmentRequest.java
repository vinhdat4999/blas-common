package com.blas.blascommon.payload;

import lombok.Data;

@Data
public class HtmlEmailWithAttachmentRequest {

  private String emailTo;
  private String title;
  private String content;
  private String fileName;
  private String base64FileContent;
}