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
  function openBox() {
	  $('#dialog').dialog();
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

function myFunction() {
    $('.myTarget').text($('.inputBoxes').val());
    $('.myTarget2').text($('.inputBoxes2').val());
    $('.myTarget3').text($('.inputBoxes3').val());
    $("#dialog").dialog('close');
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
           
           td.setAttribute("id", 14 * (columns) + rows + 1);
           td.setAttribute('onclick', "openBox()");
           
           td.width='75';
           td.height='40';
           td.align="center";
           td.appendChild(document.createTextNode(""));
           tr.appendChild(td);
       }
    }
}
  </script>
  
</head>
<style>
    #dialog {
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

	<div id="dialog" title="Basic dialog">
		<div>
	        Currently: <span id="pass"></span>
	    </div>
	    <br>
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
	        <button class="formSaver" onclick="myFunction()">Create Event2</button>
	</div>

	<p id="test">"fucking work</p>

	<div>Event Name: <span class="myTarget"></span> <br>
    Building: <span class="myTarget2"></span> <br>
    Room: <span class="myTarget3">here</span></div>
    
    </div>
</body>
</html>