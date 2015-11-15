<!DOCTYPE html>
<%@page import="com.backend.pojos.Person"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Crisis Management Scheduler</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel=stylesheet href="css/schedule.css" type="text/css" media="screen">
<script src="scripts/tableStuff.js"></script>

</head>
<body onload="main();">
	<div class="temp">
		
		<!-- Top navigation bar -->
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a href="Surprise" class="navbar-brand">CRISIS MANAGER</a>
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
	
		<br>
		<h2 align="center" class="text">Weekly Schedule</h2>
        <br><br>
	
		<table id="myTable" class="yo">
		</table>
	</div>	
</body>
</html>
