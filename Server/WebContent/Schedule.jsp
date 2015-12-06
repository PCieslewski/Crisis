<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Crisis Management Scheduler</title>
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

  
<link rel=stylesheet href="css/schedule.css" type="text/css" media="screen">
<script src="scripts/tableStuff.js"></script>

  <script>
  
  var myId;
  
  function openBox(x) {
	  console.log("old: " + myId);
	  myId = x;
	  console.log("new: " + myId);
	  
	  var content = document.getElementById(x);
	  var popUp = document.getElementById("pass");
	 
	 
	  if(content.innerHTML == "") {
		  popUp.innerHTML = "No class at this time";
		
			  $('#dialog').dialog();
			  $("#dialog2").dialog('close');
	  }
	  else {
		  popUp.innerHTML = content.innerHTML;
		  getDetails(x);
		  $('#dialog2').dialog();
		  $("#dialog").dialog('close');
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

function addContent(theId) {
    $('.myTarget').text($('.inputBoxes').val());
    $('.myTarget2').text($('.inputBoxes2').val());
    $('.myTarget3').text($('.inputBoxes3').val());
    
    var theBox = document.getElementById(theId);
    theBox.innerHTML = $('.inputBoxes').val();
 
    var day = getDayFromId(theId);
    var period = getPeriodFromId(theId);
    
    addEvent(day, period, $('.inputBoxes').val(), $('.inputBoxes2').val(), $('.inputBoxes3').val());
    
    $("#dialog").dialog('close');
}

function removeContent(theId) {
	
	var theBox = document.getElementById(theId);
    theBox.innerHTML = "";
    
    var day = getDayFromId(theId);
    var period = getPeriodFromId(theId);
    
    removeEvent(day, period);
    
	$("#dialog2").dialog('close');
}

function addTable() {
    var table = document.getElementById("myTable");
    table.border='1';

    var tableBody = document.createElement('TBODY');
    table.appendChild(tableBody);

    var daysOfWeek = ["Period/Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];

    for(var header = 0; header < 6; header++) {
	    var th = document.createElement('TH');
	    $(th).addClass("white");
	    th.innerHTML = daysOfWeek[header];
	    tableBody.appendChild(th);
    }
    for (var rows = 0; rows < 14; rows++) {
       var tr = document.createElement('TR');
       tableBody.appendChild(tr);

       for (var columns = 0; columns < 6; columns++) {
    	   
           var td = document.createElement('TD');
           addContent
           td.setAttribute("id", 14 * (columns) + rows + 1);
           
           var num = (14 * (columns) + rows + 1);
           if(columns != 0) {
           	td.setAttribute('onclick', "openBox(" + num + ")");
           }
           td.width='75';
           td.height='40';
           td.align="center";
           td.appendChild(document.createTextNode(""));
           tr.appendChild(td);
       }
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
  </script>
  
  <script>
		
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
		        	location.reload(); //THIS SHOULD BE CHANGED. JUST A REFRESH AS OF NOW.
		        }
		    });
		}
		function removeEvent(day, period) {
			$.ajax({
		        type: "POST", 
		        url: "RemoveEvent", 
		        dataType: "json",
		        data: {
		        	'day':day,
		        	'period':period,
		        },
		        success: function (student) { //The response is the updated student object.
		        	location.reload(); //THIS SHOULD BE CHANGED. JUST A REFRESH AS OF NOW.
		        }
		    });
		}
		
		function getDetails(tableId) {	
			var day = getDayFromId(tableId);
			var period = getPeriodFromId(tableId);
			
			$.ajax({
		        type: "GET", //Type of post
		        url: "GetPersonJson", //Where it is sent (Which servlet)
		        dataType: "json",
		        //data: {'gatorlink':gatorlink}, //This is sent TO THE SERVER
		        success: function (msg) { //Msg is returned FROM THE SERVER!
					var student = msg;
					for(var i = 0; i < student.schedule.classList.length; i++) {
						var courseCode = student.schedule.classList[i].course;
						var days = student.schedule.classList[i].day;
						var periods = student.schedule.classList[i].period;
						
						if((day == days) && (period == periods)) {
							var buildingVar = document.getElementById("building");
							var roomVar = document.getElementById("room");
							buildingVar.innerHTML = student.schedule.classList[i].building;
							roomVar.innerHTML = student.schedule.classList[i].room;
						}
						
						
					}
					
		        }
		    });		  

		}
		</script>
		
</head>
<style>
    #dialog {
        display:none;
    }
    #hide {
    	display:none;
    }
</style>
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

	<div id="dialog" title="Add Event">
	        <div class="form-group has-feedback">
	            <input name="username" type="Username" class="inputBoxes"
	                   placeholder="Event Name">
	        </div>
	
	        <div class="form-group has-feedback">
	            <input name="password" type="Username" class="inputBoxes2"
	                   placeholder="Building">
	        </div>
	
	
	    <div class="form-group has-feedback">
	            <input name="username" type="Username" class="inputBoxes3"
	                   placeholder="Room">
	     </div>
	        <button class="formSaver" onclick="addContent(myId)">Create Event</button>
	</div>
	
	<div id="dialog2" title="Remove Event" >
		<div>
	        Event: <span id="pass"></span> <br>
	        Building: <span id="building"></span> <br>
	        Room: <span id="room"></span>
	    </div>
	    
	    <br>
	      
	    <button class="reject" role="button" onclick="removeContent(myId)">
					<span class="glyphicon glyphicon-remove"></span> Click to remove event
				</button>
	</div>
    
    </div>
</body>
</html>