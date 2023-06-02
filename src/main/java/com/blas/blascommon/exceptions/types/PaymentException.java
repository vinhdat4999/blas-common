package com.blas.blascommon.exceptions.types;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PaymentException extends RuntimeException {

  private final String transactionId;

  public PaymentException(final String transactionId, final String message,
      Throwable cause) {
    super(message, cause);
    this.transactionId = transactionId;
  }

  public PaymentException(final String transactionId, final String message) {
    super(message);
    this.transactionId = transactionId;
  }

  public PaymentException(final String transactionId, final Throwable cause) {
    super(cause);
    this.transactionId = transactionId;
  }
}
