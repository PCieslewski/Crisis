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
					<li class="active"><a href="ScheduleServ">My Schedule</a></li>
					<li><a href="FriendServ">Friends</a></li>
					<li><a href="@">Make a Meeting</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2 align="center">Weekly Schedule</h2>
		<br><br>
		<table class="table table-striped">
			<thead>
				<tr>
					<th align="center">Period</th>
					<th align="center">Time</th>
					<th align="center">Monday</th>
					<th align="center">Tuesday</th>
					<th align="center">Wednesday</th>
					<th align="center">Thursday</th>
					<th align="center">Friday</th>
				</tr>
			</thead>
			<tbody>
			
				<%!
					public static String convertToTime(int i) {
						if(i == 1) {
							return "7:25am";
						}
						else if(i == 2) {
							return "8:30am";
						}
						else if(i == 3) {
							return "9:35am";
						}
						else if(i == 4) {
							return "10:40am";
						}
						else if(i == 5) {
							return "11:45am";
						}
						else if(i == 6) {
							return "12:50pm";
						}
						else if(i == 7) {
							return "1:55pm";
						}
						else if(i == 8) {
							return "3:00pm";
						}
						else if(i == 9) {
							return "4:05pm";
						}
						else if(i == 10) {
							return "5:10pm";
						}
						else if(i == 11) {
							return "6:15pm";
						}
						else if(i == 12) {
							return "7:20pm";
						}
						else if(i == 13) {
							return "8:20pm";
						}
						else if(i == 14) {
							return "9:20pm";
						}
						else {
							return "error";
						}
					}
				%>
			
				<%
				String days[] = {"M","T","W","R","F"};
				Person student = (Person)session.getAttribute("student");
				for(int i = 1; i <= 14; i++){
				%>
				
				<tr>
					<th><%=i %></th>
					<th><%=convertToTime(i) %></th>
					<% for(int j = 0; j < 5; j++){ %>
					<td><%=student.getSchedule().getClassAt(days[j].toString(),Integer.toString(i))%></td>
					<% } %>
				</tr>
				<% } %>
				
			</tbody>
		</table>
	</div>
</body>
</html>
