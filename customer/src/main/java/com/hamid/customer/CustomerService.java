package com.hamid.customer;

import com.hamid.amqp.RabbitMQMessageProducer;
import com.hamid.clients.fraud.FraudCheckResponse;
import com.hamid.clients.fraud.FraudClient;
import com.hamid.clients.notification.NotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final FraudClient fraudClient;
  private final RabbitMQMessageProducer rabbitMQMessageProducer;

  public void register(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

    customerRepository.saveAndFlush(customer);

    FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

    if (fraudCheckResponse.isFraudster()) {
      throw new IllegalStateException("Fraudster!");
    }

    NotificationRequest notificationRequest =
        new NotificationRequest(
            customer.getId(),
            customer.getEmail(),
            String.format("Hi %s, welcome to this project", customer.getFirstName()));

    rabbitMQMessageProducer.publish(
        notificationRequest, "internal.exchange", "internal.notification.routing-key");
  }
}
