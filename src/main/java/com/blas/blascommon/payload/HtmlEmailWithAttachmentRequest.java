package com.blas.blascommon.payload;

import java.util.List;
import java.util.Map;
import lombok.Data;
import org.springframework.data.util.Pair;

@Data
public class HtmlEmailWithAttachmentRequest {

  private String emailTo;
  private String title;
  private String emailTemplateName;
  private Map<String, String> data;
  private List<Pair<String, String>> fileList;
}
