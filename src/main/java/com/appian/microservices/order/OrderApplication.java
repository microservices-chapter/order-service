package com.appian.microservices.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inventory application.
 *
 * @author touré
 */
@SpringBootApplication
@RestController
public class OrderApplication {

  private static Logger LOG = LoggerFactory.getLogger(OrderApplication.class);

  @RequestMapping(value = "/orders")
  public String list() {
    LOG.info("Received request at /orders endpoint");
    return "all the customer's orders";
  }

  public static void main(String[] args) {
    SpringApplication.run(OrderApplication.class, args);
  }
}
