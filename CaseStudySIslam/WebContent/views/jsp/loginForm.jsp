<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<form:form id="LoginForm" modelAttribute="loginForm"
		action="loginProcess" method="POST">
		<table>
			<tr>
				<td><form:label path="email"></form:label>Username</td>
				<td><form:input path="email" name="email" id="email" /></td>
			</tr>
			<tr>
				<td><form:label path=""></form:label>Password</td>
				<td><form:input path="password" name="password" id="password" /></td>
			</tr>
			<tr>
				<td></td>
				<td><form:button name="Login" id="Login" >Login</form:button></td>
			</tr>
		</table>

	</form:form>

</body>
</html>