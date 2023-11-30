package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlasException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  public BlasException(BlasErrorCodeEnum blasErrorCodeEnum) {
    super(blasErrorCodeEnum.getMessageDescription());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BlasException(String message) {
    super(message);
    this.blasErrorCodeEnum = MSG_UNKNOWN_ERROR;
  }

  public BlasException(BlasErrorCodeEnum blasErrorCodeEnum, String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BlasException(String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;
  }

  public BlasException(BlasErrorCodeEnum blasErrorCodeEnum, Throwable cause) {
    super(cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public BlasException(BlasErrorCodeEnum blasErrorCodeEnum, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }
}
