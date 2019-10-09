<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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
<body>
	<div id="auth_container">
		<div id="register_form">
			<h1>Register</h1>
			<!-- make form:form for register -->
			<form:form action="/user/register" method="post" modelAttribute="user">
				<table>
					<tbody>
						<tr>
							<td>
								<form:label path="name">Name:</form:label>
							</td>
							<td>
								<form:input path="name" type="text"/>
								<form:errors path="name"></form:errors>
							</td>
						</tr>
						<tr>
							<td>
								<form:label path="email">Email:</form:label>
							</td>
							<td>
								<form:input path="email" type="email"/>
								<form:errors path="email"></form:errors>						
							</td>
						</tr>
						<tr>
							<td>
								<form:label path="password">Password:</form:label>
							</td>
							<td>
								<form:input path="password" type="password"/>
								<form:errors path="password"></form:errors>						
							</td>
						</tr>
						<tr>
							<td>
								<form:label path="passwordConfirmation">Password Conf:</form:label>
							</td>
							<td>
								<form:input path="passwordConfirmation" type="password"/>
							</td>
							<td>							
								<p style="color:red;"><c:out value="${errorPw}"/></p>				
							</td>
						</tr>
					</tbody>
				</table>
				<input type="submit" value="Register"/>
			</form:form>
			<!-- contains: name, email, password, and confirmation -->
		</div>
		<div id="login_form">
			<h1>Login</h1>
			<!-- make form:form for login -->
			<form action="/user/login" method="post">
				<table>
					<tbody>
						<tr>
							<td>
								<label for="email">Email</label>
							</td>
							<td>							
								<input type="email" id="email" name="email"/>													
							</td>
						</tr>
						<tr>
							<td>
								<label for="password">Password</label>
							</td>
							<td>
								<input type="password" id="password" name="password"/>						
							</td>
						</tr>
					</tbody>
				</table>
				<p style="color:red;"><c:out value="${errorLogin}"/></p>	
				<input type="submit" value="Login"/>
			</form>
			<!-- contains email and password -->
		</div>
	</div>
</body>
</html>