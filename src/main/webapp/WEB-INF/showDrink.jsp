<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1><c:out value="${fn:replace(drink.name, '\"', '')}"/></h1>
	<img src="${fn:replace(drink.imgUrl, '\"', '')}"/>
	<h2>Ingredients</h2>
	<ul>
		<c:forEach items="${drink.ingredients}" var="ingredient">
			<li>
				<c:out value="${fn:replace(ingredient.name, '\"','') }"/>
			</li>
		</c:forEach>
	</ul>
	
	<h2>Instructions</h2>
	<c:out value="${json.drinks.get(0).get('strInstructions')}"/>
	
	<h2>Glass</h2>
	<p>Serve in: <c:out value="${json.drinks.get(0).get('strGlass')}"/></p>
</body>
</html>