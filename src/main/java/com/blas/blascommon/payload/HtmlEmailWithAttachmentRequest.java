package com.blas.blascommon.payload;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class HtmlEmailWithAttachmentRequest extends EmailRequest {

  private List<FileAttachment> fileList;

  public HtmlEmailWithAttachmentRequest(String emailTo, String title, String emailTemplateName,
      Map<String, String> data, String reasonSendFailed, String status, LocalDateTime localDateTime,
      List<FileAttachment> fileList) {
    super(emailTo, title, emailTemplateName, data, reasonSendFailed, status, localDateTime);
    this.fileList = fileList;
  }
}
