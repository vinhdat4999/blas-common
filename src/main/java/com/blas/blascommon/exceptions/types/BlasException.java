package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCode.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlasException extends RuntimeException {

  private BlasErrorCode blasErrorCode;

  public BlasException(BlasErrorCode blasErrorCode) {
    super(blasErrorCode.getMessageDescription());
    this.blasErrorCode = blasErrorCode;
  }

  public BlasException(String message) {
    super(message);
    this.blasErrorCode = MSG_UNKNOWN_ERROR;
  }

  public BlasException(BlasErrorCode blasErrorCode, String message) {
    super(message);
    this.blasErrorCode = blasErrorCode;
  }

  public BlasException(BlasErrorCode blasErrorCode, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCode = blasErrorCode;
  }
}
