<!DOCTYPE html>
<html lang="en">
<head>
<title>CRISIS Friends</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
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
					<li><a href="ScheduleServ">My Schedule</a></li>
					<li class="active"><a href="FriendServ">Friends</a></li>
					<li><a href="@">Make a Meeting</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="Logout">Logout</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<h2>Friends</h2>
		<h2></h2>
		<div class="list-group">
			<a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Will Livesey</h4>
				<p class="list-group-item-text">Team Lead</p>
			</a> <a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Pawel Cieslewski</h4>
				<p class="list-group-item-text">Computer Engineer</p>
			</a> <a href="#" class="list-group-item">
				<h4 class="list-group-item-heading">Neil Garg</h4>
				<p class="list-group-item-text">Lead Product Design Integration Executive</p>
			</a>
		</div>
	</div>

	<div class="container">
		<form role="form" action="FriendServ" name="newFriendForm" method="post">
			<div class="form-group">
				<input name="friendGatorlink" type="Username" class="form-control" placeholder="Enter Friend's Gatorlink">
			</div>
			<input type="submit" value="Add Friend" class="btn btn-primary btn-block">
		</form>
		
		<!-- If the friend does exist, display success, else error. -->
		<%
			Boolean friendExists = (Boolean) request.getAttribute("friendExists");
			if (friendExists != null) {
				if (friendExists == true) {
		%>
					<div class="alert alert-success">
						<strong>Friend Successfully Added!</strong>
					</div>
		<%
				}
				else{
		%>
					<div class="alert alert-danger">
						<strong>Gatorlink does not exist.</strong>
					</div>
	
		<%	
				}
			}
		%>
		
	</div>

</body>
</html>
