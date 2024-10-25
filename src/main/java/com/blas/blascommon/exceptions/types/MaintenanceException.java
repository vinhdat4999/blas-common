package com.blas.blascommon.exceptions.types;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import com.blas.blascommon.payload.MaintenanceTimeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaintenanceException extends RuntimeException {

  private final BlasErrorCodeEnum blasErrorCodeEnum;

  private final transient MaintenanceTimeResponse maintenanceTimeResponse;

  public MaintenanceException(final BlasErrorCodeEnum blasErrorCodeEnum) {
    super(blasErrorCodeEnum.getMessageDescription());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.maintenanceTimeResponse = null;
  }

  public MaintenanceException(final BlasErrorCodeEnum blasErrorCodeEnum,
      MaintenanceTimeResponse maintenanceTimeResponse, Throwable cause) {
    super(maintenanceTimeResponse.getNotificationMessage(), cause);
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.maintenanceTimeResponse = maintenanceTimeResponse;
  }

  public MaintenanceException(final BlasErrorCodeEnum blasErrorCodeEnum,
      MaintenanceTimeResponse maintenanceTimeResponse) {
    super(maintenanceTimeResponse.getNotificationMessage());
    this.blasErrorCodeEnum = blasErrorCodeEnum;
    this.maintenanceTimeResponse = maintenanceTimeResponse;
  }
}
