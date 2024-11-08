package br.com.fiap.smarttraffic.service;

public interface PushNotificationService {
    void sendPushNotification(String message, String deviceId);
}