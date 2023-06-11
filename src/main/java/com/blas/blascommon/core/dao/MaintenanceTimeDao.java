package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.MaintenanceTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTimeDao extends JpaRepository<MaintenanceTime, String> {

  @Query("SELECT m FROM MaintenanceTime m WHERE m.service = ?1")
  public MaintenanceTime getMaintenanceTimeByService(String serviceName);
}
