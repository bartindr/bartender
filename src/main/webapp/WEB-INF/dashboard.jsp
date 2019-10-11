<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<meta charset="UTF-8">
<title>BarTindr</title>
</head>
<body>
	<%-- HEADER --%>
	<div class="page-header">
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
						<h1 class="page-title">Hello, <c:out value="${user.name}"/></h1>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%-- DASH MAIN --%>
	<div class="section-section">
		<div class="container">
			<div class="card-block bg-white mb30 page-main-container">
				<div class="row">
					<div class="page-main-body col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
						<div class="section-title mb-0">
							<form class="dash-main" action="/checklist/lists" method="get">
								<h2>Let's plan out your night.</h2>
								<p>(or day, it's a night in somewhere in the world right?)</p>
								<button class="btnSubmit dash-btn" type="submit">Let's go!</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>