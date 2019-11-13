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
	<jsp:include page="menu.jsp" />
	
	<form:form method="POST" id="picObj" modelAttribute="picObj"
		action="${pageContext.request.contextPath}/gallery/processUpload"
		enctype="multipart/form-data">
		<table>
			<tr>
				<td>File to upload: <input type="file" name="file"></td>
			</tr>
			<tr>
				<td><input type="text" name="title" id="title" placeholder="Enter Title"></td>
			</tr>

			<tr>
				<td><textarea rows="7" cols="70" name="description"
						id="description" placeholder="Describe the picture"></textarea></td>
			</tr>

			<tr>

					<td><form:button name="createPost" id="createPost">Create Post</form:button></td>

			</tr>
		</table>
	</form:form>

</body>
</html>