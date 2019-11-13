<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit - ${post.getTitle()} - The GALLERY!</title>
</head>
<body>
	<p>${picture.getName() }</p>
	<form:form method="POST" id="picture" modelAttribute="picture"
		action="${pageContext.request.contextPath}/gallery/edit/${picture.getName()}">
		<table>
			<tr>
				<td><h3>${post.getTitle()}</h3></td>
			<tr>
				<td><img src="${picObj.getPath()}"/></td>
			</tr>

			<form:form method="POST" id="post" modelAttribute="post"
				action="${pageContext.request.contextPath}/gallery/edit/${picture.getName()}">
				<tr>
					<td><textarea rows="7" cols="70"
							name="description" id="description"
							placeholder="Describe the picture"></textarea></td>
				</tr>

			</form:form>
			<tr>
					<td><form:button name="updatePost" id="updatePost">Update Post</form:button></td>

			</tr>
		</table>
	</form:form>
</body>
</html>