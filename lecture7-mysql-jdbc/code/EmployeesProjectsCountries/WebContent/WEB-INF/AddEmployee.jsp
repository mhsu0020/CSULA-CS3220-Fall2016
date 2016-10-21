<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<form action="AddEmployee" method="post">
	  <h1>Add Employee</h1>
		<fieldset>
			<legend>Employee Info</legend>
			<div>
				<label for="firstName">First Name:</label>
				<input type="text" id="firstName" name="firstName" required>
			</div>
			<div>
				<label for="lastName">Last Name:</label>
				<input type="text" id="lastName" name="lastName" required>
			</div>
			<div>
				<label for="address">Address:</label>
				<input type="text" id="address" name="address" required>
			</div>
		</fieldset>
		<div class="button">
			<button type="submit">Submit Info</button>
		</div>
		
	</form>
</body>
</html>