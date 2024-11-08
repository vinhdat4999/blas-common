package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.mongodb.NotificationDao;
import com.blas.blascommon.core.model.Notification;
import com.blas.blascommon.core.service.NotificationService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class NotificationServiceImpl implements NotificationService {


  @Lazy
  private final MongoTemplate mongoTemplate;

  @Lazy
  private final NotificationDao notificationDao;

  @Override
  public List<Notification> getAllNotificationByUsername(String username) {
    return notificationDao.getAllNotificationByUsername(username);
  }

  @Override
  public Notification getNotificationById(String notificationId) {
    return notificationDao.getNotificationByNotificationId(notificationId);
  }

  @Override
  public void deleteNotificationById(String notificationId) {
    notificationDao.deleteById(notificationId);
  }

  @Override
  public Notification save(Notification notification) {
    notification.setNotificationId(genUUID());
    return notificationDao.save(notification);
  }

  @Override
  public void updateReadNotification(String notificationId) {
    Query query = new Query(Criteria.where("notification_id").is(notificationId));

    Update update = new Update()
        .set("is_read", true);

    mongoTemplate.updateFirst(query, update, Notification.class);
  }
}
