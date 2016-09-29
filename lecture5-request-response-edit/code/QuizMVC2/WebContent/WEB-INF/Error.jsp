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
	<title>Error</title>
	<%-- Files in WEB-INF are not publicly accessible. the css file is located in the css folder under the WebContent directory. --%>
	 <link rel="stylesheet" href="css/main.css">
</head>
<body>
	<h1>Error: ${message}</h1>
</body>
</html>
