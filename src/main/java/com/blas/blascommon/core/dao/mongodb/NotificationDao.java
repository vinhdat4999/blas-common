package com.blas.blascommon.core.dao.mongodb;

import com.blas.blascommon.core.model.Notification;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends MongoRepository<Notification, String> {

  @Query("{" +
      "'receiver_username': ?0" +
      "}")
  List<Notification> getAllNotificationByUsername(@Param("receiver_username") String username);
}
