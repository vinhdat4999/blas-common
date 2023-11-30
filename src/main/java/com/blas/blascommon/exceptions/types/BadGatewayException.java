package com.blas.blascommon.exceptions.types;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadGatewayException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  public BadGatewayException(BlasErrorCodeEnum blasErrorCodeEnum) {
    super(blasErrorCodeEnum.getMessageDescription());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BadGatewayException(BlasErrorCodeEnum blasErrorCodeEnum, String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BadGatewayException(String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;
  }

  public BadGatewayException(BlasErrorCodeEnum blasErrorCodeEnum, Throwable cause) {
    super(cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BadGatewayException(BlasErrorCodeEnum blasErrorCodeEnum, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }
}
