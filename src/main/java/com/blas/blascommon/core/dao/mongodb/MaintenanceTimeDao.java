package com.blas.blascommon.core.dao.mongodb;

import com.blas.blascommon.core.model.MaintenanceTime;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaintenanceTimeDao extends MongoRepository<MaintenanceTime, String> {

  MaintenanceTime findMaintenanceTimeByService(String service);
}
