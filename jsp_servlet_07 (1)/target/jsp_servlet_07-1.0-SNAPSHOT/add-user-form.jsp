<!DOCTYPE html>
<html>
<head>
	<title>Add Menu</title>
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
	<h3>Add User</h3>

	<form action="UserController" method="POST" enctype="multipart/form-data">

		<input type="hidden" name="command" value="ADD" />

		<table>
			<tbody>
			<tr>
				<td><label>Name:</label></td>
				<td><input type="text" name="FullName" /></td>
			</tr>
			<tr>
				<td><label>Birthday:</label></td>
				<td><input type="text" name="Birthday" /></td>
			</tr>
			<tr>
				<td><label>Email:</label></td>
				<td><input type="text" name="Email" /></td>
			</tr>
			<tr>
				<td><label>Phone:</label></td>
				<td><input type="text" name="Phone" /></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input type="text" name="Password" /></td>
			</tr><tr>
				<td><label>Role:</label></td>
				<td><input type="text" name="Role" /></td>
			</tr><tr>
				<td><label>Address:</label></td>
				<td><input type="text" name="Address" /></td>
			</tr><tr>
				<td><label>ResetToken:</label></td>
				<td><input type="text" name="ResetToken" /></td>
			</tr>
			<tr>
				<td></td>
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
