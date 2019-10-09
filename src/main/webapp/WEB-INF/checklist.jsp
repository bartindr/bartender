
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
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<title>Ingredient Checklist</title>
</head>
<body>
	<h2>Whatd'ya have?</h2>
	<h3>Check your fridge, pantry, or wherever you stow your "goods".</h3>
	<form id="ingredientSearchForm" action="/api/checklist/add">
		<input type="text" name="ingredientName" id="ingredientSearch"/>
		<input type="hidden" name="drinkList" value="${drinkList}"/>
		<input type="submit" value="Add"/>
	</form>
	<script src="/script/app.js"></script>
</body>
</html>