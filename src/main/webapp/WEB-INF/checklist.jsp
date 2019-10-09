
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
<link rel="stylesheet" href="css/style.css" />
<title>Ingredient Checklist</title>
</head>
<body id="checklist-container">
	<h2>What do you have,  <c:out value="${user.name}"/>?</h2>
	<h3>Check your fridge, pantry, or wherever you stow your "goods".</h3>
	<div id="checklist-main">
		<div id="ingredient-search-div">
			<form id="ingredientSearchForm" action="/api/checklist/add" method="POST">
				<%-- <input type="hidden" name="currentDrinkList" value="${currentDrinkList}"/> --%>
				<input type="text" name="ingredientName" id="ingredientSearch"/>
				<input type="submit" value="Add"/>
			</form>
		</div>
		<table id="currentIngredients">
			<thead>
				<tr>
					<th>Ingredient</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${currentDrinkList.ingredients}" var="ingredient">
					<tr>
						<c:out value="${ingredient.name}" />
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<!-- SCRIPT SRC -->
	<script src="/script/app.js"></script>
</body>
</html>