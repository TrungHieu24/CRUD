<%@ page import="java.util.*, thidk.codelean.jdbc.*" %>
<!DOCTYPE html>
<html>
<head>
	<title>Menu Tracker App</title>
	<link type="text/css" rel="stylesheet" href="css/style.css">
</head>
<body>
<div id="wrapper">
	<div id="header">
		<h2>CodeLean Academy</h2>
	</div>
</div>
<div id="container">
	<div id="content">
		<!-- Add Menu button -->
		<input type="button" value="Add Menu" onclick="window.location.href='add-menu-form.jsp'; return false;" class="add-student-button" />

		<table>
			<tr>
				<th>Name</th>
				<th>Price</th>
				<th>Description</th>
				<th>Image</th>
				<th>Action</th>
			</tr>
			<%
				// get the menu items from the request object (sent by servlet)
				List<Menu> theMenu = (List<Menu>) request.getAttribute("MENU_LIST");
				for (Menu tempMenu : theMenu) {
			%>
			<tr>
				<td><%= tempMenu.getName() %></td>
				<td><%= tempMenu.getPrice() %></td>
				<td><%= tempMenu.getDescription() %></td>
				<td>
					<%
						if (tempMenu.getImagePath() != null && !tempMenu.getImagePath().isEmpty()) {
					%>
					<img src="images/<%= tempMenu.getImagePath() %>" alt="<%= tempMenu.getName() %>" width="100" height="100"/>
					<%
						}
					%>
				</td>
				<td>
					<a href="MenuController?command=LOAD&menuId=<%= tempMenu.getId() %>" class="update-link">Update</a> |
					<a href="MenuController?command=DELETE&menuId=<%= tempMenu.getId() %>" class="delete-link" onclick="if (!(confirm('Are you sure you want to delete this menu item?'))) return false">Delete</a>
				</td>
			</tr>
			<% } %>
		</table>
	</div>
</div>
</body>
</html>
