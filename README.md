# moneymate
MoneyMate - A Rest API for money transfers between internal users and accounts

# Endpoints

```
- GET     /v1/users
- POST    /v1/users
- DELETE  /v1/users/{id}
- PUT     /v1/users/{id}
- GET     /v1/users/{username}
- GET     /v1/users/{username}/account
- GET     /v1/accounts
- POST    /v1/accounts
- GET     /v1/accounts/{id}
- PUT     /v1/accounts/{id}
- GET     /v1/accounts/{id}/balance
- DELETE  /v1/accounts/{username}
- PUT     /v1/accounts/{username}/deposit
- PUT     /v1/accounts/{username}/withdraw
- POST    /v1/transfers
```
## Usage

```
git clone https://github.com/genericmethod/moneymate.git
```

```
mvn package
```

```
java -jar target/moneymate-api-1.0.0-SNAPSHOT.jar server money-mate.yml
```

## Account Resources

## GET /v1/accounts

##### Example

##### Request

```
GET /v1/accounts
```

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

## POST    /v1/accounts

##### Example

###### Request

```
POST /v1/accounts
```

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

## GET     /v1/accounts/{id}

##### Example
###### Request

```
GET /v1/accounts/1
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

## PUT     /v1/accounts/{id}

##### Example

###### Request

```
PUT /v1/accounts/{id}
```

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

## GET     /v1/accounts/{id}/balance

##### Example

###### Request

```
GET /v1/accounts/1/balance
```

###### Response

```
{
  "amount": 123,
  "currency": "EUR"
}
```

## DELETE  /v1/accounts/{username}

##### Example
###### Request

```
DELETE  /v1/accounts/vlad
```

###### Response

STATUS 204 OK

## PUT     /v1/accounts/{username}/withdraw

##### Example

###### Request

```
PUT /v1/accounts/vlad/withdraw
```

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

## PUT     /v1/accounts/{username}/deposit

##### Example

###### Request

```
PUT /v1/accounts/vlad/deposit
```

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


## User Resources

## GET /v1/users

##### Example
###### Request

```
GET /v1/users
```

###### Response

```
[
  {
    "id": 1,
    "username": "vlad",
    "email": "vlad@gmail.com"
  },
  {
    "id": 2,
    "username": "nik",
    "email": "nik@gmail.com"
  }
]
```

## POST    /v1/users

##### Example
###### Request

```
POST /v1/users
```

Body
```
 {
    "username": "nik",
    "email": "nik@gmail.com"
 }
```

###### Response

```
 {
     "id": 2,
     "username": "nik",
     "email": "nik@gmail.com"
 }
```

## DELETE  /v1/users/{id}

##### Example
###### Request

```
DELETE /v1/users/4
```

###### Response

STATUS 204

## PUT /v1/users/{id}

##### Example
###### Request

```
PUT /v1/users/2
```

Body
```
 {
    "id": 2,
    "username": "nik",
    "email": "nik@gmail.com"
 }
```

###### Response

```
 {
    "id": 2,
    "username": "nik",
    "email": "nik@gmail.com"
 }
```

## GET /v1/users/{username}

##### Example

###### Request

```
GET /v1/users/vlad
```

###### Response

```
{
  "id": 1,
  "username": "vlad",
  "email": "vlad@gmail.com"
}
```

## GET /v1/users/{username}/account

##### Example
###### Request

```
GET /v1/users/vlad/account
```

###### Response

```
{
  "id": 1,
  "username": "vlad",
  "description": "salary account",
  "balance": 888,
  "currency": "EUR"
}
```

## Transferring Money

## POST /v1/transfers

##### Example

###### Request

```
POST /v1/transfers
```

Body
```json
{
  "amount":123.00,
  "currency":"EUR",
  "sourceAccountId":"1",
  "destinationAccountId":"2"
}
```

###### Response

STATUS 204





