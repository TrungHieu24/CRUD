<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<title>Menu Tracker App</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
	<!-- <link type="text/css" rel="stylesheet" href="css/list.css"> -->
</head>
<body>
<div id="wrapper">
	<header id="header">
		<h1>CodeLean Academy</h1>
	</header>
</div>

<div id="container">
	<div id="content">
		<!-- Add Menu button -->
		<input type="button" value="Add Menu" onclick="window.location.href='add-student-form.jsp'; return false;" class="add-student-button" />

		<table>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Image</th>
				<th>Action</th>
			</tr>
			<c:forEach var="tempMenu" items="${MENU_LIST}">
				<!-- Set up a link for each menu item -->
				<c:url var="tempLink" value="MenuController">
					<c:param name="command" value="LOAD"/>
					<c:param name="menuId" value="${tempMenu.id}"/>
				</c:url>
				<!-- Set up a link to delete a menu item -->
				<c:url var="deleteLink" value="MenuController">
					<c:param name="command" value="DELETE"/>
					<c:param name="menuId" value="${tempMenu.id}"/>
				</c:url>
				<tr>
					<td>${tempMenu.name}</td>
					<td>${tempMenu.price}</td>
					<td>${tempMenu.description}</td>
					<td>
						<c:if test="${not empty tempMenu.imagePath}">
							<img src="${pageContext.request.contextPath}/images/${tempMenu.imagePath}" alt="${tempMenu.name}" width="100" height="100"/>
						</c:if>
					</td>
					<td>
						<a href="${tempLink}" class="update-link">Update</a> |
						<a href="${deleteLink}" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this menu item?'))) return false">Delete</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>
