<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css" />
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<title>BarTindr</title>
</head>
<body id="container" class="login-container">
	<div id="logo">
		<h1 id="logo-text">BarTindr</h1>
	</div>
	<div id="auth_container" class="row">
		<div id="login_form" class="col-md-6 login-form-2">
			<h3>Login</h3>
			<!-- make form:form for login -->
			<form action="/user/login" method="post">
				<div class="form-group">		
						<input type="email" id="email" name="email" placeholder="Your e-mail"/>		
				</div>
				<div class="form-group">
						<input type="password" id="password" name="password" placeholder="Your password *"/>
				</div>
				<p style="color:red;"><c:out value="${errorLogin}"/></p>	
				<div class="login-button-div">
					<input class="btnSubmit" type="submit" value="Login"/>
				</div>
			</form>
			<!-- contains email and password -->
		</div>
		<div id="register_form" class="col-md-6 login-form-1">
			<h3>Register</h3>
			<!-- make form:form for register -->
			<form:form action="/user/register" method="post" modelAttribute="user">
				<div class="form-group">
						<form:input path="name" type="text" placeholder="Your name *"/>
						<form:errors path="name"></form:errors>
				</div>
				<div class="form-group">
						<form:input path="email" type="email" placeholder="Your e-mail *"/>
						<form:errors path="email"></form:errors>	
				</div>
				<div class="form-group">
						<form:input path="password" type="password" placeholder="Your password *"/>
						<form:errors path="password"></form:errors>		
				</div>
				<div class="form-group">
						<form:input path="passwordConfirmation" type="password" placeholder="re-enter password *"/>
						<p style="color:red;"><c:out value="${errorPw}"/></p>	
				</div>
				<div class="login-button-div">
					<input class="btnSubmit" type="submit" value="Register"/>
				</div>
			</form:form>
			<!-- contains: name, email, password, and confirmation -->
		</div>

	</div>
</body>
</html>