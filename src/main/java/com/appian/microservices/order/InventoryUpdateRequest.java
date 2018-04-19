package com.appian.microservices.order;

public class InventoryUpdateRequest {
  private String sku;
  private int quantity;
  private InventoryUpdateRequestType type;

  public InventoryUpdateRequest(
      String sku, int quantity, InventoryUpdateRequestType type) {
    this.sku = sku;
    this.quantity = quantity;
    this.type = type;
  }

  public InventoryUpdateRequest() {
  }

  public String getSku() {
    return sku;
  }

  public void setSku(String sku) {
    this.sku = sku;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public InventoryUpdateRequestType getType() {
    return type;
  }

  public void setType(InventoryUpdateRequestType type) {
    this.type = type;
  }
}
