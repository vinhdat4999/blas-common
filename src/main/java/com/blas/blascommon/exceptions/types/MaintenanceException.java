package com.blas.blascommon.exceptions.types;

import com.blas.blascommon.payload.MaintenanceTimeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MaintenanceException extends RuntimeException {

  private MaintenanceTimeResponse maintenanceTimeResponse;

  public MaintenanceException(MaintenanceTimeResponse maintenanceTimeResponse, Throwable cause) {
    super(maintenanceTimeResponse.getNotificationMessage(), cause);
    this.maintenanceTimeResponse = maintenanceTimeResponse;
  }

  public MaintenanceException(MaintenanceTimeResponse maintenanceTimeResponse) {
    super(maintenanceTimeResponse.getNotificationMessage());
    this.maintenanceTimeResponse = maintenanceTimeResponse;
  }

  public MaintenanceException(Throwable cause) {
    super(cause);
  }
}
