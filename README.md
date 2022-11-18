# Kruger Challenge
#### Developed by [Diego Álava]
Application for vaccination status of employees.


## Tech

- [Spring Boot] - Spring based Applications
- [JPA] - Implement JPA based repositories
- [Postgresql] - Open source database


## Installation
We need to create the "kruger_alava" database in postgresql, or else configure the **configuration.properties** file
The project was developed in Apache Netbeans IDE, it is recommended to open and run it in a similar IDE.

## User roles
- **ADMIN**
username: ADMIN
password: *any*

- **EMPLOYEE**
username and password automatically generated when the admin registers a new one.

## API Functionalitys
APIs can be called by both users **(ADMIN,Employee)** depending on their permissions.
All requests need to send **Basic Auth username and password**, defined in the **User Roles** section of this document.

#### 1) Add new employee
**Rol:** Admin  
**API:** /api/employee  
**Method:** POST  
**Body Json:**  
```json
{
    "dni":"1315309482",
    "names":"Diego",
    "lastNames":"Alava",
    "email":"diego@outlook.com"
}
```
**Return:** Json of the added entity.

#### 2) Update employee information
**Rol:** Admin, Employee  
**API:** /api/employee  
**Method:** PUT  
**Body Json:**  
```json
{
    "userId":1,
    "birthDate": "1998-08-13",
    "homeAddress": "Manta",
    "phoneNumber": "0999041806",
    "vaccinationStatus":1
}
```
Indicating in the "userId" key the identifier of the employee that we want to update, "vaccinationStatus" can be 1 or 0.
**Return:** Json of the updated entity.

#### 3) Delete employee
**Rol:** Admin  
**API:** /api/employee/delete/{userIdid}  
**Method:** DELETE  
**Return:** Json with the ex userId of the employee and if the execution was correct.  
```json
{
    "deleted": true,
    "exUserId": 1
}
```

#### 4) Add new vaccine information to an employee  
**Rol:** Admin, Employee  
**API:** /api/employee/vaccine/user/{userId}  
**Method:** POST  
**Body Json:**  
```json
{
    "typeOfVaccine":"Pfizer",
    "vaccineDate":"2022-06-31"
}
```
**Return:** Json with info of the added vaccination.

#### 5) Filter by vaccination status 
**Rol:** Admin  
**API:** /api/employee/get/vaccine/status/{status}  
**Method:** GET  
**Path variable:** 1 or 0  
```json
[
    {
        "userId": 1,
        "dni": 1315309482,
        "names": "Diego",
        "lastNames": "Alava",
        "email": "diego@outlook.com",
        "birthDate": "1998-08-13T00:00:00.000+00:00",
        "homeAddress": "Manta",
        "phoneNumber": "0999041803",
        "vaccinationStatus": 1
    },
    {
        "userId": 3,
        "dni": 1315304444,
        "names": "Washo",
        "lastNames": "Cedeño",
        "email": "washo@outlook.com",
        "birthDate": "1998-08-13T00:00:00.000+00:00",
        "homeAddress": "Portoviejo",
        "phoneNumber": "0999041803",
        "vaccinationStatus": 1
    }
]
```
**Return:** Json Array with filtered employees.

#### 6) Filter by vaccination type  
**Rol:** Admin  
**API:** /api/employee/get/vaccine/type/{vaccineType}  
**Method:** GET  
**Path variable values:** Sputnik, AstraZeneca, Pfizer or Jhonson&Jhonson  
```json
[
    {
        "userId": 1,
        "dni": 1315302222,
        "names": "Mario",
        "lastNames": "Rodriguez",
        "email": "mario@outlook.com",
        "birthDate": "1998-08-13T00:00:00.000+00:00",
        "homeAddress": "Portoviejo",
        "phoneNumber": "0999041803",
        "vaccinationStatus": 1
    }
]
```
**Return:** Json Array with filtered employees.

#### 7) Filter by vaccination dates
**Rol:** Admin  
**API:** /api/employee/get/vaccine/dates  
**Method:** GET  
**JSON Body:**  
```json
{
    "begin": "2022-02-01",
    "end": "2022-05-01"
}
```
**Return:** Json Array with filtered employees.

#### 8) Get all employees
**Rol:** Admin  
**API:** /api/employee/get/all  
**Method:** GET  
**Return:** Json Array with all employees.  


   [Spring Boot]: <https://spring.io/projects/spring-boot>
   [JPA]: <https://spring.io/projects/spring-data-jpa>
   [Postgresql]: <https://www.postgresql.org>
   [Diego Álava]: <https://www.linkedin.com/in/diego-fabrizzio-alava>
   
