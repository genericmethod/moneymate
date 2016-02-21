# moneymate
MoneyMate - A Rest API for money transfers between internal users and accounts

# Endpoints

#### Account Resources

- GET     /v1/accounts

##### Example

##### Request

GET /v1/accounts

##### Response
```
[
  {
    "id": 1,
    "username": "chris",
    "description": "salary account",
    "balance": 888,
    "currency": "EUR"
  },
  {
    "id": 2,
    "username": "mark",
    "description": "salary account",
    "balance": 888,
    "currency": "EUR"
  }
]
```

- POST    /v1/accounts

##### Example

###### Request

POST    /v1/accounts

Body

```
{
  "username":"vlad",
  "description":"description",
  "balance":123.00,
  "currency":"EUR"
}
```

###### Response

```
{
  "username":"vlad",
  "description":"description",
  "balance":123.00,
  "currency":"EUR"
}
```

- GET     /v1/accounts/{id}

##### Example
###### Request

GET /v1/accounts/1

###### Response

```
{
  "username":"vlad",
  "description":"description",
  "balance":123.00,
  "currency":"EUR"
}
```

- PUT     /v1/accounts/{id}

##### Example

###### Request

PUT /v1/accounts/{id}

Body
```
{
  "id":1,
  "username":"vlad",
  "description":"updated description",
  "balance":123.00,
  "currency":"EUR"
}
```

###### Response

```
{
  "id":1,
  "username":"vlad",
  "description":"updated description",
  "balance":123.00,
  "currency":"EUR"
}
```

- GET     /v1/accounts/{id}/balance

##### Example

###### Request

GET /v1/accounts/1/balance

###### Response

```
{
  "amount": 123,
  "currency": "EUR"
}
```

- DELETE  /v1/accounts/{username}

##### Example
###### Request

DELETE  /v1/accounts/vlad

###### Response

STATUS 204 OK

- PUT     /v1/accounts/{username}/withdraw

##### Example

###### Request

PUT /v1/accounts/vlad/withdraw

Body
```
{
  "amount": 10,
  "currency": "EUR"
}
```
###### Response

```
{
  "id": 1,
  "username": "vlad",
  "description": "description",
  "balance": 113,
  "currency": "EUR"
}
```



- PUT     /v1/accounts/{username}/deposit

##### Example

###### Request

PUT /v1/accounts/vlad/deposit

Body
```
{
  "amount": 10,
  "currency": "EUR"
}
```
###### Response

```
{
  "id": 2,
  "username": "mark",
  "description": "salary account",
  "balance": 1031.22,
  "currency": "EUR"
}
```


#### User Resources

- GET     /v1/users

##### Example
###### Request
###### Response

- POST    /v1/users

##### Example
###### Request
###### Response

- DELETE  /v1/users/{id}

##### Example
###### Request
###### Response

- PUT     /v1/users/{id}

##### Example
###### Request
###### Response

- GET     /v1/users/{username}

##### Example
###### Request
###### Response

- GET     /v1/users/{username}/account

##### Example
###### Request
###### Response


#### Transferring Money

- POST    /v1/transfers

##### Example
###### Request
###### Response





