package com.blas.blascommon.core.service.impl;

import com.blas.blascommon.core.dao.MaintenanceTimeDao;
import com.blas.blascommon.core.model.MaintenanceTime;
import com.blas.blascommon.core.service.MaintenanceTimeService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class MaintenanceTimeServiceImpl implements MaintenanceTimeService {

  @Lazy
  private final MaintenanceTimeDao maintenanceTimeDao;

  public MaintenanceTimeServiceImpl(MaintenanceTimeDao maintenanceTimeDao) {
    this.maintenanceTimeDao = maintenanceTimeDao;
  }

  @Override
  public MaintenanceTime getMaintenanceTimeByService(String serviceName) {
    return maintenanceTimeDao.getMaintenanceTimeByService(serviceName);
  }
}
