<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.railwaycrossing.pojo.RailwayCrossing"%>
<%@ page import="com.railwaycrossing.dao.RailwayCrossingDatabaseOp"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Railway Crossings - Favorite Crossings</title>
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
.status {
	display: inline-block;
	padding: 5px;
	color: white;
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
</style>
</head>
<body>
	<h1 style="font-family: Times New Roman;" align=center>User Home Page</h1>


	<button onclick="location.href='userHome.jsp'" style="border-radius: 5px; font-weight: bold;
	margin-top: 10px; padding: 5px 10px;">All Crossings</button>


	<div class="favorites">
		<%
		RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
				List<RailwayCrossing> favoriteCrossings = crossingDAO.getFavoriteCrossings();
				for (RailwayCrossing crossing : favoriteCrossings) {
			String status = crossing.getStatus();
			String statusClass = status.equalsIgnoreCase("Open") ? "status-open" : "status-closed";
		%>
		<div class="container">
			<h3 style="font-family: Times New Roman;"><%=crossing.getName()%></h3>
			<p>
				Status: <strong><span class="status <%=statusClass%>"><%=status%></span></strong>
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
			<form action="removeFromFavorites" method="post"
				style="display: inline;">
				<input type="hidden" name="crossingId" value="<%=crossing.getId()%>">
				<button type="submit"
					style="border-radius: 5px; font-weight: bold; margin-top: 10px; padding: 5px 10px;">REMOVE
					FROM FAVORITES</button>
			</form>
		</div>
		<%
		}
		%>
	</div>
</body>
</html>