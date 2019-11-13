<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
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
					<p>${message}</p>
					<form action='<spring:url value="/loginAction" />' method="post">
						<div class="form-group row">
							<label for="inputEmail3" class="col-sm-2 col-form-label">Email</label>
							<div class="col-sm-10">
								<input type="email" name="username" class="form-control"
									id="inputEmail3" placeholder="Email">
							</div>
						</div>
						<div class="form-group row">
							<label for="inputPassword3" class="col-sm-2 col-form-label">Password</label>
							<div class="col-sm-10">
								<input type="password" name="password" class="form-control"
									id="inputPassword3" placeholder="Password">
							</div>
						</div>
						<div class="form-group row">
							<div class="col-sm-10">
								<button type="submit" class="btn btn-primary">Sign in</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>