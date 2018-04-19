package com.appian.microservices.order;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum InventoryUpdateRequestType {

  INCREASE, DECREASE;

  @JsonCreator
  public static InventoryUpdateRequestType fromText(String text){
    return InventoryUpdateRequestType.valueOf(text.toUpperCase());
  }

  @Override
  public String toString() {
    return this.name().toLowerCase();
  }
}
