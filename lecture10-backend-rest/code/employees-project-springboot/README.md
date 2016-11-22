# Employee Project Web Service With Spring Boot

Spring Boot bundles many useful Spring and third-party libraries together in the form of a parent POM. For example, Jackson is included by default.

Compare this with the other two examples, you will see that it requires significantly less code and very little repetition.

Note: Remember to change the Spring Data Source database config in src/main/resources/application.properties

You can base your lab off this project or use the Spring Boot Initializer to generate the project:
[http://start.spring.io/](http://start.spring.io/)

Documentation:

* [How Spring Depedency Injection (the @Autowired stuff) works](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/beans.html)
* [REST Service with Spring Boot](https://spring.io/guides/gs/rest-service/)
* [Complete Reference](http://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
* [JdbcTemplate](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/jdbc.html)

To run the commands on Windows, install [git](https://git-scm.com/downloads), then shift + right-click in the folder and select "Git Bash here"

Cleaning and Packaging

```
./mvnw clean package
```

Cleaning/Packaging and Running Using the Spring Boot Maven Plugin

```
./mvnw clean package spring-boot:run

```

RESTful Resource endpoint (try them out in Postman after running the project):

```
GET http://localhost:8080/employee/1

GET http://localhost:8080/employee?id=1
```

returns a single employee with the id in the following format:

```
{
   "id":1,
   "firstName":"John",
   "lastName":"Doe",
   "address":"Street #215",
   "country":{
      "id":1,
      "name":"USA"
   }
}
```

```
GET http://localhost:8080/employees
```
returns list of employees in the following format:

```
[
      {
         "id":1,
         "firstName":"John",
         "lastName":"Doe",
         "address":"Street #215",
         "country":{
            "id":1,
            "name":"USA"
         }
      },
    
      ...
    
      {
         "id":2,
         "firstName":"Michael",
         "lastName":"Hsu",
         "address":"Street #711",
         "country":{
            "id":1,
            "name":"USA"
         }
      }
]

```

```
GET http://localhost:8080/countries
```

returns list of countries in the following format:

```
[
   {
      "id":1,
      "name":"USA"
   },
   {
      "id":2,
      "name":"Canada"
   },
   {
      "id":3,
      "name":"China"
   }
]
```



```
POST http://localhost:8080/employee

body:

{
   "firstName":"test3first",
   "lastName":"test3last",
   "address":"testAddress",
   "country":{
      "id":1,
      "name":"USA"
   }
}

```

returns created employee id in following format:
```
{"id": 15}
```

Example webpage client:
```
http://localhost:8080
```