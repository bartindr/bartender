
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Ingredient Checklist</title>
</head>
<body>
	<h2>What do you have around there?</h2>
	<h3>Check your fridge, pantry, or wherever you stow your "goods".</h3>
		<form:form action="" method="POST" modelAttribute="DrinkListIngredient">
		<input type="submit" value="Submit">
	</form:form>
</body>
</html>