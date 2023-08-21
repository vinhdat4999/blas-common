package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.NOTIFICATION_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.NotificationDao;
import com.blas.blascommon.core.model.Notification;
import com.blas.blascommon.core.service.NotificationService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class NotificationServiceImpl implements NotificationService {

  @Lazy
  private final AuthUserDao authUserDao;

  @Lazy
  private final NotificationDao notificationDao;

  public NotificationServiceImpl(AuthUserDao authUserDao, NotificationDao notificationDao) {
    this.authUserDao = authUserDao;
    this.notificationDao = notificationDao;
  }

  @Override
  public List<Notification> getAllNotificationByUser(String userId) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return notificationDao.getAllNotificationByUser(userId);
  }

  @Override
  public int getNumberOfUnreadNotificationByUser(String userId) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return notificationDao.getNumberOfUnreadNotificationByUser(userId);
  }

  @Override
  public Notification createNotification(Notification notification) {
    notification.setNofiticationId(genUUID());
    return notificationDao.save(notification);
  }

  @Override
  public void updateNotification(Notification notification) {
    notificationDao.findById(notification.getNofiticationId())
        .orElseThrow(() -> new NotFoundException(NOTIFICATION_ID_NOT_FOUND));
    notificationDao.save(notification);
  }

  @Override
  public void deletePhysicalNotification(String notificationId) {
    notificationDao.delete(notificationDao.findById(notificationId)
        .orElseThrow(() -> new NotFoundException(NOTIFICATION_ID_NOT_FOUND)));
  }
}
