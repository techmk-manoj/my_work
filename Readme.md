# MHBS
Hotel Booking System

## I. Technology Stack
```
- Spring Framework 3.9
- Java 8
- MySQL 5.7
-Gradle
```

## II. How to Run
```
1. clone this repository - https://github.com/techmk-manoj/my_work.git
2. install JDK 1.8
3. install MySQL 5.7
    - run at localhost:3306 
- create databases with name 'M3BI-HM' ;
4.import project as gradle in STS above version 3.9.XX
5.update build.gradle
5. run as spring boot app
5. by default, application will run at localhost:8080
``` 

## III. API Documentation
This section describes the available API endpoints with example request and response

### 1. Room Type

#### a. get available room types
``` 
GET /room-types/available?start=2021-02-01&end=2021-10-30
```
```
Response :
[
    {
        "id": 1,
        "type": "executive",
        "description": "executive room only for 1 person",
        "image": "",
        "available_quantity": 26,
        "price": 1000
    },
    {
        "id": 2,
        "type": "regular",
        "description": "regular room",
        "image": "",
        "available_quantity": 40,
        "price": 400
    }
]
```

### 2. Reservation

#### a. create reservation
``` 
POST /reservations
Request body :
{
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 10,
    "start_date": "2021-02-05",
    "end_date": "2021-02-10",
}
```
```
Response :
{
    "id": 1,
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 10,
    "start_date": "2019-11-28",
    "end_date": "2019-11-30",
    "cancelled": false,
    "created_at": "2018-03-11",
    "updated_at": "2018-03-11"
}
```

#### b. get reservation
``` 
GET /reservations/1
```
```
Response :
{
    "id": 1,
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 10,
    "start_date": "2021-11-28",
    "end_date": "2021-11-30",
    "cancelled": false,
    "created_at": "2021-02-01",
    "updated_at": "2021-02-01"
}
```

#### c. update reservation
``` 
PATCH /reservations/1
Request body :
{
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 5,
    "start_date": "2021-02-12",
    "end_date": "2021-02-28",
}
```
```
Response :
{
    "id": 1,
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 5,
     "start_date": "2021-02-12",
    "end_date": "2021-02-28",
    "cancelled": false,
    "created_at": "2021-02-01",
    "updated_at": "2021-02-01"
}
```

#### d. cancel reservation
``` 
PATCH /reservations/1/cancel
```
```
Response :
{
    "id": 1,
    "room_type_id": 1,
    "customer_id": 1,
    "quantity": 10,
    "start_date": "2019-11-28",
    "end_date": "2019-11-30",
    "cancelled": true,
    "created_at": "2021-02-01 19:23:06",
    "updated_at": "2021-02-01 19:23:06"
}
```

## IV. Admin Dashboard
This section describes the list of available dashboard.
The URL is {host}/admin, for example http://localhost:8080/admin/login

### 1. Login and Logout
Features :
```
a. login
b. logout
```
Default admin user credentials :
```
username : admin
password : admin
```

### 2. Room Type Dashboard
Features :
```
a. list room types
b. create room type
c. update room type
d. delete room type
```

### 3. Reservation
Features
```
a. list reservations
```

### 4. Admin User
Features :
```
a. list admin users
b. create admin user
c. change password
```

## Swaager API Documentation

http://localhost:8080/v2/api-docs



