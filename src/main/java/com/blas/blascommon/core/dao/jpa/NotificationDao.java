package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends JpaRepository<Notification, String> {

  @Query("SELECT n FROM Notification n WHERE n.userDetail.userId = :userId")
  public List<Notification> getAllNotificationByUser(@Param("userId") String userId);

  @Query("SELECT COUNT(1) FROM Notification n WHERE n.userDetail.userId = :userId AND n.isRead = false")
  public int getNumberOfUnreadNotificationByUser(@Param("userId") String userId);
}
