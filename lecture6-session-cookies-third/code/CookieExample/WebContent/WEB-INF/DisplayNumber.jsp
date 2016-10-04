<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Display Number</title>
</head>
<body>
	<h1>Current number in cookie: ${number}</h1>
	<form action="DisplayNumber" method="post">
	  <h1>Create New Number</h1>
	  	<label for="number">New Number</label>
		<input type="number" id="number" name="number" value="${number}" required>
		<div class="button">
			<button type="submit">Submit number</button>
		</div>
		
	</form>
</body>
</html>