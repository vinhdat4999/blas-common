package com.blas.blascommon.exceptions.types;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String message, Throwable cause) {
    super(message, cause);
  }

  public BadRequestException(String message) {
    super(message);
  }

  public BadRequestException(Throwable cause) {
    super(cause);
  }
}
