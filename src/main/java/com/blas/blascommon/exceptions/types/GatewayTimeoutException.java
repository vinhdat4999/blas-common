package com.blas.blascommon.exceptions.types;

public class GatewayTimeoutException extends RuntimeException {

  public GatewayTimeoutException(String message, Throwable cause) {
    super(message, cause);
  }

  public GatewayTimeoutException(String message) {
    super(message);
  }

  public GatewayTimeoutException(Throwable cause) {
    super(cause);
  }
}
