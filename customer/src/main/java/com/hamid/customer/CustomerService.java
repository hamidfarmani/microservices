package com.hamid.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final RestTemplate restTemplate;

  public void register(CustomerRegistrationRequest request) {
    Customer customer =
        Customer.builder()
            .firstName(request.firstName())
            .lastName(request.lastName())
            .email(request.email())
            .build();

    customerRepository.saveAndFlush(customer);

    FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
        "http://localhost:8081/api/v1/fraud-check/{customerId}",
        FraudCheckResponse.class,
        customer.getId());

    if(fraudCheckResponse.isFraudster()){
      throw new IllegalStateException("Fraudster!");
    }
  }
}
