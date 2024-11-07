package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Notification;
import java.util.List;

public interface NotificationService {

  List<Notification> getAllNotificationByUsername(String username);

  Notification getNotificationById(String notificationId);

  void deleteNotificationById(String notificationId);

  Notification save(Notification notification);
}
