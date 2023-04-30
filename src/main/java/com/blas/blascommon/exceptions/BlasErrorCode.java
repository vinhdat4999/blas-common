package com.blas.blascommon.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus.Series;

@Getter
@AllArgsConstructor
public enum BlasErrorCode {
  POTENTIAL_DANGER_REQUEST(460, Series.CLIENT_ERROR, "Potential security risk detected"),
  CARD_EXCEPTION(460, Series.CLIENT_ERROR, "Potential security risk detected"),
  BLAS_SYSTEM_FAILURE(520, Series.SERVER_ERROR, "Blas system failure");

  private final int errorCode;
  private final Series series;
  private final String errorMessage;
}
