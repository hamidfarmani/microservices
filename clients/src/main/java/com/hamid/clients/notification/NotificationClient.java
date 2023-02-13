package com.hamid.clients.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
    name = "notification",
    url = "${clients.notification.url}"
)
public interface NotificationClient {
  @GetMapping(path = "api/v1/notification")
  void sendNotification(NotificationRequest notificationRequest);
}
