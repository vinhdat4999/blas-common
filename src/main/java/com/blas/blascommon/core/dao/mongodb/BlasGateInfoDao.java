package com.blas.blascommon.core.dao.mongodb;

import com.blas.blascommon.core.model.BlasGateInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasGateInfoDao extends MongoRepository<BlasGateInfo, String> {

}
