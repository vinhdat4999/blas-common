package com.blas.blascommon.payload;

import lombok.Data;

@Data
public class HtmlEmailRequest {

  private String emailTo;
  private String title;
  private String content;
}