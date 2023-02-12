package com.hamid.notification;

import com.hamid.clients.notification.NotificationRequest;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

  private final NotificationRepository notificationRepository;

  public void send(NotificationRequest notificationRequest) {
    notificationRepository.save(
        Notification.builder()
            .toCustomerId(notificationRequest.toCustomerId())
            .toCustomerEmail(notificationRequest.toCustomerEmail())
            .sender("Hamid")
            .message(notificationRequest.message())
            .sentAt(LocalDateTime.now())
            .build());
  }
}
