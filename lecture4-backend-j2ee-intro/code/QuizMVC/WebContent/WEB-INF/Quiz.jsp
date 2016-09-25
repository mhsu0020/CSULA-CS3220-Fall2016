<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quizzes</title>

</head>
<body>
    <%--display different html based on difficulty --%>
    <c:choose>
    	<c:when test="${quiz.difficultyRating >= 5}">
    		<p>Difficult Quiz: ${quiz.difficultyRating}/10</p>
    	</c:when>
    	<c:otherwise>
    		<p>Eazy Peazy: ${quiz.difficultyRating}/10</p>
    	</c:otherwise>
    </c:choose>
	<p><c:out value="${quiz.questionText}" /></p>
	<ul>
	<c:forEach items="${quiz.options}" var="option">
      <li>${option}</li>
	</c:forEach>
	</ul>
</body>
</html>