<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<meta charset="UTF-8">
<title>Drink List Result</title>
</head>
<body>
	<div class="page-header drink-lists-header">
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
						<h1 class="page-title">Your matches</h1>
					</div>
				</div>
			</div>
		</div>
	</div>


<!-- PAGE MAIN -->
	<div id="drink-list-results">
		<c:forEach items="${drinks}" var="drink">
			<div class="drink-card">
				<div>
					<h3><a href="/drinks/${drink.drinkId}"><c:out value="${drink.name}"/></a></h3>
				</div>
				<a href="/drinks/${drink.drinkId}">
				<div class="drink-card-img" style="background:url(${fn:replace(drink.imgUrl, '\"', '')})no-repeat;background-size:cover">
				</div>
				 </a>
			</div>
		</c:forEach>
	</div>
	
	<!--  -->
	<!--  -->
	<!--  -->
<%-- 	<c:forEach items="${drinks}" var="drink">
		<ul>
			<li>
				<a href="/drinks/${drink.drinkId}"><c:out value="${drink.name}"/></a>
				<ul>
					<c:forEach items="${drink.ingredients}" var="ingredient">
						<li>
							<c:out value="${ingredient.name}" />
						</li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</c:forEach> --%>
</body>
</html>