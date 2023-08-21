package com.blas.blascommon.exceptions.types;

import com.blas.blascommon.exceptions.BlasErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BadGatewayException extends RuntimeException {

  private final BlasErrorCode blasErrorCode;

  public BadGatewayException(BlasErrorCode blasErrorCode) {
    super(blasErrorCode.getMessageDescription());
    this.blasErrorCode = blasErrorCode;
  }

  public BadGatewayException(BlasErrorCode blasErrorCode, String message) {
    super(message);
    this.blasErrorCode = blasErrorCode;
  }

  public BadGatewayException(BlasErrorCode blasErrorCode, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCode = blasErrorCode;
  }
}
