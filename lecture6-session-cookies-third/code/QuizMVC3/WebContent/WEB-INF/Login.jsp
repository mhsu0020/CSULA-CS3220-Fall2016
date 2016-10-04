<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="Login" method="post">
	  <h1>Login</h1>
	  <c:if test="${not empty message}">
   			<h3><strong>${message}</strong></h3>   
		</c:if>
    	<div>
				<label for="username">Username:</label>
				<input type="text" id="username" name="username" required>
		</div>
		<div>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
		</div>
		<div class="button">
			<button type="submit">Login</button>
		</div>
	</form>
</body>
</html>