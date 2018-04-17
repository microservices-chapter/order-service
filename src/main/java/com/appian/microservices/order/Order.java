package com.appian.microservices.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "order")
public class Order {
  @Id
  private String orderId;
  private String customerId;
  private Date dateCreated;
  private Date dateUpdated;
  private List<OrderItem> orderItemList;

  public Order() {
    this.orderId = UUID.randomUUID().toString();
    this.dateCreated = new Date();
    this.customerId = null;
    this.dateUpdated = null;
    this.orderItemList = new ArrayList<>();
  }

  public Order(String customerId, List<OrderItem> orderItemList) {
    this.orderId = UUID.randomUUID().toString();
    this.customerId = customerId;
    this.orderItemList = new ArrayList<>(orderItemList);
    this.dateCreated = new Date();
    this.dateUpdated = null;
  }

  public String getOrderId() {
    return orderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }

  public Date getDateUpdated() {
    return dateUpdated;
  }

  public void setDateUpdated(Date dateUpdated) {
    this.dateUpdated = dateUpdated;
  }

  public List<OrderItem> getOrderItemList() {
    return new ArrayList<>(orderItemList);
  }

  public void setOrderItemList(List<OrderItem> orderItemList) {
    this.orderItemList = new ArrayList<>(orderItemList);
  }
}
