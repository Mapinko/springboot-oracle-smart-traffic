package br.com.fiap.smarttraffic.service;

import br.com.fiap.smarttraffic.model.Notification;
import br.com.fiap.smarttraffic.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private PushNotificationService pushNotificationService;

    public void sendNotification(Notification notification) {
        User user = notification.getUser();

        if (user.isPushNotificationEnabled()) {
            sendPushNotification(notification, user);
        } else {
            // Log or handle the case where push notifications are disabled
            System.out.println("Push notifications are disabled for user: " + user.getUsername());
        }
    }

    private void sendPushNotification(Notification notification, User user) {
        String message = notification.getMessage();
        String deviceId = user.getDeviceId();  // Assuming you have a deviceId field in User

        pushNotificationService.sendPushNotification(message, deviceId);
    }
}