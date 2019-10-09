<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BarTindr</title>
</head>
<body>
	<h1>Hello, <c:out value="${user.name}"/></h1>
	<form action="/checklist" method="get">
		<button type="submit">What are you having?</button>
	</form>
</body>
</html>