# Library Management API

## Overview

The Library Management API is a RESTful service that allows users to manage library operations such as borrowing and returning books, managing users, and tracking borrowed records. This API ensures compliance with borrowing limits, handles suspended users, and calculates fines.

## Features

- Borrow and return books
- Borrowing rules enforcement
- Fine calculation for overdue items
- Management of different user types 
- Management of different item types


## Technologies Used

- Java (Spring Boot)
- Spring Data JPA(Hibernate)
- REST APIs
- POSTMAN
- MySQL (Database)
- JUnit & Mockito (Testing)

## API Endpoints

- Register a new user: `POST /library/addUser
- Get user details: `GET /library/User
- Create new Item : POST /library/addItem
- Get item list : GET /library/items
- Borrow item for user : POST /library/borrow
- Return item : GET /library/borrow

