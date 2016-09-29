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
	<form action="EditQuiz" method="post">
	<h1>Edit Quiz id ${quiz.id}</h1>
	  <fieldset>
    	<legend>Metadata</legend>
		<div>
			<label for="rating">Difficulty Rating:</label>
			<%-- setting value of an input via expression language --%>
			<input type="number" id="rating" name="rating" min="1" max="10" value="${quiz.difficultyRating}" required>
		</div>
	    <div>
			<label for="category">Category:</label>
			<select id="category" name="category" required>
			<!-- each option value is the category enum value -->
				<c:forEach items="${categories}" var="category">
				<!-- The existing quiz category is marked selected -->
					<option value="${category}" <c:if test="${quiz.category eq category}">selected</c:if>>${category}</option>
				</c:forEach>
			</select>
		</div>
		</fieldset>		
		<fieldset>
			<legend>Question Content</legend>
			<div>
				<label for="question-text">Question Text:</label>
				<%-- setting content of textarea via expression language --%>
				<textarea id="question-text" name="questionText" required>${quiz.questionText}</textarea>
		    </div>
			<div>
				<label for="option1">Option 1:</label>
				<input type="text" id="option1" value="${quiz.options[0]}" name="option1" required>
			</div>
			<div>
				<label for="option2">Option 2:</label>
				<input type="text" id="option2" value="${quiz.options[1]}" name="option2" required>
			</div>
			<div>
				<label for="option3">Option 3:</label>
				<input type="text" id="option3" value="${quiz.options[2]}" name="option3" required>
			</div>
			<div>
				<label for="option4">Option 4:</label>
				<input type="text" id="option4" value="${quiz.options[3]}" name="option4" required>
			</div>
			<div>
				<label for="answer">Correct Answer:</label>
				<input type="number" id="answer" name="answer" min="0" max="3" value="${quiz.correctAnswerIndex}" required>
			</div>	
		</fieldset>
		<!-- hidden field to pass id so doPost knows which quiz to edit -->
		<input type="hidden" name="id" value="${quiz.id}">
		<div class="button">
			<button type="submit">Update Question</button>
		</div>
	</form>
</body>
</html>