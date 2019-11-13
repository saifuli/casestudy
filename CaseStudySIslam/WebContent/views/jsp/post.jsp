<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Post - ${post.getTitle()} - The GALLERY!</title>
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
	<p>${post.getPicture().getPath()}</p>
	<img src="/imageuploads/${post.getPicture().getName()}.jpg" />
	<br>
	<p>${post.getDescription()}</p>
	<%-- <c:if test="${post.getLikes() > 0}"> --%>
	<p>Likes: ${post.getLikes()}</p>
	
	<c:if test="${pageContext.request.userPrincipal.name == credential.getEmail() && liked == null}">
	<form
			action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/like"
			method="post">
			<input type="submit" value="Like" />
	</form>
	</c:if>
	<c:if test="${pageContext.request.userPrincipal.name == credential.getEmail() && liked != null}">
	<form
			action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/unlike"
			method="post">
			<input type="submit" value="UnLike" />
	</form>
	</c:if>
	<c:if
		test="${credential != null && post.getAuthor().getId() == credential.getId()}">

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
					<c:if test="${comment.getAuthor().getId() == credential.getId()}">
						<form:form
							action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/editcomment/${comment.getId()}"
							method="get">
							<input type="submit" value="Edit Comment" />
						</form:form>
					</c:if>
					<c:if
						test="${comment.getAuthor().getId() == credential.getId() || role == 'admin'}">
						<form
							action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/deletecomment/${comment.getId()}"
							method="post">
							<input type="submit" value="Delete Comment" />
						</form>
					</c:if>
				</c:if>
			</c:forEach>
	</div>

	<c:if test="${action != 'edit' }">
		<form
			action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/submitcomment"
			method="post">
			<textarea name="postComment" id="postComment"
				placeholder="add comment"></textarea>
			<input type="submit" value="Add Comment" />
		</form>

	</c:if>
	<c:if test="${action == 'edit' }">
		<form
			action="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}/editcomment/${comment.getId()}/submit"
			method="post">
			<textarea name="newComment" id="postComment">${comment.getComment()}</textarea>
			<input type="submit" value="Submit Comment" />
		</form>
	</c:if>
	</div>
	</div>
	</div>
	</div>
</body>
</html>