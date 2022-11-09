package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.Notification;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationDao extends JpaRepository<Notification, String> {

  @Query("SELECT n FROM Notification n WHERE n.userDetail.userId = ?1")
  public List<Notification> getAllNotificationByUser(String userId);

  @Query("SELECT COUNT(1) FROM Notification n WHERE n.userDetail.userId = ?1 AND n.isRead = false")
  public int getNumberOfUnreadNotificationByUser(String userId);
}
