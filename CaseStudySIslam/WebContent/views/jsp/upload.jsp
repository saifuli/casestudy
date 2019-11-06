<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registration</title>
<style type=text/css>
.error {
	color: red;
	font-size: small;
}
</style>
</head>
<body>
	<jsp:include page="menu.jsp"/>
	<%-- <p>${message}</p>
	<form:form id="userCredentialFormObj"
		modelAttribute="userCredentialFormObj"
		action="${pageContext.request.contextPath}/processUpload"
		method="POST">
		<table>
			<form:form id="picObj"
				modelAttribute="picObj"
				action="${pageContext.request.contextPath}/processUserCredential"
				method="POST">
				<tr>
					<td><form:label path="name" />Name</td>
					<td><form:input path="name" name="name" id="name" placeholder="Full Name" /></td>
					<td><form:errors path="name" cssClass="error" /></td>
				</tr>
			</form:form>
			<tr>
				<td><form:label path="email" />Email</td>
				<c:if test="${action == 'update'}">
					<td><form:input readonly="true" path="email" name="email" id="email" placeholder="Email" /></td>
				</c:if>
				<c:if test="${action == 'register'}">
					<td><form:input path="email" name="email" id="email" placeholder="Email" /></td>
				</c:if>
				<td><form:errors path="email" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="username" />Username</td>
				<td><form:input path="username" name="name" id="username" placeholder="Username" /></td>
				<td><form:errors path="username" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:label path="password" />Password</td>
				<td><form:password path="password" name="password"
						id="password" placeholder="Password" /></td>
				<td><form:errors path="password" cssClass="error" /></td>
				<td></td>
			</tr>
			<tr>
				<td><label>Re-enter Password:</label></td>
				<td><input type="password" name="confPassword" id="password"
					placeholder="Confirm Password" /></td>
				<td></td>
			</tr>
			<tr>
				<td></td>
				<c:if test="${action == 'update'}">
					<td><form:button name="edit" id="edit">Save</form:button></td>
					<td><a
						href="${pageContext.request.contextPath}/user?u=${userCredentialFormObj.getEmail()}"><input
							type="button" value="Cancel"></a></td>
				</c:if>
				<c:if test="${action == 'register'}">
					<td><form:button name="login" id="login">Register</form:button></td>
				</c:if>
			</tr>
		</table>

	</form:form> --%>
	 <form:form method = "POST" modelAttribute = "fileUpload"
         enctype = "multipart/form-data">
         Please select a file to upload : 
         <input type = "file" name = "file" />
         <input type = "submit" value = "upload" />
      </form:form>

</body>
</html>