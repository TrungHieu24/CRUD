<!DOCTYPE html>
<html>

<head>
	<title>Update Student</title>

	<link type="text/css" rel="stylesheet" href="css/style.css">
	<link type="text/css" rel="stylesheet" href="css/add-student-style.css">	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>CodeLean Academy</h2>
		</div>
	</div>
	
	<div id="container">
		<h3>Update User</h3>
		
		<form action="UserController" method="GET">
		
			<input type="hidden" name="command" value="UPDATE" />

			<input type="hidden" name="menuId" value="${THE_USER.id}" />
			
			<table>
				<tbody>
					<tr>
						<td><label>FullName:</label></td>
						<td><input type="text" name="FullName"
								   value="${THE_USER.name}" /></td>
					</tr>

					<tr>
						<td><label>Birthday:</label></td>
						<td><input type="text" name="Birthday"
								   value="${THE_USER.birthday}" /></td>
					</tr>

					<tr>
						<td><label>Email:</label></td>
						<td><input type="text" name="Email"
								   value="${THE_USER.email}" /></td>
					</tr>

					<tr>
						<td><label>Phone:</label></td>
						<td><input type="text" name="Phone"
								   value="${THE_USER.phone}" /></td>
					</tr>

					<tr>
						<td><label>Password:</label></td>
						<td><input type="text" name="Password"
								   value="${THE_USER.passwords}" /></td>
					</tr>

					<tr>
						<td><label>Role:</label></td>
						<td><input type="text" name="Role"
								   value="${THE_USER.role}" /></td>
					</tr>

					<tr>
						<td><label>Address:</label></td>
						<td><input type="text" name="Address"
								   value="${THE_USER.address}" /></td>
					</tr>

					<tr>
						<td><label>ResetToken:</label></td>
						<td><input type="text" name="ResetToken"
								   value="${THE_USER.resetToken}" /></td>
					</tr>



					<tr>
						<td><label></label></td>
						<td><input type="submit" value="Save" class="save" /></td>
					</tr>
					
				</tbody>
			</table>
		</form>
		
		<div style="clear: both;"></div>
		
		<p>
			<a href="UserController">Back to List</a>
		</p>
	</div>
</body>

</html>











