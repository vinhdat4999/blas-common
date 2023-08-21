package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCode.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UnauthorizedException extends RuntimeException {

  private final BlasErrorCode blasErrorCode;

  public UnauthorizedException(BlasErrorCode blasErrorCode) {
    super(blasErrorCode.getMessageDescription());
    this.blasErrorCode = blasErrorCode;
  }

  public UnauthorizedException(String message) {
    super(message);
    this.blasErrorCode = MSG_UNKNOWN_ERROR;
  }

  public UnauthorizedException(BlasErrorCode blasErrorCode, String message) {
    super(message);
    this.blasErrorCode = blasErrorCode;
  }

  public UnauthorizedException(BlasErrorCode blasErrorCode, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCode = blasErrorCode;
  }
}
