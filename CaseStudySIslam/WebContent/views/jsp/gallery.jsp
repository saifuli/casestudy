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
	<jsp:include page="menu.jsp" />
	<h1>List of posts</h1>
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<a href="${pageContext.request.contextPath}/gallery/upload">Create
			a new post</a>
	</c:if>
	<p>${pageContext.request.userPrincipal.name}</p>

	<div>
		<table>
			<c:if test="${posts.size() > 0}">
				<c:forEach var="post" items="${posts}">
					<tr>
						<td>
							<p>${post.getPicture().getPath()}</p> <img
							src="${post.getPicture().getPath()}" /> <br>
							<p>${post.getDescription()}</p> <%-- <c:if test="${post.getLikes() > 0}"> --%>
							<p>Likes: ${post.getLikes()}</p> <a
							href="${pageContext.request.contextPath}/gallery/edit/${post.getPicture().getName()}">Edit</a>
							<form action="${pageContext.request.contextPath}/gallery/delete/${post.getPicture().getName()}" method="post">
								<input type="submit" value="Delete" />
							</form>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</table>
	</div>
</body>
</html>