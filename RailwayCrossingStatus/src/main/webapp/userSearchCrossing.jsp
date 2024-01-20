<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.railwaycrossing.pojo.RailwayCrossing"%>
<%@ page import="com.railwaycrossing.dao.RailwayCrossingDatabaseOp"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Railway Crossings - Search Crossings</title>
<style>
body {
	font-family: Arial, sans-serif;
	margin: 0;
	padding: 20px;
}
.container {
	width: 500px;
	border: 1px solid #ccc;
	border-radius: 10px;
	padding: 20px;
	margin-bottom: 20px;
	margin-top: 20px;
	box-shadow: 0 2px 5px rgba(0, 1, 1, 1);
}
.container h3 {
	margin-top: 0;
}
.container p {
	margin: 0;
}
.search-container {
	margin-top: 60px;
	margin-bottom: 20px;
}
.search-container input[type="text"] {
	padding: 10px;
	width: 200px;
}
.logout-button {
	position: absolute;
	padding: 10px;
	top: 50px;
	right: 30px;
	border-radius: 10px;
	font-weight: bold;
	margin-top: 20px;
	top: 50px;
}
.allCrossing-button {
	position: absolute;
	padding: 5px;
	top: 75px;
	left: 10px;
}
.favouriteCrossing-button {
	position: absolute;
	padding: 5px;
	top: 75px;
	left: 130px;
}
.status {
	display: inline-block;
	padding: 5px;
	font-weight: bold;
}
.status-open {
	color: white;
	border-radius: 15%;
	background-color: green;
}
.status-closed {
	color: white;
	border-radius: 15%;
	background-color: red;
}
.crossing {
	margin-bottom: 10px;
}
.crossing button {
	margin-left: 10px;
}
</style>
<script>
	function searchCrossings() {
		var input = document.getElementById("searchInput");
		var filter = input.value.toUpperCase();
		var crossings = document.getElementsByClassName("crossing");
		for (var i = 0; i < crossings.length; i++) {
			var crossing = crossings[i];
			var name = crossing.getElementsByTagName("h3")[0].innerText;
			if (name.toUpperCase().indexOf(filter) > -1) {
				crossing.style.display = "";
			} else {
				crossing.style.display = "none";
			}
		}
	}
</script>
</head>
<body>
	<h1 style="font-family: Times New Roman; margin-top: 5px;" align=center >Search Crossings</h1>

	<button onclick="location.href='userHome.jsp'"
		class="allCrossing-button"
		style="border-radius: 5px; font-weight: bold; padding: 5px 10px;">All Crossings</button>


	<button onclick="location.href='userHomeFavoriteCrossing.jsp'"
		class="favouriteCrossing-button"
		style="border-radius: 5px; font-weight: bold; padding: 5px 10px;">Favorite
		Crossings</button>


	<div class="search-container">
		<input type="text" id="searchInput" placeholder="Search by name"
			onkeyup="searchCrossings()">
		<button onclick="location.href='userLogin.jsp'" class="logout-button"
			style="border-radius: 5px; font-weight: bold; padding: 5px 10px;">Logout</button>
	</div>



	<div class="search-results">
		<%
		RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
				List<RailwayCrossing> allCrossings = crossingDAO.getAllCrossings();
				for (RailwayCrossing crossing : allCrossings) {
			String status = crossing.getStatus();
			String statusClass = status.equalsIgnoreCase("Open") ? "status-open" : "status-closed";
		%>
		<div class="container crossing">
			<h3 style="font-family: Times New Roman;"><%=crossing.getName()%></h3>
			<p>
				Status: <strong><span class="status <%=statusClass%>"><%=crossing.getStatus()%></span></strong>
			</p>
			<p>
				Person in Charge: <strong><%=crossing.getPersonInCharge()%></strong>
			</p>
			<p>
				Train Schedules: <strong><%=crossing.getTrainSchedule()%></strong>
			</p>
			<p>
				Landmark: <strong><%=crossing.getLandmark()%></strong>
			</p>
			<p>
				Address: <strong><%=crossing.getAddress()%></strong>
			</p>
			<form action="addToFavorites" method="post" style="display: inline;">
				<input type="hidden" name="crossingId" value="<%=crossing.getId()%>">
				<button type="submit"
					style="border-radius: 5px; font-weight: bold; margin-top: 10px; margin-left: 0px; padding: 5px 10px;">ADD
					TO FAVORITES</button>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>