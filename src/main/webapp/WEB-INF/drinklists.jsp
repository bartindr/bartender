<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<style>
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>HELLO FROM LISTS</h1>
	<c:forEach items="${drinkLists}" var="drinkList">
		<a href="/checklist/${drinkList.id}">
			<c:out value="${drinkList.name}" />
		</a>
	</c:forEach>
	
	<form:form action="/checklist/create" method="POST" modelAttribute="drinkList">
		<p>
	        <form:label path="name">Make a new drink list?</form:label>
	        <form:errors path="name"/>
	        <form:input path="name"/>
	        <input type="hidden" name="userId" value="${user.id}"/>
		</p>
    	<input type="submit" value="Let's check it off"/>
	</form:form>
</body>
</html>