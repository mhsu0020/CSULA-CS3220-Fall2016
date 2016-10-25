<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Change Rating</title>
</head>
<body>
	<form action="ChangeRating" method="post">
	  <h1>Change Rating</h1>
		<fieldset>
			<legend>Members Ratings</legend>
			<div>
			 	<%-- Key: Employee, Value: rating of employee as project member --%>
				<c:forEach var="projectMemberRating" items="${projectMemberRatings}">
					<div>
						<%-- Using employee id as part of the input name/id --%>
						<label for="rating-${projectMemberRating.key.id}">${projectMemberRating.key.firstName} ${projectMemberRating.key.lastName}::</label>
						<input type="number" id="rating-${projectMemberRating.key.id}" name="rating-${projectMemberRating.key.id}" value="${projectMemberRating.value}" required>
					</div>
				</c:forEach>
			</div>
			<input type="hidden" name="projectId" value="${projectId}">
		</fieldset>
		<div class="button">
			<button type="submit">Submit Ratings</button>
		</div>
		
	</form>
</body>
</html>