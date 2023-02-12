package com.hamid.notification;

import com.hamid.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/notification")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

  private final NotificationService notificationService;

  @PostMapping
  public void sendNotification(@RequestBody NotificationRequest notificationRequest){
    log.info("New notification {}", notificationRequest);
    notificationService.send(notificationRequest);
  }
}
