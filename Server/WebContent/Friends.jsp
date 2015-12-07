<!DOCTYPE html>
<html lang="en">
<head>
<title>CRISIS Friends</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="https://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

<link rel=stylesheet href="css/friends.css" type="text/css" media="screen">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900">

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
				'<p class="list-group-item-text" id='+list[i]+'-name-pend>Full Name</p>'+
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
    	updateName(list[i]);
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
			'<p class="list-group-item-text" id='+list[i]+'-name-friend>Full Name</p>'+
			'<input class="myButtons" onclick="handleClick(this, \''+list[i]+'\');" type="checkbox">' +
		'</a>';
		updateName(list[i]);
    }
}
 
var myId;
var people = [];
var self = false;

function handleClick(cb, name) {
	if(cb.checked) {
		people.push(name);
	}
	else {
		var index = people.indexOf(name);
		people.splice(index,1);
	}
	clearSchedule();
	if(self) {
		asyncGetSelf();
	}
	for(var i = 0; i < people.length; i++) {
		asyncGetPerson(people[i]);
	}
}

function seeMySchedule(cb) {
	if(cb.checked) {
//		people.push(name);
		//asyncGetPerson()
		asyncGetSelf();
		self = true;
	}
	else {
		self = false;
		clearSchedule();
		for(var i = 0; i < people.length; i++) {
			asyncGetPerson(people[i]);
		}
	}
}

function asyncGetSelf() {
	$.ajax({
        type: "GET", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json",
        success: function (msg) { //Msg is returned FROM THE SERVER!
			
        	var student = msg; //student is the person we queried for given gatorlink string.
        	addPerson(student, 2);
        }
    });
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
        	addPerson(student, 1);
        	//ADD PERSON TO SCHEDULE
 //       	console.log(student.name);
        	
        }
    });
}

function updateName(gatorlink) {
	$.ajax({
        type: "POST", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json",
        data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			
        	var student = msg; //student is the person we queried for given gatorlink string.
        	
        	var nameField = document.getElementById(gatorlink+"-name-friend");
        	if(nameField != null){
        		nameField.innerHTML = student.name;
        	}
        	
        	nameField = document.getElementById(gatorlink+"-name-pend");
        	if(nameField != null){
        		nameField.innerHTML = student.name;
        	}
        	
        	//document.getElementById(i).innerHTML = "";
        	
        }
    });
}

function addPerson(will, number) {
	for(var i = 0; i < will.schedule.classList.length; i++) {
		var courseCode = will.schedule.classList[i].course;
		var days = will.schedule.classList[i].day;
		var periods = will.schedule.classList[i].period;
		
		var all = work(courseCode, days, periods);
		updateTable2(all, number);
	}
}

function updateTable2(courseAndIndex, number) {
    for(var i = 0; i < courseAndIndex.length; i++) {
        var parse = courseAndIndex[i].split("@");
        fillTable(parse[1], number);
    }
}

function fillTable(id, number) {
	if(id < 15) {
		//I'm embarrassed will be in production
	}
	else {
		if(number == 2) {
			document.getElementById(id).className = "selfPattern";
		}
		else {
			document.getElementById(id).className = "coolPattern";
		}
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
			updateFriendList();
        }
    });
}

function updateFriendList() {
	$.ajax({
        type: "GET", //Type of post
        url: "GetPersonJson", //Where it is sent (Which servlet)
        dataType: "json",
        //data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
        success: function (msg) { //Msg is returned FROM THE SERVER!
			updatePending(msg.pendingFriends);
        	updateFriends(msg.friends);
        	updatePendingMeetings(msg.pendingMeetings)
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
        	//updateFriends(msg.friends);
        	updatePendingMeetings(msg.pendingMeetings)
        }
    });
}

function addPendingEvent(gatorlinks, day, period, title, building, room) {
	$.ajax({
        type: "POST", 
        url: "AddPendingEvent", 
        dataType: "json",
        data: {
        	'gatorlinks':gatorlinks,
        	'day':day,
        	'period':period,
        	'title':title,
        	'building':building,
        	'room':room
        },
        success: function (student) { //The response is the updated student object.
        	updateAll();
        	//redraw
        	
        	var index = getTableIndex(day, period) + 14;
        	fillTable(index,2);
        }
    });
}

