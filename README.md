Swedbank Test Assignment
===============================

Application for managing bank accounts. One can assume that this application is just for one user and no authentication/authorization has to be in place. Whoever is using the application should be able to do everything.

## Project description

- It must be possible to create a new bank account, edit and delete it. For each account it must be possible to specify account name, balance and currency. Make it possible to choose at least between two currencies.
- It must be possible to view all created accounts.
- It should also be possible to make payments between accounts. When accounts have different currencies, then the money should be converted from source account currency to the currency that is used in target account. When making payments you should select source account, target account and the amount you want to transfer from the source account.
- It must not be possible to transfer more money than is available in account, also zero or negative amount.


[Technologies used](#technologies-used)

[Endpoints](#endpoints) – API endpoints

[How to use](#how-to-use?)


## Technologies used

- Java 11
- REST API
- Maven
- Spring Boot
- Spring JPA
- H2 Embedded Database
- JUnit
- Angular


## Endpoints

- GET **All accounts**: `/accounts`
- GET **Account by id**: `/accounts/<id>`
- POST **New account**: `/accounts`
- PUT **Edit account**: `/accounts/<id>`
- PUT **Transfer between accounts**: `/accounts/from/<from_id>/to/<to_id>`
- DELETE **Account by id**: `/accounts/<id>`


## How to use?

To run the server
```
cd SwedBank/server
mvn clean install
mvn spring-boot:run
```

To run the client
```
cd SwedBank/client/angularclient
npm install
ng serve --open
```

Access ```http://localhost:4200```
