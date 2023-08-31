package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.MaintenanceTime;

public interface MaintenanceTimeService {

  MaintenanceTime getMaintenanceTimeByService(String serviceName);
}
