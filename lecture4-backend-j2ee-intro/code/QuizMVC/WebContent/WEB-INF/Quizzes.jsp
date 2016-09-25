<%-- This is a comment directive. below are JSTL directives. Note that in the final html returned to client it doe not show up.--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%-- The prefix shows up in the core tags you use --%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Quizzes</title>
	<%-- Files in WEB-INF are not publicly accessible. the css file is located in the css folder under the WebContent directory. --%>
	 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1><c:out value="${pageHeader}" /></h1>
	<h2>Page generated at: <fmt:formatDate value="${date}" pattern="yyyy-MM-dd hh:mm:ss a" /></h2>
	<%-- You can still add classes to the elements, just like regular html --%>
	<table class="quiz-table">
	<tr><th>Question Text</th><th>Detailed Link</th><th>Difficulty Rating</th></tr>
	<%-- The varStatus property gives information about the current loop. see http://docs.oracle.com/javaee/6/api/javax/servlet/jsp/jstl/core/LoopTagStatus.html for more info. Remember getIndex translates to variable.index--%>
	<%-- looping through the quizzes ArrayList passed from request.setAttribute, and generate table rows. quiz.qeustionText calls quiz.getQuestionText(), and so on --%>
	<c:forEach items="${quizzes}" var="quiz" varStatus="loop">
      <tr>
      	<td>${quiz.questionText}</td>
		<td><a href="ViewQuiz?id=${loop.index}">ViewQuiz?id=${loop.index}</a></td>
		<td>${quiz.difficultyRating}/10</td>
      </tr>
	</c:forEach>
	</table>
</body>
</html>
