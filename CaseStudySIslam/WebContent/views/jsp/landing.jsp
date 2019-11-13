<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>The GALLERY!</title>
<link href="<c:url value='/resources/css/default.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet">
<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet">
<style>
	body{
		background: url("<c:url value='/resources/images/background2.jpg'/>");
	}
</style>
</head>
<body>
	<div class="main-wrapper">
		<div class="wrapper">
			<div id="intro">
                <form action="${pageContext.request.contextPath}/home" method="get">
                    <button type="submit" id="introButton">ENTER</button>
                </form>
			</div>
		</div>
	</div>
</body>
</html>