
package com.alerthub.demo.notifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alerthub.demo.NetworkResult;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(path = "api/v1/notification")
@Tag(name = "Notification", description = "Notification APIs")
public class NotificationController {
    private final String fetchSuccessful = "Operation Successful";

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping(path = "get_notifications/{uid}")
    @Operation(summary = "Get user notifications", description = "")
    public ResponseEntity<NetworkResult> getNotifications(@PathVariable String uid) {
        Notifications notifications = notificationService.getNotifications(uid);
        NetworkResult result = new NetworkResult(fetchSuccessful, notifications);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "clear_notifications/{uid}")
    @Operation(summary = "Clear notifications", description = "")
    public ResponseEntity<NetworkResult> deleteNotification(@PathVariable String uid) {
        try {
            notificationService.deleteNotifications(uid);
            NetworkResult result = new NetworkResult(fetchSuccessful, null);
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception exception) {
            return new ResponseEntity<>(new NetworkResult(exception.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
