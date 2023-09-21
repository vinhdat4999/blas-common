package com.blas.blascommon.core.dao.mongodb;

import com.blas.blascommon.core.model.BlasResult;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasResultDao extends MongoRepository<BlasResult, String> {

  @Query("{" +
      "'export_time': {$gte: ?1, $lt: ?2}," +
      "'user_id': ?0" +
      "}")
  List<BlasResult> findByUserIdAndExportTime(String reportBy, LocalDateTime date1,
      LocalDateTime localDateTime2);
}
