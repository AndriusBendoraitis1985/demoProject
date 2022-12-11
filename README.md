# Demo project (Bank Account Balance)

Demo service as a homework.
Service which sole purpose is to manage bank account balance via Rest API.

## Description

Endpoints:
* Import bank statement for one or several bank accounts via CSV.
* Export bank statement for one or several bank accounts via CSV.
* Calculate account balance for a given date.
* View all accounts balance entries.

## Getting Started

### Dependencies

* Java JDK 11
* Maven 3.8.6 alt least
* Postman

### Installing

* Clone repository
* Open project as Maven project and update all dependencies

### Executing program

* API uses H2 database, therefore no data is stored after stopping the application.
* Initial data is uploaded during API startup, therefore it is able to test it instantly.
* File for demo import is added into project resources package. File name ``` import.csv```
* For data access and manipulation it is required to create requests to endpoints:
1. GET http://localhost:8081/operations/all No Auth No Params
2. GET http://localhost:8081/operations/filter No Auth ```Params: account(mandatory), dateFrom(optional), dateTo(optional)```
3. POST http://localhost:8081/file/upload No Auth No Params ```Body:form-data``` ```Key:file Value:import.csv```
4. GET http://localhost:8081/file/export No Auth ```Params: dateFrom(optional), dateTo(optional)```

## Authors

Andrius Bendoraitis
bendoraitis.andriuss@gmail.com
