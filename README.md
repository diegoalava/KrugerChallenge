# Kruger Challenge
#### Developed by [Diego Álava]
Application for vaccination status of employees.


## Tech

- [Spring Boot] - Spring based Applications
- [JPA] - Implement JPA based repositories
- [Postgresql] - Open source database


## Installation
Description...

## User roles
- Admin
- Employee

## API Functionalitys
APIs can be called by both users **(ADMIN,Employee)** depending on their permissions.

#### 1) Add new employee
**Rol:** Admin
**API:** /api/employee
**Method:** POST
**Body Json:**
```json
{
    "dni":"1315308888",
    "names":"Diego",
    "lastNames":"Alava",
    "email":"123@outlook.com"
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
    "homeAddress": "Manta aqui estoooooooy",
    "phoneNumber": "0999041806",
    "vaccinationStatus":"Vacunado"
}
```
Indicating in the "userId" key the identifier of the employee that we want to update.
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

## License
Test Software

   [Spring Boot]: <https://spring.io/projects/spring-boot>
   [JPA]: <https://spring.io/projects/spring-data-jpa>
   [Postgresql]: <https://www.postgresql.org>
   [Diego Álava]: <https://www.linkedin.com/in/diego-fabrizzio-alava>
   
