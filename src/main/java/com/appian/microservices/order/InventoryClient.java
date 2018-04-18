package com.appian.microservices.order;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("inventory")
public interface InventoryClient {

  @RequestMapping(method = RequestMethod.GET, value = "/products")
  List<Inventory> getAllProducts();
}
