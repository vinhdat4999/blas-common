package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Notification;
import java.util.List;

public interface NotificationService {

  public List<Notification> getAllNotificationByUser(String userId);

  public int getNumberOfUnreadNotificationByUser(String userId);

  public Notification createNotification(Notification notification);

  public void updateNotification(Notification notification);

  public void deletePhysicalNotification(String notificationId);
}
