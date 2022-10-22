package com.blas.blascommon.payload;

import java.util.List;
import lombok.Data;

@Data
public class HtmlEmailResponse {

  private int sentEmailNum;
  private List<HtmlEmailRequest> htmlEmailRequestFailedList;
}