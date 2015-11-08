<!DOCTYPE html>
<html lang="en">
<head>
<title>CRISIS Friends</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel=stylesheet href="css/friends.css" type="text/css" media="screen">
<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Lato:100,300,400,700,900">

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
		<br>
		<ul class="nav nav-pills text2">
			<li><a data-toggle="pill" href="#friends" class="text">Friends</a></li>
			<li><a data-toggle="pill" href="#pending" class="text">Pending Friends <span class="badge"> 2</span></a></li>
			<li class="active"><a data-toggle="pill" href="#addFriends" class="text">Add Friends</a></li>
		</ul>

		<div class="tab-content">
			<div id="friends" class="tab-pane fade">
				<br>
				<br>
				<div class="list-group people">
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Will Livesey</h4>
						<p class="list-group-item-text">Steelerfan2010</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Pawel Cieslewski</h4>
						<p class="list-group-item-text">Pawel</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a> <a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Neil Garg</h4>
						<p class="list-group-item-text">NG38</p>
					</a>
				</div>
			</div>

			<div id="pending" class="tab-pane fade">
				<br>
				<br>
				<div class="people">
					<a href="#" class="list-group-item">
						<div class="">
							<h4 class="list-group-item-heading">Big Jake</h4>
							<p class="list-group-item-text">Dog123</p>
						</div>
						<div class="myButtons">
							<button class="accept" role="button">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
							<button class="reject" role="button">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</div>
					</a> <a href="#" class="list-group-item">
						<div class="">
							<h4 class="list-group-item-heading">Big Jake</h4>
							<p class="list-group-item-text">Dog123</p>
						</div>
						<div class="myButtons">
							<button class="accept" role="button">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
							<button class="reject" role="button">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</div>
					</a>
				</div>
			</div>

			<div id="addFriends" class="tab-pane fade in active addFriend text">
				<br><br><br><br><br><br>
				
				<script>
				function addFriend() {
				    $.ajax({
				        type: "POST", //Type of post
				        url: "AddFriend", //Where it is sent (Which servlet)
				        dataType: "json",
				        data: {gatorlink:$('#friendGatorlink').val()}, //This is sent TO THE SERVER
				        success: function (msg) { //Msg is returned FROM THE SERVER!
				            if (msg.error != 1) {
				                $("#test").text("Success");
				                $("#friendGatorlink").val("");
				            } else {
				            	$("#test").text("Failure");
				            	$("#friendGatorlink").val("");
				            }
				        }
				    });
				}
				</script>

				<div>
					<input id="friendGatorlink" type="text" class="form-control" placeholder="Enter Friend's Gatorlink">
				</div>
				<div id="test"></div>
				<button onclick="addFriend()" class="btn btn-default btn-block addFriendButton text">Add Friend</button>

			</div>
		</div>
	</div>

	<div class="temp col-xs-6">
		<br>
		<br>
		<br>
		<p>Todo:</p>
		<p>insert friend schedule here</p>
	</div>



</body>
</html>
