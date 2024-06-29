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
	<h3>Add Menu</h3>

	<form action="MenuController" method="POST" enctype="multipart/form-data">

		<input type="hidden" name="command" value="ADD" />

		<table>
			<tbody>
			<tr>
				<td><label>Name:</label></td>
				<td><input type="text" name="Name" /></td>
			</tr>
			<tr>
				<td><label>Price:</label></td>
				<td><input type="text" name="Price" /></td>
			</tr>
			<tr>
				<td><label>Description:</label></td>
				<td><input type="text" name="Description" /></td>
			</tr>
			<tr>
				<td><label>Image:</label></td>
				<td><input type="file" name="Image" /></td>
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
		<a href="MenuController">Back to List</a>
	</p>
</div>
</body>
</html>
