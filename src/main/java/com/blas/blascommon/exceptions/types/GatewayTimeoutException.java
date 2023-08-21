package com.blas.blascommon.exceptions.types;

import static com.blas.blascommon.exceptions.BlasErrorCode.MSG_UNKNOWN_ERROR;

import com.blas.blascommon.exceptions.BlasErrorCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class GatewayTimeoutException extends RuntimeException {

  private final BlasErrorCode blasErrorCode;

  public GatewayTimeoutException(BlasErrorCode blasErrorCode) {
    super(blasErrorCode.getMessageDescription());
    this.blasErrorCode = blasErrorCode;
  }

  public GatewayTimeoutException(String message) {
    super(message);
    this.blasErrorCode = MSG_UNKNOWN_ERROR;
  }

  public GatewayTimeoutException(BlasErrorCode blasErrorCode, String message) {
    super(message);
    this.blasErrorCode = blasErrorCode;
  }

  public GatewayTimeoutException(BlasErrorCode blasErrorCode, String message, Throwable cause) {
    super(message, cause);
    this.blasErrorCode = blasErrorCode;
  }
}
