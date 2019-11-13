<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.js"/>" defer></script>
<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>" defer></script>
<script src="<c:url value="/resources/js/popper.min.js"/>" defer></script>

</head>
<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light rounded">
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand"
			href="${pageContext.request.contextPath}/landing">GALLERY</a>

		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
				<c:if test="${pageContext.request.servletPath == '/views/jsp/home.jsp'}">
				<li class="nav-item active">
				</c:if>
				<c:if test="${pageContext.request.servletPath != '/views/jsp/home.jsp'}">
				<li class="nav-item">
				</c:if>
					<a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a>
				</li>
				<c:if test="${pageContext.request.servletPath == '/views/jsp/gallery.jsp'}">
				<li class="nav-item active">
				</c:if>
				<c:if test="${pageContext.request.servletPath != '/views/jsp/gallery.jsp'}">
				<li class="nav-item">
				</c:if>
					<a class="nav-link" href="${pageContext.request.contextPath}/gallery">Gallery</a>
				</li>
				<c:if test="${pageContext.request.servletPath == '/views/jsp/contact.jsp'}">
				<li class="nav-item active">
				</c:if>
				<c:if test="${pageContext.request.servletPath != '/views/jsp/contact.jsp'}">
				<li class="nav-item">
				</c:if>
					<a class="nav-link" href="${pageContext.request.contextPath}/contactus">Contact Us</a>
				</li>
			</ul>
			<div class="form-inline my-2 my-lg-0">
				<ul class="navbar-nav mr-auto mt-2 mt-lg-0">
					<c:if test="${pageContext.request.userPrincipal.name == null}">
						<c:if test="${pageContext.request.servletPath == '/views/jsp/login.jsp'}">
						<li class="nav-item active">
						</c:if>
						<c:if test="${pageContext.request.servletPath != '/views/jsp/login.jsp'}">
						<li class="nav-item">
						</c:if>
							<a class="nav-link" href="${pageContext.request.contextPath}/login">Login</a>
						</li>
						<c:if test="${pageContext.request.servletPath == '/views/jsp/userCredentialForm.jsp'}">
						<li class="nav-item active">
						</c:if>
						<c:if test="${pageContext.request.servletPath != '/views/jsp/userCredentialForm.jsp'}">
						<li class="nav-item">
						</c:if>
							<a class="nav-link"
							href="${pageContext.request.contextPath}/register">Register</a>
						</li>
					</c:if>
					<c:if test="${pageContext.request.userPrincipal.name != null}">
						<c:if test="${pageContext.request.servletPath == '/views/jsp/user.jsp'}">
						<li class="nav-item active">
						</c:if>
						<c:if test="${pageContext.request.servletPath != '/views/jsp/user.jsp'}">
						<li class="nav-item">
						</c:if>
							<a class="nav-link"
							href="${pageContext.request.contextPath}/user/${pageContext.request.userPrincipal.name}">My Account</a>
						</li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/login">Logout</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>