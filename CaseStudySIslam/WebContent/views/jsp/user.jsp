<%@page import="java.util.Map"%>
<%@page import="java.util.LinkedHashMap"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User - ${credential.getUsername()} - The GALLERY!</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/gallery.css' />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.js"/>" defer></script>
<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>" defer></script>
<script src="<c:url value="/resources/js/popper.min.js"/>" defer></script>
</head>
<body>
	<div class="container rounded">
			<div class="test rounded border border-dark">
	<jsp:include page="menu.jsp"/>
	<div class="container-fluid">
	<div class="test2">
	<p>Name: ${user.getName()}</p>
	<p>Email: ${credential.getEmail()}</p>
	<p>Username: ${credential.getUsername()}</p>
	<p>Number of posts: ${user.getNumOfPosts()}</p>
	<p>Number of comments: ${user.getNumOfComments()}</p>
	<form
			action="${pageContext.request.contextPath}/user/edit/${credential.getEmail()}"
			method="get">
			<input type="submit" value="Edit" />
	</form>
	<form
			action="${pageContext.request.contextPath}/user/delete/${credential.getEmail()}"
			method="post">
			<input type="submit" value="Delete" />
	</form>
	</div>
	</div>
	</div>
	</div>
</body>
</html>