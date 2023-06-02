package com.blas.blascommon.exceptions.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class NotAcceptableException extends RuntimeException {

  private final String transactionId;

  public NotAcceptableException(final String transactionId, final String message,
      final Throwable cause) {
    super(message, cause);
    this.transactionId = transactionId;
  }

  public NotAcceptableException(final String transactionId, final String message) {
    super(message);
    this.transactionId = transactionId;
  }

  public NotAcceptableException(final String transactionId, final Throwable cause) {
    super(cause);
    this.transactionId = transactionId;
  }
}
