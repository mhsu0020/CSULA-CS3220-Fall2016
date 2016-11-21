# Employee Project Web Service Barebone

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

RESTful Resource endpoint:

```
GET http://localhost:8080/employee-project-service-barebone/employees
```
returns list of employees in the following format:

```
{
   "employees":[
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
}
```


Example webpage client:
```
http://localhost:8080/employee-project-service-barebone/ListEmployees
```