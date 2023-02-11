package com.hamid.customer;

import org.springframework.stereotype.Service;

@Service
public record CustomerService() {

  public void register(CustomerRegistrationRequest request) {
    Customer customer = Customer.builder()
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .build();
  }
}
