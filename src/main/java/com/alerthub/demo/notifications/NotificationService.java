
package com.alerthub.demo.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void addNotification(String uid, String description) {
        if (uid == null || description == null) {
            throw new IllegalArgumentException("Description and User Id cannot be null");
        }

        Notifications notifications;
        final Optional<Notifications> optionalData = notificationRepository.findByUid(uid);
        if (optionalData.isPresent()) {

            notifications = optionalData.get();
        } else {

            final List<Notification> notication = new ArrayList<>();
            notifications = new Notifications(null, uid, notication);
        }

        Notification notification = new Notification(description, System.currentTimeMillis());

        notifications.getNotifications().add(0, notification);

        notificationRepository.save(notifications);

    }

    public Notifications getNotifications(String uid) {

        if (uid == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        final Optional<Notifications> optionalData = notificationRepository.findByUid(uid);
        if (optionalData.isPresent()) {
            return optionalData.get();
        } else {
            final List<Notification> notication = List.of();
            return new Notifications(null, uid, notication);
        }

    }

    public void deleteNotifications(String uid) {
        if (uid == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        Optional<Notifications> optionalNotification = notificationRepository.findByUid(uid);
        if (optionalNotification.isPresent()) {
            Notifications notification = optionalNotification.get();
            notificationRepository.deleteById(notification.getId());

        } else {
            throw new IllegalStateException("Notification not found");
        }
    }

}