package com.appian.microservices.order;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.oracle.tools.packager.Log;

import feign.Feign;
import feign.jackson.JacksonDecoder;

@SpringBootApplication
@RestController
@EnableFeignClients
public class OrderApplication extends WebMvcConfigurerAdapter {

  @Autowired
  private OrderService orderService;

  @Autowired
  private CorrelationIdFilter correlationIdFilter;

  private final Logger LOG = LoggerFactory.getLogger(OrderApplication.class);
  private static InventoryClient inventoryClient;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(correlationIdFilter);
  }

  @RequestMapping(value = "/orders")
  public @ResponseBody List<Order> list() {
    LOG.info("Received request at /orders endpoint");
    List<Inventory> inventoryList = inventoryClient.getAllProducts();
    LOG.info("Inventory has " + inventoryList.size() + " products");
    return orderService.getAllOrders();
  }

  @RequestMapping(method = RequestMethod.POST, value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
  public @ResponseBody Order newOrder(@RequestBody ShoppingCart shoppingCart) {
    return orderService.create(shoppingCart);
  }

  public static void main(String[] args) {
    inventoryClient = Feign.builder()
                        .contract(new SpringMvcContract())
                        .decoder(new JacksonDecoder())
                        .target(InventoryClient.class, "http://localhost:8081");
    SpringApplication.run(OrderApplication.class, args);
  }
}
