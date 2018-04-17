package com.appian.microservices.order;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
  private String userId;
  private List<OrderItem> items;

  public ShoppingCart() {
    this.userId = "";
    this.items = new ArrayList<>();
  }

  public ShoppingCart(String userId, List<OrderItem> items) {
    this.userId = userId;
    this.items = new ArrayList<>(items);
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public List<OrderItem> getItems() {
    return new ArrayList<>(items);
  }

  public void setItems(List<OrderItem> items) {
    this.items = new ArrayList<>(items);
  }
}
