
# Order Service #

## API ##

| METHOD | PATH | Accept | Body | DESCRIPTION |
| ------ |----- | ------ |----- | ----------- |
| **GET**    | orders |        |      | List all orders |
| **GET**    | orders/*{orderId}* |        |      | Get details of a order |
| **POST** | create | application/json | {"userId": "1", "items": [ {"uuid": "abc", "quantity": 23}, {"uuid": "def", "quantity": 16} ]} | Create a new order |

## Testing Locally ##

Run Mongo DB:

docker run --name mongodb -p 27017:27017 mongo

To test order creation, here is an example call to the 'create' API':

```
curl -X POST -H "Content-Type: application/json" \
-d '{"userId": "1", "items": [ {"uuid": "abc", "quantity": 23}, {"uuid": "def", "quantity": 16} ]}' \
http://localhost:8099/create
```

To test persistence (this will return all orders):

```
curl -X GET http://localhost:8099/orders
```

To insert a product into the Inventory Service:

```
curl -X PUT -H "Content-Type: application/json" -d '{"sku": "123-foo", "quantity": 12, "type": "increase"}' http://localhost:8081/products
```

When running in Docker:

Test Order Service:
curl -X GET http://localhost:8080/order/orders

Test Order Service Hystrix Stream in a browser:
http://localhost:8080/order/hystrix.stream

Test the monitoring dashboard:
http://localhost:9000/hystrix

Enter the following in the stream: http://api-gateway:8080/order/hystrix.stream

