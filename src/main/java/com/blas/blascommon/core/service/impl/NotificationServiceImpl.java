package com.blas.blascommon.core.service.impl;

import com.blas.blascommon.core.dao.mongodb.NotificationDao;
import com.blas.blascommon.core.model.Notification;
import com.blas.blascommon.core.service.NotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class NotificationServiceImpl implements NotificationService {

  @Lazy
  private final NotificationDao notificationDao;

  @Override
  public List<Notification> getAllNotificationByUsername(String username) {
    return notificationDao.getAllNotificationByUsername(username);
  }

  @Override
  public Notification getNotificationById(String notificationId) {
    return notificationDao.findById(notificationId).orElse(null);
  }

  @Override
  public void deleteNotificationById(String notificationId) {
    notificationDao.deleteById(notificationId);
  }

  @Override
  public Notification save(Notification notification) {
    notification.setNotificationId(null);
    return notificationDao.save(notification);
  }
}
