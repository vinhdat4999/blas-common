package com.blas.blascommon.dto;

import lombok.Data;

@Data
public class ImgbbResponseDto {

  private ImgbbDataDto data;

  private boolean success;

  private int status;

}
