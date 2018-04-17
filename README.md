
# Order Service #

## API ##

| METHOD | PATH | Accept | Body | DESCRIPTION |
| ------ |----- | ------ |----- | ----------- |
| **GET**    | orders |        |      | List all orders |
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
