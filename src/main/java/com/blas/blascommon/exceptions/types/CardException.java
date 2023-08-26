package com.blas.blascommon.exceptions.types;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CardException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  private final String transactionId;

  public CardException(final BlasErrorCodeEnum blasErrorCodeEnum, final String transactionId,
      final String message, final Throwable cause) {
    super(message, cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.transactionId = transactionId;
  }

  public CardException(final BlasErrorCodeEnum blasErrorCodeEnum, final String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.transactionId = null;
  }

  public CardException(final BlasErrorCodeEnum blasErrorCodeEnum, final String transactionId,
      final String message) {
    super(message);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.transactionId = transactionId;
  }

  public CardException(final BlasErrorCodeEnum blasErrorCodeEnum, final String transactionId,
      final Throwable cause) {
    super(cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.transactionId = transactionId;
  }
}
