# Example Maven Servlet Project

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
