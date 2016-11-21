<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- The prefix shows up in the core tags you use --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employees RESTful Web Service Example With Jackson</title>
	<link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Employees From Database Using AJAX on EmployeeResource Web Service (Jackson)</h1>
	<table>
		<thead>
			<tr><th>id</th><th>name</th><th>address</th><th>country</th></tr>
		</thead>
		<tbody id="employees-table-body">
			
		</tbody>
	</table>
	<br><br>
	<button id="get-employees">Refresh Employees List (No Page Refresh)</button>
	<hr>
	<form id="add-employee">
	  <h1>Add Employee (AJAX HTTP POST)</h1>
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
				<label for="country">Country:</label>
				<select id="country" name="country" required></select>
			</div>
			<div>
				<label for="address">Address:</label>
				<input type="text" id="address" name="address" required>
			</div>
		</fieldset>
		<div class="button">
			<button id="add-employee-button" type="button">POST Info</button>
		</div>
	</form>
	
	
	<!-- Using JQuery CDN, downloads jquery javascript file from their CDN server -->
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script src="js/employees.js"></script>
</body>
</html>
