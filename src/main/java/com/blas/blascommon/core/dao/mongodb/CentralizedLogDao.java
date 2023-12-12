package com.blas.blascommon.core.dao.mongodb;

import com.blas.blascommon.core.model.CentralizedLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentralizedLogDao extends MongoRepository<CentralizedLog, String> {

}
