
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" href="../css/style.css" />
<title>Ingredient Checklist</title>
</head>
<body id="checklist-container">
	<h2>What do you have,  <c:out value="${user.name}"/>?</h2>
	<h3>Show us the goods.</h3>
	<div id="checklist-main">
		<div id="ingredient-search-div">
			<form id="ingredientSearchForm" action="/api/checklist/add" method="POST">
				<%-- <input type="hidden" name="currentDrinkList" value="${currentDrinkList}"/> --%>
				<input type="text" name="ingredientName" id="ingredientSearch"/>
				<input type="hidden" name="drinkListId" value="${drinkList.id}"/>
				<input type="submit" value="Add"/>
			</form>
		</div>
		<div id="current-ingredients-div">
			<table id="current-ingredients-table">
				<thead>
					<tr>
						<th>Ingredient</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${drinkList.ingredients}" var="ingredient">
							<tr id='checklist_ingredient_${ingredient.id}'>
								<td>${fn:replace(ingredient.name, '\"', '')}</td>
								<td>
									<form id="ingredient-form-${ingredient.id}" class="delete-ingredient" action="api/${drinkList.id}/delete/${ingredient.id}" method="POST">
										<input type="hidden" name="_method" value="delete"/>
										<input type="hidden" name="drinkListId" value="${drinkList.id}"/>
										<input type="hidden" name="ingredientId" value="${ingredient.id}"/>
										<input type="submit" data-form-id="ingredient-form-${ingredient.id}" class="delete-list-item" value="X" />
									</form>
								</td>
							</tr>
					</c:forEach>
					<tr>
						<td>
						</td>
					</tr>
				</tbody>
			</table>
			<form action="/checklist/${drinkList.id}/generateList" method="GET">
				<%-- <input type="hidden" name="list" value="${drinkList.id}"/> --%>
				<input id="submit-checklist" type="submit" value="Let's see what we can do" />
			</form>
		</div>
	</div>
	
	<!-- SCRIPT SRC -->
	<script src="/script/app.js"></script>
</body>
</html>