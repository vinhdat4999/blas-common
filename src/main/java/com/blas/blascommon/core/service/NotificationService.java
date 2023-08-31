package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Notification;
import java.util.List;

public interface NotificationService {

  List<Notification> getAllNotificationByUser(String userId);

  int getNumberOfUnreadNotificationByUser(String userId);

  Notification createNotification(Notification notification);

  void updateNotification(Notification notification);

  void deletePhysicalNotification(String notificationId);
}
