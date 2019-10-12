<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>  
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../../css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="ISO-8859-1">
<title>${fn:replace(drink.name, '\"', '')}</title>
</head>
<body>
	<div class="page-header check-list-header">
		<div class="nav">
			<h2 id="header-logo">
				<a href="/dashboard">BarTindr</a>
			</h2>
			<ul>
				<li>
					<a href="/checklist/lists">Your DrinkLists</a>
				</li>
				<li>
					<a href="/dashboard">
						<c:out value="${user.name}" />
					</a>
				</li>
			</ul>
		</div>
		<div class="container">
			<div class="row">
				<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
					<div class="page-caption">
						<h1 class="page-title"><c:out value="${drink.name}"/></h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- PAGE MAIN -->
	<div id="show-container">
	<br /><br />
	<img src="${fn:replace(drink.imgUrl, '\"', '')}"/>
	
	<div class="show-card">
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
	</div>
	
	</div>
	
</body>
</html>