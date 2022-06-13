package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.NOTIFICATION_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.NotificationDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.Notification;
import com.blas.blascommon.core.service.NotificationService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private NotificationDao notificationDao;

    @Override
    public List<Notification> getAllNotificationByUser(String userId) {
        Optional<AuthUser> authUser = authUserDao.findById(userId);
        if (authUser.isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return notificationDao.getAllNotificationByUser(userId);
    }

    @Override
    public int getNumberOfUnreadNotificationByUser(String userId) {
        Optional<AuthUser> authUser = authUserDao.findById(userId);
        if (authUser.isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return notificationDao.getNumberOfUnreadNotificationByUser(userId);
    }

    @Override
    public Notification createNotification(Notification notification) {
        return notificationDao.save(notification);
    }

    @Override
    public void updateNotification(Notification notification) {
        notificationDao.save(notification);
    }

    @Override
    public void deletePhysicalNotification(String notificationId) {
        Optional<Notification> notification = notificationDao.findById(notificationId);
        if (notification.isEmpty()) {
            throw new NotFoundException(NOTIFICATION_ID_NOT_FOUND);
        }
        notificationDao.delete(notification.get());
    }
}
