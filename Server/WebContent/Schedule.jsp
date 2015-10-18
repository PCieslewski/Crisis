<!DOCTYPE html>
<%@page import="com.backend.pojos.Person"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Crisis Management Scheduler</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

</head>
<body>

	<!-- Top navigation bar -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand">CRISIS MANAGER</a>
			</div>

			<div>
				<ul class="nav navbar-nav">
					<li class="active"><a href="@">My Schedule</a></li>
					<li><a href="@">Friends</a></li>
					<li><a href="@">Make a Meeting</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2 align="center">Current Schedule</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th align="center">Period</th>
					<th align="center">Sunday</th>
					<th align="center">Monday</th>
					<th align="center">Tuesday</th>
					<th align="center">Wednesday</th>
					<th align="center">Thursday</th>
					<th align="center">Friday</th>
					<th align="center">Saturday</th>
				</tr>
			</thead>
			<tbody>
			
				<%
				String days[] = {"SU","M","T","W","R","F","SA"};
				Person student = (Person)session.getAttribute("student");
				for(int i = 1; i <= 14; i++){
				%>
				<tr>
					<th><%=i %></th>
					<% for(int j = 0; j < 7; j++){ %>
					<td><%=student.getSchedule().getClassAt(days[j].toString(),Integer.toString(i))%></td>
					<% } %>
				</tr>
				<% } %>
				
			</tbody>
		</table>
	</div>
</body>
</html>
