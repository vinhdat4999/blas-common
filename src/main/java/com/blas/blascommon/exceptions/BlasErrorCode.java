package com.blas.blascommon.exceptions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BlasErrorCode {

  private int httpCode;

  @JsonProperty("blasErrorCode")
  private int errorCode;

  private String messageDescription;

  public BlasErrorCode(BlasErrorCodeEnum blasErrorCodeEnum) {
    this.httpCode = blasErrorCodeEnum.getHttpCode();
    this.errorCode = blasErrorCodeEnum.getErrorCode();
    this.messageDescription = blasErrorCodeEnum.getMessageDescription();
  }
}
