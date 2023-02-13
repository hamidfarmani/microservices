package com.hamid.fraud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@EnableDiscoveryClient
@PropertySources({
    @PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class FraudApplication {

  public static void main(String[] args) {
    SpringApplication.run(FraudApplication.class, args);
  }
}
