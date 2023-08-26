package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.MaintenanceTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTimeDao extends JpaRepository<MaintenanceTime, String> {

  @Query("SELECT m FROM MaintenanceTime m WHERE m.service = :serviceName")
  public MaintenanceTime getMaintenanceTimeByService(@Param("serviceName") String serviceName);
}
