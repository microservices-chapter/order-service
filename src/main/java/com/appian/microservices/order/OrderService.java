package com.appian.microservices.order;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Transactional
  public Order create(ShoppingCart shoppingCart) {
    Order order = new Order(shoppingCart.getUserId(), shoppingCart.getItems());
    return orderRepository.save(order);
  }

  @Transactional
  public List<Order> getAllOrders() {
    return new ArrayList<>(orderRepository.findAll());
  }
}