function removePendingEvent(day, period, title, building, room) {
	$.ajax({
        type: "POST", 
        url: "RemovePendingEvent", 
        dataType: "json",
        data: {
        	'day':day,
        	'period':period,
        	'title':title,
        	'building':building,
        	'room':room
        },
        success: function (student) { //The response is the updated student object.
        	updateAll();
        }
    });
}

function addEvent(day, period, title, building, room) {
	$.ajax({
        type: "POST", 
        url: "AddEvent", 
        dataType: "json",
        data: {
        	'day':day,
        	'period':period,
        	'title':title,
        	'building':building,
        	'room':room
        },
        success: function (student) { //The response is the updated student object.
        	removePendingEvent(day, period, title, building, room);
        }
    });
}

function acceptMeeting(day, period, title, building, room){
	addEvent(day, period, title, building, room);
}

function rejectMeeting(day, period, title, building, room){
	removePendingEvent(day, period, title, building, room);
}

function updatePendingMeetings(list) {
    var len = list.length;
    document.getElementById('meetings').innerHTML = '';
    
    for(var i=0; i<len; i++){
    	//var event = JSON.stringify(list[i].event);
    	var day = list[i].event.day;
    	var period = list[i].event.period;
    	var title = list[i].event.course;
    	var building = list[i].event.building;
    	var room = list[i].event.room;
    	var args = '\''+day+'\',\''+period+'\',\''+title+'\',\''+building+'\',\''+room+'\'';
    	
    	document.getElementById('meetings').innerHTML += 
    	'<a href="#" class="list-group-item">'+
			'<div class="">'+ 
				'<h4 class="list-group-item-heading">'+list[i].event.course+'</h4>'+
				'<p class="list-group-item-text">'+list[i].whoInvitedYou+'</p>'+
			'</div>'+
			'<div class="myButtons">'+
				'<button class="accept" role="button" onclick="acceptMeeting('+args+');">'+
					'<span class="glyphicon glyphicon-ok"></span>'+
				'</button>'+
				'<button class="reject" role="button" onclick="rejectMeeting('+args+');">'+
					'<span class="glyphicon glyphicon-remove"></span>'+
				'</button>'+
			'</div>'+
		'</a>';
    }
}

function getDayFromId(someId) {
	if((someId > 14) && (someId < 29)) {
		return "M";
	}
	else if((someId > 28) && (someId < 43)) {
		return "T";
	}
	else if((someId > 42) && (someId < 57)) {
		return "W";
	}
	else if((someId > 56) && (someId < 71)) {
		return "R";
	}
	else if((someId > 70) && (someId < 85)) {
		return "F";
	}
	else {
		return "ERROR";
	}
}

function getPeriodFromId(someId) {
	if(someId %14 == 1) {
		return "1";
	}	
	else if(someId %14 == 2) {
		return "2";
	}
	else if(someId %14 == 3) {
		return "3";
	}
	else if(someId %14 == 4) {
		return "4";
	}
	else if(someId %14 == 5) {
		return "5";
	}
	else if(someId %14 == 6) {
		return "6";
	}
	else if(someId %14 == 7) {
		return "7";
	}
	else if(someId %14 == 8) {
		return "8";
	}
	else if(someId %14 == 9) {
		return "9";
	}
	else if(someId %14 == 10) {
		return "10";
	}
	else if(someId %14 == 11) {
		return "11";
	}
	else if(someId %14 == 12) {
		return "E1";
	}
	else if(someId %14 == 13) {
		return "E2";
	}
	else if(someId %14 == 0) {
		return "E3";
	}
}

function makeMeeting(id) {
	var day = getDayFromId(id);
	var period = getPeriodFromId(id);
	
	addPendingEvent(people, day, period, $('.title').val(), $('.building').val(), $('.room').val())
	
	var fix = "[";
	fix += people.toString();
	fix += "]";
	
	addPendingEvent(fix, day, period, $('.title').val(), $('.building').val(), $('.room').val())
	
	$('#dialog').dialog('close');
}

