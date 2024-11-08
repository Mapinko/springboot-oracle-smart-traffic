package br.com.fiap.smarttraffic.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("test")
public class MockPushNotificationService implements PushNotificationService {

    @Override
    public void sendPushNotification(String message, String deviceId) {
        System.out.println("Sending push notification to device ID: " + deviceId);
        System.out.println("Notification message: " + message);
    }
}