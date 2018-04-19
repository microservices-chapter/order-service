package com.appian.microservices.order;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InventoryStatus {
  ACTIVE, INACTIVE;

  @JsonCreator
  public static InventoryStatus fromText(String text){
    return InventoryStatus.valueOf(text.toUpperCase());
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
