<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Railway Crossing Status</title>
<style>
body {
	background-image:url("https://source.unsplash.com/1000x1000/?nature,mountain");
	background-repeat: no-repeat;
	background-size: cover;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 90vh;
	text-align: center;
	background-color: #f2f2f2;
}
.panel {
	width: 500px;
	padding: 50px;
	background-color: white;
	opacity: 0.5;
	border-radius: 10px;
	box-shadow: 0 2px 5px rgba(100, 10, 10, 1000);
}
.panel button[type="submit"] {
	width: 60%;
	padding: 10px;
	background-color: black;
	
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}
.panel button[type="submit"]:hover {
	background-color: #000000;
	opacity: 0.9;
}
</style>
</head>
<body>
		<div class="panel">
		<h2>Railway Crossings Status</h2>
		<h4>Developed by saurabh gupta</h4><br>

		<form action="adminLogin.jsp" method="post">
			<button type="submit">Government-Admin</button>
		</form><br>
		<form action="userLogin.jsp" method="post">
			<button type="submit">User</button>
		</form>				

		</div>
</body>
</html>