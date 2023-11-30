package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceUnavailableException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  public ServiceUnavailableException(BlasErrorCodeEnum blasErrorCodeEnum) {
    super(blasErrorCodeEnum.getMessageDescription());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public ServiceUnavailableException(String message) {
    super(message);
    this.blasErrorCodeEnum = MSG_UNKNOWN_ERROR;
  }

  public ServiceUnavailableException(BlasErrorCodeEnum blasErrorCodeEnum, String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = BlasErrorCodeEnum.MSG_UNKNOWN_ERROR;
  }

  public ServiceUnavailableException(BlasErrorCodeEnum blasErrorCodeEnum, Throwable cause) {
    super(cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }

  public ServiceUnavailableException(BlasErrorCodeEnum blasErrorCodeEnum, String message,
      Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
  }
}
