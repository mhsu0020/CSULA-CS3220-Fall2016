# Employees/Projects/Countries JDBC Example

## Demonstrates one-to-many (country to employees, employees is many side so employee has a foreign key to country), and many-to-many (project and employees, create third table to track relationship) relationships, and how to retrieve data using a DAO (data access object) pattern.

1. Run employees.sql on either your local or cs3 database

2. Download the MySQL JDBC driver and add it under /WEB-INF/lib

3. Change the connection configurations in DatabaseConfig.java

4. Run the project and see the data pulled from database
