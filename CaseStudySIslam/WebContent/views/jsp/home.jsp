<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Welcome Home!</title>
</head>
<body>
<jsp:include page="menu.jsp"/>

Welcome ${user.name}!
<br>
Username: ${credential.getEmail()}
<br>
Password: ${credential.getPassword()}
<br>
This is an authenticated homepage!
</body>
</html>