# Employee Project Web Service With Jackson

Uses Jackson for JSON Marshalling/Unmarshalling, no manual JSON String creation required.

See the [mkyong tutorial](https://www.mkyong.com/java/jackson-2-convert-java-object-to-from-json/)

Note: Remember to change the database config in src/main/java/DatabaseConfig.java

To run the commands on Windows, install [git](https://git-scm.com/downloads), then shift + right-click in the folder and select "Git Bash here"

Cleaning and Packaging

```
./mvnw clean package
```

Cleaning/Packaging and Running

```
./mvnw clean package tomcat7:run-war

```

RESTful Resource endpoint (try them out in Postman after running the project):

```
GET http://localhost:8080/employee-project-service-jackson/employees
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
GET http://localhost:8080/employee-project-service-jackson/countries
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
POST http://localhost:8080/employee-project-service-jackson/employee

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
http://localhost:8080/employee-project-service-jackson/ListEmployees
```