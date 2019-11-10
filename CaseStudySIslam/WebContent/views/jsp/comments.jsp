<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form
		action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}"
		method="post">
		<p>
		<input type="submit" value="Delete" />
	</form>
	<div>
		<c:forEach var="comment" items="${posts.getComments()}">
			<div>
				<p>${comment}</p>
					<form
						action="${pageContext.request.contextPath}/gallery/edit/${post.getPicture().getName()}"
						method="get">
						
					</form>
			</div>
		</c:forEach>
	</div>
</body>
</html>