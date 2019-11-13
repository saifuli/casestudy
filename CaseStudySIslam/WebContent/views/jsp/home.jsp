<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home - The GALLERY!</title>
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
			<jsp:include page="menu.jsp" />
			<div class="container-fluid">
				<div class="test2">
					<h1>
						Welcome to the GALLERY!
						<c:if test="${pageContext.request.userPrincipal.name != null}">
							${credential.getUsername()}
						</c:if>
					</h1>
				</div>
			</div>
		</div>
	</div>
</body>
</html>