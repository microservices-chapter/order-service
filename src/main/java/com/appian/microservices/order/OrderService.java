package com.appian.microservices.order;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  private final Logger LOG = LoggerFactory.getLogger(OrderService.class);

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

  @Transactional
  public List<Order> getAllOrders() {
    return new ArrayList<>(orderRepository.findAll());
  }

  @Transactional
  public Order getOrderDetails(String orderId) {
    return orderRepository.findByOrderId(orderId);
  }
}
