<%@ page import="java.util.*,thidk.codelean.jdbc.*" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Student Tracker App</title>
		<link type="text/css" rel="stylesheet" href="css/style.css">
	</head>
	<%
		// get the students from the request object (sent by servlet)
		List<Menu> theMenu =
						(List<Menu>) request.getAttribute("MENU_LIST");
	%>
	<body>
		<div id="wrapper">
			<div id="header">
				<h2>CodeLean Academy</h2>
			</div>
		</div>
		<div id="container">
			<div id="content">
				<table>
					<tr>
						<th>Name</th>
						<th>Description</th>
					</tr>
					<% for (Menu tempMenu : theMenu) { %>
						<tr>
							<td> <%= tempMenu.getName() %> </td>
							<td> <%= tempMenu.getDescription() %> </td>
						</tr>
					<% } %>
				</table>
			</div>
		</div>
	</body>
</html>








