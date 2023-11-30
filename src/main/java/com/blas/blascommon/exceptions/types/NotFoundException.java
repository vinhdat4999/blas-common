package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotFoundException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  public NotFoundException(BlasErrorCodeEnum blasErrorCodeEnum) {
    super(blasErrorCodeEnum.getMessageDescription());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public NotFoundException(String message) {
    super(message);
    this.blasErrorCodeEnum = MSG_UNKNOWN_ERROR;
  }

  public NotFoundException(BlasErrorCodeEnum blasErrorCodeEnum, String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public NotFoundException(String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;
  }

  public NotFoundException(BlasErrorCodeEnum blasErrorCodeEnum, Throwable cause) {
    super(cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public NotFoundException(BlasErrorCodeEnum blasErrorCodeEnum, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }
}
