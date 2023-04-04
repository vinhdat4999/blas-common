package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCode.BLAS_SYSTEM_FAILURE;

import com.blas.blascommon.exceptions.BlasErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BlasException extends RuntimeException {

  private BlasErrorCode blasErrorCode;

  public BlasException(String message, Throwable cause) {
    super(message, cause);
  }

  public BlasException() {
    super(BLAS_SYSTEM_FAILURE.getErrorMessage());
    this.blasErrorCode = BLAS_SYSTEM_FAILURE;
  }

  public BlasException(BlasErrorCode blasErrorCode) {
    super(blasErrorCode.getErrorMessage());
    this.blasErrorCode = blasErrorCode;
  }

  public BlasException(Throwable cause) {
    super(cause);
  }
}
