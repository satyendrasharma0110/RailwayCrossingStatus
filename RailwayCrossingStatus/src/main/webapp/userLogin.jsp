<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Login</title>
<style>
body {
	display: flex;
	justify-content: center;
	align-items: center;
	text-align: center;
	background-color: #f2f2f2;
	background-image:url("https://source.unsplash.com/1000x1000/?nature,mountain");
	background-repeat: no-repeat;
	background-size: cover;
	align-items: center;
	height: 90vh;
	background-color: #f2f2f2;
}
.panel {
	width: 500px;
	padding: 40px;
	opacity:0.9;
	background-color: #ffffff;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(1, 1, 1, 1);
}
.panel label {
	display: block;
	margin-bottom: 10px;
	text-align: left;
}
.panel input[type="text"], .panel input[type="password"] {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
}
.panel button[type="submit"] {
	width: 100%;
	padding: 10px;
	background-color: gray;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
.panel button[type="submit"]:hover {
	background-color: #000000;
}
.panel .signup-link {
	display: block;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div class="panel">
		<h2>Railway Crossing</h2>
		<h4>User Login</h4>
		<form action="login" method="post">
			<label for="email">Email:</label> <input type="text" id="email"
				name="email" required><br> <label for="password">Password:</label>
			<input type="password" id="password" name="password" required><br><br>
			<button type="submit">Login</button>
		</form><br>
		<form action="HomePage.jsp" method="post">
			<button type="submit">Home Page</button>
		</form>
		<div class="signup-link">
			<p>
				Don't have an account? <a href="userRegister.jsp">Create New
					Account</a>
			</p>
		</div>
	</div>
</body>
</html>