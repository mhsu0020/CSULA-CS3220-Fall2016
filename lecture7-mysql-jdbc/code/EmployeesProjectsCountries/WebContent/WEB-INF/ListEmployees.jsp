<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- The prefix shows up in the core tags you use --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employees</title>
	 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Employees From Database</h1>
	<table>
	<tr><th>id</th><th>name</th><th>address</th></tr>
	<c:forEach items="${employees}" var="employee" varStatus="loop">
      <tr>
      	<td>${employee.id}</td>
		<td>${employee.firstName} ${employee.lastName}</td>
		<td>${employee.address}</td>
      </tr>
	</c:forEach>
	</table>
	<br><br>
	<a href="AddEmployee">Add Employee</a>
</body>
</html>
