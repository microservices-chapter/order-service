package com.appian.microservices.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  private final Logger LOG = LoggerFactory.getLogger(OrderService.class);

  @HystrixCommand (fallbackMethod = "fallback_emptyOrder")
  @Transactional
  public Order create(ShoppingCart shoppingCart, InventoryClient inventoryClient) {
    for (OrderItem orderItem : shoppingCart.getItems()) {
      LOG.info("Processing order item: " + orderItem.getUuid() + " for quantity: " + orderItem.getQuantity());

      Inventory productDetails = inventoryClient.getProductDetails(orderItem.getUuid());
      LOG.info("Quantity Before Order: " + productDetails.getQuantity());
      InventoryUpdateRequest inventoryUpdateRequest =
          new InventoryUpdateRequest(orderItem.getUuid(), orderItem.getQuantity(),
                InventoryUpdateRequestType.fromText("decrease"));
      inventoryClient.updateProductQty(inventoryUpdateRequest);

      productDetails = inventoryClient.getProductDetails(orderItem.getUuid());
      LOG.info("Quantity After Order: " + productDetails.getQuantity());
    }
    Order order = new Order(shoppingCart.getUserId(), shoppingCart.getItems());
    return orderRepository.save(order);
  }


  @HystrixCommand (fallbackMethod = "fallback_getAllOrders")
  @Transactional
  public List<Order> getAllOrders() {
    return new ArrayList<>(orderRepository.findAll());
  }

  @HystrixCommand (fallbackMethod = "fallback_emptyOrder")
  @Transactional
  public Order getOrderDetails(String orderId) {
    return orderRepository.findByOrderId(orderId);
  }

  private List<Order> fallback_getAllOrders() {
    return new ArrayList<>();
  }

  private Order fallback_emptyOrder(ShoppingCart shoppingCart, InventoryClient inventoryClient) {
    return new Order();
  }

  private Order fallback_emptyOrder(String orderId) {
    return new Order();
  }
}
