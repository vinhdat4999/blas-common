package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.NOTIFICATION_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.jpa.AuthUserDao;
import com.blas.blascommon.core.dao.jpa.NotificationDao;
import com.blas.blascommon.core.model.Notification;
import com.blas.blascommon.core.service.NotificationService;
import com.blas.blascommon.exceptions.types.NotFoundException;
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
  private final AuthUserDao authUserDao;

  @Lazy
  private final NotificationDao notificationDao;

  @Override
  public List<Notification> getAllNotificationByUser(String userId) {
    if (authUserDao.existsById(userId)) {
      return notificationDao.getAllNotificationByUser(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public int getNumberOfUnreadNotificationByUser(String userId) {
    if (authUserDao.existsById(userId)) {
      return notificationDao.getNumberOfUnreadNotificationByUser(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public Notification createNotification(Notification notification) {
    notification.setNofiticationId(genUUID());
    return notificationDao.save(notification);
  }

  @Override
  public void updateNotification(Notification notification) {
    if (notificationDao.existsById(notification.getNofiticationId())) {
      notificationDao.save(notification);
    }
    throw new NotFoundException(NOTIFICATION_ID_NOT_FOUND);
  }

  @Override
  public void deletePhysicalNotification(String notificationId) {
    notificationDao.delete(notificationDao.findById(notificationId)
        .orElseThrow(() -> new NotFoundException(NOTIFICATION_ID_NOT_FOUND)));
  }
}
