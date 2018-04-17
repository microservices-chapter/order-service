package com.appian.microservices.order;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
  Order findByOrderId(String orderId);
}
