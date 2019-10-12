<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title><c:out value="${user.name}"/>'s Drink Lists</title>
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
						<h1 class="page-title"><c:out value="${user.name}"/>'s Drink Lists</h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- PAGE MAIN -->
	<div class="section-section">
		<div class="container">
			<div class="card-block bg-white mb30 page-main-container">
				<div class="row">
					<div class="page-main-body col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div id="drink-lists-container" class="section-title mb-0">
								<div id="make-new-list-form">
									<form:form action="/checklist/create" method="POST" modelAttribute="drinkList">
								        <form:label path="name">
								        	<h2>Special occasion? Make a new list</h2>
							        	</form:label>
										<p>
									        <form:errors path="name"/>
									        <form:input id="new-list-name-input" path="name" placeholder="Your new list name *"/>
									        <input type="hidden" name="userId" value="${user.id}"/>
										</p>
								    	<input type="submit" class="dash-btn btnSubmit" value="Let's start"/>
									</form:form>
								</div>
								<div id="drink-lists-list">
									<div id="drink-lists-list-head">
										<h2>Your current lists</h2>
									</div>
									<c:forEach items="${drinkLists}" var="drinkList">
										<a href="/checklist/${drinkList.id}">
											<c:out value="${drinkList.name}" />
										</a>
									</c:forEach>
								</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>