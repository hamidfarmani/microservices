package com.hamid.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Notification {

  @Id
  @SequenceGenerator(name = "notification_id_sequence", sequenceName = "notification_id_sequence")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id_sequence")
  private Integer id;

  private Integer notificationId;
  private Integer toCustomerId;
  private String toCustomerEmail;
  private String sender;
  private String message;
  private LocalDateTime sentAt;


}
