<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.railwaycrossing.pojo.RailwayCrossing"%>
<%@ page import="com.railwaycrossing.dao.RailwayCrossingDatabaseOp"%>
<%@ page import="java.util.List"%>

<%
RailwayCrossingDatabaseOp crossingDAO = new RailwayCrossingDatabaseOp();
List<RailwayCrossing> crossings = crossingDAO.getAllCrossings();
String searchId = request.getParameter("searchId");
if (searchId != null && !searchId.isEmpty()) {
	int crossingId = Integer.parseInt(searchId);
RailwayCrossing searchedCrossing = crossingDAO.getCrossingById(crossingId);
	request.setAttribute("crossing", searchedCrossing);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Home Page</title>
<style>
/* Button Styles */
.button-container {
	display: inline-block;
	justify-content: flex-start;
	margin-bottom: 10px;
	margin-left: 1px;
}
.button-container form {
	margin-right: 100px;
}
.button-container input[type="text"] {
	margin-right: 5px;
	padding: 5px;
	border-radius: 4px;
	border: 1px solid #ccc;
	display: inline-block;   
}
.button-container button[type="submit"] {
	padding: 10px 20px;
	border-radius: 4px;
	border: none;
	background-color: #B6B6B6;
	font-weight: bold;
	color: black;
	cursor: pointer;
}
.button-container button[type="submit"]:hover {
	background-color: #45a049;
}
.update-button, .delete-button {
	display: inline-block;
	vertical-align: middle;
	margin-right: 10px;
	padding: 7px;
	width: 80px;
	height: 30px;
}
table {
	border-collapse: collapse;
	width: 100%;
	
}
th, td {
	text-align: left;
	padding: 8px;
	border-bottom: 1px solid #ddd;
}
th {
	background-color: #f2f2f2;
}
.inline {
        display: inline-block;
    }
</style>
</head>
<body>
	<h1 style="font-family: Times New Roman;" align=center>Admin Home Page</h1>
	<h3>
		Number of Railway Crossing : <%=crossings.size()%>
	</h3>

	<button onclick="location.href='HomePage.jsp'" style="border-radius: 5px; font-weight: bold; padding: 5px 10px;">
	Home</button>

	<button onclick="location.href='adminHome.jsp'"
		style="border-radius: 5px; margin-left: 50px; font-weight: bold; padding: 5px 10px;">
		Display All Railway Crossings</button>

	<button onclick="location.href='addRailCrossingForm.jsp'" style="border-radius: 5px;
	margin-left: 50px; font-weight: bold; padding: 5px 10px;">Add Railway Crossing</button>

	<button onclick="location.href='adminLogin.jsp'"
		style="border-radius: 5px; margin-left: 700px; font-weight: bold; padding: 5px 10px;">Logout</button><br><br>


	<form action="searchCrossing" method="post">

			<button type="submit" style="border-radius: 5px; font-weight: bold; padding: 5px 10px;">
			Search Crossing</button>
			<input type="text" name="searchId" placeholder="Enter ID" style="padding: 5px 10px" required>
	</form><br>

	<table>
		<thead>
			<tr>
				<th>Sr.No</th>
				<th>Name</th>
				<th>Address</th>
				<th>Landmark</th>
				<th>Train Schedule</th>
				<th>Person In-Charge</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
			if (request.getAttribute("crossing") != null) { 
				RailwayCrossing crossing = (RailwayCrossing) request.getAttribute("crossing");
			%>
			<tr>
				<td><%=crossing.getId()%></td>
				<td><%=crossing.getName()%></td>
				<td><%=crossing.getAddress()%></td>
				<td><%=crossing.getLandmark()%></td>
				<td><%=crossing.getTrainSchedule()%></td>
				<td><%=crossing.getPersonInCharge()%></td>
				<td><%=crossing.getStatus()%></td>
				<td></td>
			</tr>
			<%
			} else { 
			for (RailwayCrossing anotherCrossing : crossings) {
			%>
			<tr>
				<td><%=anotherCrossing.getId()%></td>
				<td><%=anotherCrossing.getName()%></td>
				<td><%=anotherCrossing.getAddress()%></td>
				<td><%=anotherCrossing.getLandmark()%></td>
				<td><%=anotherCrossing.getTrainSchedule()%></td>
				<td><%=anotherCrossing.getPersonInCharge()%></td>
				<td><%=anotherCrossing.getStatus()%></td>
				<td>
					<form action="updateRailCrossingForm.jsp" method="post">
						<input type="hidden" name="id"
							value="<%=anotherCrossing.getId()%>">
						<button type="submit" class="update-button">Update</button>
					</form>
					<form action="deleteCrossing" method="post">
						<input type="hidden" name="id"
							value="<%=anotherCrossing.getId()%>">
						<button type="submit" class="delete-button">Delete</button>
					</form>
				</td>
			</tr>
			<%
			}
			}
			%>
		</tbody>
	</table>
</body>
</html>