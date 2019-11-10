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
	<p>${post.getPicture().getPath()}</p>
	<img src="${post.getPicture().getPath()}" />
	<br>
	<p>${post.getDescription()}</p>
	<%-- <c:if test="${post.getLikes() > 0}"> --%>
	<p>Likes: ${post.getLikes()}</p>
	<p>Views: ${post.getViews()}</p>
	<c:if test="${credential != null && post.getAuthor().getId() == credential.getId()}">

	<form
		action="${pageContext.request.contextPath}/gallery/edit/${post.getPicture().getName()}"
		method="get">
		<input type="submit" value="Edit" />
	</form>
	<form
		action="${pageContext.request.contextPath}/gallery/delete/${post.getPicture().getName()}"
		method="post">
		<input type="submit" value="Delete" />
	</form>

	</c:if>
	
	<div>
		<p>${post.getComments().size()}
		<c:forEach var="comment" items="${post.getComments()}">
				<p>${comment.getComment()}</p>
				<p>${comment.getAuthor().getCredential().getUsername()}</p>
				<p>${comment.getTimestamp()}</p>
			<c:if test="${credential != null }">
			<p>testing if credential is not null</p>
			<p>Credential ID: ${credential.getId() }</p>
			<p>Comment Author ID: ${comment.getAuthor().getId() }</p>
			<c:if test="${comment.getAuthor().getId() == credential.getId() || role == 'admin'}">
			<form
						action="${pageContext.request.contextPath}/gallery/edit/${post.getPicture().getName()}"
						method="get">
						<input type="submit" value="Edit Comment" />
			</form>
			<form
						action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/deletecomment/${comment.getId()}"
						method="post">
						<input type="submit" value="Delete Comment" />
					</form>
					</c:if>
	</c:if>
		</c:forEach>
	</div>
	
	<form
		action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/submitcomment"
		method="post">
		<textarea name="postComment" id="postComment" placeholder="add comment"></textarea>
		<input type="submit" value="Add Comment" />
	</form>
</body>
</html>