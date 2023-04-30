package com.blas.blascommon.exceptions.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotAcceptableException extends RuntimeException {

  private String transactionId;

  public NotAcceptableException(String transactionId, String message, Throwable cause) {
    super(message, cause);
    this.transactionId = transactionId;
  }

  public NotAcceptableException(String transactionId, String message) {
    super(message);
    this.transactionId = transactionId;
  }

  public NotAcceptableException(String transactionId, Throwable cause) {
    super(cause);
    this.transactionId = transactionId;
  }
}