function openBox(x) {
	myId = x;
	
	var theBox = document.getElementById(x);
	var reason = document.getElementById("reason");
	
	if(theBox.className == "selfPattern") {
		reason.innerHTML = "Sorry, you can't have a meeting at this time. You have a previous event already scheduled.";
		$('#dialog2').dialog();
		$('#dialog').dialog('close');
	}
	else if(theBox.className == "coolPattern") {
		reason.innerHTML = "Sorry, you can't have a meeting at this time. A friend has a previous event already scheduled.";
		$('#dialog2').dialog();
		$('#dialog').dialog('close');
	}
	else if(!self) {
		reason.innerHTML = "You must make your schedule visable before adding meetings!";
		$('#dialog2').dialog();
		$('#dialog').dialog('close');
	}
	else {
		$('#dialog').dialog();
		$('#dialog2').dialog('close');
	}
	
}

$("#dialog").dialog({
  autoOpen: false,
  show: {
      effect: "blind",
      duration: 1000
  },
  hide: {
      effect: "explode",
      duration: 1000
  }
  });
  

</script>

</head>
<body onload="updateFriendList(); friendsMain(); clearSchedule">
	<!-- Top navigation bar -->
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a href="Surprise" class="navbar-brand">CRISIS MANAGER</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li><a href="ScheduleServ">My Schedule</a></li>
						<li class="active"><a href="FriendServ">Friends</a></li>
                        <li><a href="Surprise">About Us</a></li>
                        <li><a href="Help.html">Help</a></li>
					</ul>

					<ul class="nav navbar-nav navbar-right">
						<li><a href="Logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
	
<div class="hideMe">
	<div id="dialog" title="Add a Meeting">
	        <div class="form-group has-feedback">
	            <input name="username" type="Username" class="title"
	                   placeholder="Event Name">
	        </div>
	
	        <div class="form-group has-feedback">
	            <input name="password" type="Username" class="building"
	                   placeholder="Building">
	        </div>
	
	
	    <div class="form-group has-feedback">
	            <input name="username" type="Username" class="room"
	                   placeholder="Room">
	     </div>
	        <button class="formSaver" onclick="makeMeeting(myId)">Make Meeting</button>
	</div>
	
	<div id="dialog2" title="Time Unavailable" >
		<div>
	        <span id="reason"></span> <br>
	    </div>
	</div>
</div>

<div class="col-xs-1"></div>
	
<div class="col-xs-4">
<br><br><br><br><br><br><br><br><br><br>
	<div class="container">
		<br>
		<ul class="nav nav-pills text2">
			<li><a data-toggle="pill" href="#friends" class="text" onclick="updateAll();">Friends</a></li>
			<li><a data-toggle="pill" href="#pending" class="text" onclick="updateAll();">Pending Friends</a></li>
			<li><a data-toggle="pill" href="#pendingMeetings" class="text" onclick="updateAll();">Pending Meetings</a></li>
			<li class="active"><a data-toggle="pill" href="#addFriends" class="text" onclick="updateAll();">Add Friends</a></li>
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
				<div class="pendingFriends people" id="pendingFriends"></div>
				<br>
			</div>
			
			<div id="pendingMeetings" class="tab-pane fade">
				<br>
				<br>
				<div class="list-group people" id="meetings">
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Crisis Meeting</h4>
						<p class="list-group-item-text">M - 7 - NEB - 101</p>
						<div class="myButtons">
							<button class="accept" role="button" onclick="">
								<span class="glyphicon glyphicon-ok"></span>
							</button>
							<button class="reject" role="button" onclick="">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</div>
						
					</a> 
					<a href="#" class="list-group-item">
						<h4 class="list-group-item-heading">Pawel Cieslewski</h4>
						<p class="list-group-item-text">Pawel</p>
					</a>
				</div>
			</div>

			<div id="addFriends" class="tab-pane fade in active people text">
				<br><br><br><br><br><br><br>
								
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
	<div class="col-xs-7">
		<br>
		<h2 align="center" class="header">Friends' Schedules</h2>
        <br><br>
        
		<table id="myTable" class="yo1">
		</table>
		
		<br>
		<div class="yo1 mySched" align="center">
			<input onclick="seeMySchedule(this)" type="checkbox">
			Click to see your schedule
		</div>
		
		
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
