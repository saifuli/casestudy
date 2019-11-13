<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update ${credential.getUsername()} - The GALLERY!</title>
<link href="<c:url value='/resources/css/bootstrap.css' />"
	rel="stylesheet">
<link href="<c:url value='/resources/css/gallery.css' />"
	rel="stylesheet">
<script src="<c:url value="/resources/js/bootstrap.js"/>" defer></script>
<script src="<c:url value="/resources/js/jquery-3.1.1.js"/>" defer></script>
<script src="<c:url value="/resources/js/popper.min.js"/>" defer></script>
<style type=text/css>
.error {
	color: red;
	font-size: small;
}
</style>
</head>
<body>
	<div class="container rounded">
		<div class="test rounded border border-dark">
			<jsp:include page="menu.jsp" />
			<div class="container-fluid">
				<div class="test2">
					<p>${message}</p>
					<form:form id="userCredentialFormObj"
						modelAttribute="userCredentialFormObj"
						action="${pageContext.request.contextPath}/updateUserCredential"
						method="POST">
						<table>
							<tr>
								<td><form:label path="email" />Email</td>

								<td><form:input readonly="true" path="email" name="email"
										id="email" placeholder="Email" /></td>


								<td><form:errors path="email" cssClass="error" /></td>
							</tr>
							<tr>
								<td><form:label path="username" />Username</td>

								<td><form:input readonly="true" path="username" name="name"
										id="username" placeholder="Username" /></td>

								<td><form:errors path="username" cssClass="error" /></td>
							</tr>
							<form:form id="userFormObj" modelAttribute="userFormObj"
								action="${pageContext.request.contextPath}/updateUserCredential"
								method="POST">
								<tr>
									<td><form:label path="name" />Name</td>
									<td><form:input path="name" name="name" id="name"
											placeholder="Full Name" /></td>
									<td><form:errors path="name" cssClass="error" /></td>
								</tr>
							</form:form>
							<tr>
								<td><form:label path="password" />Password</td>
								<td><form:password path="password" name="password"
										id="password" placeholder="Password" /></td>
								<td><form:errors path="password" cssClass="error" /></td>
								<td></td>
							</tr>
							<tr>
								<td><label>Re-enter Password:</label></td>
								<td><input type="password" name="confPassword"
									id="password" placeholder="Confirm Password" /></td>
								<td></td>
							</tr>
							<tr>


								<td><form:button name="edit" id="edit">Save</form:button></td>
								<td><a
									href="${pageContext.request.contextPath}/user/${userCredentialFormObj.getEmail()}"><input
										type="button" value="Cancel"></a></td>
							</tr>
						</table>
					</form:form>
				</div>
			</div>
		</div>
	</div>

</body>
</html>