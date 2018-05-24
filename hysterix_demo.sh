#!/bin/bash

while true; do
  curl -X PUT -H "Content-Type: application/json" \
    -d '{"sku": "sku-123", "quantity": 10, "type": "increase"}' http://localhost:8080/inventory/products
  curl -X PUT -H "Content-Type: application/json" \
    -d '{"sku": "sku-345", "quantity": 20, "type": "increase"}' http://localhost:8080/inventory/products

  curl -X POST -H "Content-Type: application/json" \
    -d '{"userId": "1", "items": [ {"uuid": "sku-123", "quantity": 10}, {"uuid": "sku-345", "quantity": 20} ]}' \
    http://localhost:8080/order/create

  curl -X GET http://localhost:8080/order/orders
done
