package br.com.fiap.smarttraffic.controller;

import br.com.fiap.smarttraffic.model.Notification;
import br.com.fiap.smarttraffic.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody Notification notification) {
        // Assuming the request body directly contains the notification details
        notificationService.sendNotification(notification);
        return ResponseEntity.ok("Notification sent successfully");
    }
}