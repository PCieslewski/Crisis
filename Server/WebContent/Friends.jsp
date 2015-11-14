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

<link rel=stylesheet href="css/displayFriends.css" type="text/css" media="screen">
<script src="scripts/tableStuff.js"></script><base>

<script>
function updatePending(list) {
    var len = list.length;
    document.getElementById('pendingFriends').innerHTML = '';
    
    for(var i=0; i<len; i++){
    	document.getElementById('pendingFriends').innerHTML += 
    	'<a href="#" class="list-group-item">'+
			'<div class="">'+ 
				'<h4 class="list-group-item-heading">'+list[i]+'</h4>'+
				'<p class="list-group-item-text"> a </p>'+
			'</div>'+
			'<div class="myButtons">'+
				'<button class="accept" role="button" onclick="acceptFriend(\''+list[i]+'\');">'+
					'<span class="glyphicon glyphicon-ok"></span>'+
				'</button>'+
				'<button class="reject" role="button" onclick="rejectFriend(\''+list[i]+'\');">'+
					'<span class="glyphicon glyphicon-remove"></span>'+
				'</button>'+
			'</div>'+
		'</a>';
    }
}

function updateFriends(list) {
	var len = list.length;
    document.getElementById('normalFriends').innerHTML = '';
    var a = "poop";
    for(var i=0; i<len; i++){
    	document.getElementById('normalFriends').innerHTML += 
    	'<a href="#" class="list-group-item">'+
			'<h4 class="list-group-item-heading">'+list[i]+'</h4>'+
			'<p class="list-group-item-text">Fun</p>'+
			'<input class="myButtons" onclick="handleClick(this, \''+list[i]+'\');" type="checkbox">' +
		'</a>';
    }
}

var people = [];

function handleClick(cb, name) {
	if(cb.checked) {
		people.push(name);
	}
	else {
		var index = people.indexOf(name);
		people.splice(index,1);
	}
	clearSchedule();
	for(var i = 0; i < people.length; i++) {
		asyncGetPerson(people[i]);
	}
}

//WILL LOOK HERE ----------------------------------------------------------------FOR WILL
function asyncGetPerson(gatorlink) {
	$.ajax({
        type: "POST", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json",
        data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			
        	var student = msg; //student is the person we queried for given gatorlink string.
        	addPerson(student);
        	//ADD PERSON TO SCHEDULE
 //       	console.log(student.name);
        	
        }
    });
}

function addPerson(will) {
	for(var i = 0; i < will.schedule.classList.length; i++) {
		var courseCode = will.schedule.classList[i].course;
		var days = will.schedule.classList[i].day;
		var periods = will.schedule.classList[i].period;
		
		var all = work(courseCode, days, periods);
		updateTable2(all);
	}
}

function updateTable2(courseAndIndex) {
    for(var i = 0; i < courseAndIndex.length; i++) {
        var parse = courseAndIndex[i].split("@");
        fillTable(parse[1]);
    }
}

function fillTable(id) {
	if(id < 15) {
		//I'm embarrassed will be in production
	}
	else {
		document.getElementById(id).className = "coolPattern";
	}
}

function clearSchedule() {
	 for(var i = 15; i < 85; i++) {
		 document.getElementById(i).innerHTML = "";
		 document.getElementById(i).className = "";
	 }
	 addClass();
}

function acceptFriend(gatorlink) {
	$.ajax({
        type: "POST", //Type of post
        url: "AcceptFriend", //Where it is sent (Which servlet)
        dataType: "json",
        data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			updateAll();
        }
    });
}

function rejectFriend(gatorlink) {
	$.ajax({
        type: "POST", //Type of post
        url: "RejectFriend", //Where it is sent (Which servlet)
        dataType: "json",
        data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			updateAll();
        }
    });
}

function updateAll() {
	$.ajax({
        type: "GET", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json",
        //data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			updatePending(msg.pendingFriends);
        	updateFriends(msg.friends);
        }
    });
}

</script>

</head>
<body onload="updateAll(); friendsMain(); clearSchedule">
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

<div class="col-xs-4">
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
				<div class="list-group people" id="normalFriends">
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Will Livesey</h4>
						<p class="list-group-item-text">Steelerfan2010</p>
					</a> 
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Pawel Cieslewski</h4>
						<p class="list-group-item-text">Pawel</p>
					</a>
				</div>
			</div>

			<div id="pending" class="tab-pane fade">
				<br>
				<br>
				<div class="pendingFriends" id="pendingFriends"></div>
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
				        	updateAll();
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
	
	</div>
	<div class="col-xs-8">
		<br>
		<h2 align="center" class="header">Friends' Schedules</h2>
        <br><br>
        
		<table id="myTable" class="yo1">
		</table>
	</div>

	<script>
	
//	   ______          _______
//	  /       \      /        \
//	/           \  /            \
// |			 | |             |
// |			 | |             |
//	\           /   \           /
//	  \_______/       \_______/
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	         |          |
//	       __|__________|__
//	      /                \
//	     | 				   |
//	     |				   |
//	      \		  |		  /
//	       \______|______/
//	      
	     
	   
//	$(document).ready(function () {
//	   $('input[type=checkbox]').change(function() {
//	      alert($(this).attr('id') + " box clicked...");
//	   });
//	});
	</script>
</body>
</html>
