package com.blas.blascommon.payload;

import lombok.Data;

@Data
public class FileAttachment {

  private String fileName;
  private String fileContent;
}
