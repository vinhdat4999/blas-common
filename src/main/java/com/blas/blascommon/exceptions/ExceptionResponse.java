package com.blas.blascommon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionResponse {

  private int status;
  private String message;
  private BlasErrorCode blasErrorCode;
  private long timeStamp;

  @Override
  public String toString() {
    return "ExceptionResponse{" +
        "status=" + status +
        ", message='" + message + '\'' +
        ", blasErrorCode=" + blasErrorCode +
        ", timeStamp=" + timeStamp +
        '}';
  }
}
