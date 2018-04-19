package com.appian.microservices.order;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("inventory")
public interface InventoryClient {

  @RequestMapping(method = RequestMethod.GET, value = "/products")
  List<Inventory> getAllProducts();

  @RequestMapping(method = RequestMethod.PUT, value = "/products", consumes = MediaType.APPLICATION_JSON_VALUE)
  Inventory updateProductQty(InventoryUpdateRequest inventoryUpdateRequest);

  @RequestMapping(method = RequestMethod.GET, value = "/products/{sku}")
  Inventory getProductDetails(@PathVariable("sku") String sku);

}
