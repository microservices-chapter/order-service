package com.appian.microservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Inventory application.
 *
 * @author touré
 */
@SpringBootApplication
@RestController
public class OrderApplication {

  @RequestMapping(value = "/orders")
  public String list() {
    return "all the customer's orders";
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }
}
