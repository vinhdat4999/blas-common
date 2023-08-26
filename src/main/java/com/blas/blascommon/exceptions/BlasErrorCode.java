package com.blas.blascommon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlasErrorCode {

  private int httpCode;
  private int blasErrorCode;
  private String messageDescription;

  public BlasErrorCode(BlasErrorCodeEnum blasErrorCodeEnum) {
    this.httpCode = blasErrorCodeEnum.getHttpCode();
    this.blasErrorCode = blasErrorCodeEnum.getBlasErrorCode();
    this.messageDescription = blasErrorCodeEnum.getMessageDescription();
  }
}
