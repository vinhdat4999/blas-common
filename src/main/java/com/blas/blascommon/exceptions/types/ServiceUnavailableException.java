package com.blas.blascommon.exceptions.types;

public class ServiceUnavailableException extends RuntimeException {

  public ServiceUnavailableException(String message, Throwable cause) {
    super(message, cause);
  }

  public ServiceUnavailableException(String message) {
    super(message);
  }

  public ServiceUnavailableException(Throwable cause) {
    super(cause);
  }
}