package com.appian.microservices.order;

import java.util.UUID;

public class OrderItem {
  private String uuid;
  private int quantity;

  public OrderItem() {
    this.uuid = UUID.randomUUID().toString();
    this.quantity = 1;
  }

  public OrderItem(String uuid, int quantity) {
    if (quantity <= 0) {
      throw new RuntimeException("Quantity must be positive: " + quantity);
    }
    this.uuid = uuid;
    this.quantity = quantity;
  }

  public String getUuid() {
    return uuid;
  }

  public void setUuid(String uuid) {
    this.uuid = uuid;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    if (quantity <= 0) {
      throw new RuntimeException("Quantity must be positive: " + quantity);
    }
    this.quantity = quantity;
  }
}
