package com.blas.blascommon.exceptions.types;

public class UnauthorizedException extends RuntimeException {

  public UnauthorizedException(String message, Throwable cause) {
    super(message, cause);
  }

  public UnauthorizedException(String message) {
    super(message);
  }

  public UnauthorizedException(Throwable cause) {
    super(cause);
  }
}
