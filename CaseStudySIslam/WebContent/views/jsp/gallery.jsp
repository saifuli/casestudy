<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gallery - The GALLERY!</title>
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
			<h1>List of posts</h1>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
					<a href="${pageContext.request.contextPath}/gallery/upload">Create
						a new post</a>
				</c:if>
				<p>${pageContext.request.userPrincipal.name}</p>
				<div class="test2">
					<c:if test="${posts.size() > 0}">
						<c:forEach var="post" items="${posts}">

							<a
								href="${pageContext.request.contextPath}/gallery/${post.getPicture().getName()}">
								<img src="/imageuploads/${post.getPicture().getName()}.jpg">
							</a>


						</c:forEach>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</body>
</html>