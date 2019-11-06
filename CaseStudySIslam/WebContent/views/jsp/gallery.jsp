<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<h1>List of posts</h1>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="${pageContext.request.contextPath}/gallery/upload">Create a new post</a>
	</c:if>
	<p>${pageContext.request.userPrincipal.name}</p>
</body>
</html>