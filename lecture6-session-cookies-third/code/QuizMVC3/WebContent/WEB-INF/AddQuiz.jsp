<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Quiz</title>
</head>
<body>
	<form action="AddQuiz" method="post">
	  <h1>Add Quiz</h1>
	  <fieldset>
    	<legend>Metadata</legend>
		<div>
			<label for="rating">Difficulty Rating:</label>
			<input type="number" id="rating" name="rating" min="1" max="10" required>
		</div>
	    <div>
			<label for="category">Category:</label>
			<select id="category" name="category" required>
			<!-- each option value is the category enum value -->
				<c:forEach items="${categories}" var="category">
					<option value="${category}">${category}</option>
				</c:forEach>
			</select>
		</div>				
		</fieldset>
		<fieldset>
			<legend>Question Content</legend>
			<div>
				<label for="question-text">Question Text:</label>
				<textarea id="question-text" name="questionText" required></textarea>
			</div>
			<div>
				<label for="option1">Option 1:</label>
				<input type="text" id="option1" name="option1" required>
			</div>
			<div>
				<label for="option2">Option 2:</label>
				<input type="text" id="option2" name="option2" required>
			</div>
			<div>
				<label for="option3">Option 3:</label>
				<input type="text" id="option3" name="option3" required>
			</div>
			<div>
				<label for="option4">Option 4:</label>
				<input type="text" id="option4" name="option4" required>
			</div>
			<div>
				<label for="answer">Correct Answer:</label>
				<input type="number" id="answer" name="answer" min="0" max="3" required>
			</div>	
		</fieldset>
		<div class="button">
			<button type="submit">Submit Question</button>
		</div>
		
	</form>
</body>
</html>