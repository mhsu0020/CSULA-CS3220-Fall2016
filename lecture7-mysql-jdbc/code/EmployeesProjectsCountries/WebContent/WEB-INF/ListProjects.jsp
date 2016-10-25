<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- The prefix shows up in the core tags you use --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Projects</title>
	 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Projects</h1>
	<table>
	<tr><th>id</th><th>project name</th><th>leader</th><th>members</th></tr>
	<c:forEach items="${projects}" var="project" varStatus="loop">
      <tr>
      	<td>${project.id}</td>
		<td>${project.name}</td>
		<td>${project.leader.firstName} ${project.leader.lastName}</td>
		<td>TBH</td>
      </tr>
	</c:forEach>
	</table>
	<br><br>
</body>
</html>
