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

</head>
<body onload="main();">
	<div class="temp">
		
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
	
		<h2 align="center" class="text">Weekly Schedule</h2>
        <br><br>
	
		<table id="myTable" class="yo">
		</table>
	</div>
	<script>
	function addTable() {
	    var table = document.getElementById("myTable");
	    table.border='1';
	
	    var tableBody = document.createElement('TBODY');
	    table.appendChild(tableBody);
	
	    var daysOfWeek = ["Period/Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"];

	    for(var header = 0; header < 6; header++) {
		    var th = document.createElement('TH');
		    th.innerHTML = daysOfWeek[header];
		    tableBody.appendChild(th);
	    }
	    for (var rows = 0; rows < 14; rows++) {
	       var tr = document.createElement('TR');
	       tableBody.appendChild(tr);
	
	       for (var columns = 0; columns < 6; columns++) {
	    	   
	           var td = document.createElement('TD');
	           
	           td.setAttribute("id", 14 * (columns) + rows + 1);
	           
	           td.width='75';
	           td.height='40';
	           td.appendChild(document.createTextNode(""));
	           tr.appendChild(td);
	       }
	    }
	}
	
	function daySplitter(days) {
	    var noSpaces = days.split(" ");
	
	    return noSpaces;
	}
	
	function timeSplitter(time) {
	    var noDash = time.split("-");
	    if(noDash.length > 1) {
			if((noDash[0] == "E1") && (noDash[1] == "E3")) {
				noDash.push("E2");
				return noDash;
			}
			var first = parseInt(noDash[0]);
			var second = parseInt(noDash[1]);
			if((first + 1) < second) {
				while((first+1) < second) {
					noDash.push(first+1);
					first += 1;
				}
				return noDash;
			}	
	    }
	    return noDash;
	}
	
	function work(courseCode, day, time) {
	    var dayList = daySplitter(day);
	    var timeList = timeSplitter(time);
	    var dayTime = [];
	
	    for(var i = 0; i < dayList.length; i++) {
	        for(var j = 0; j < timeList.length; j++) {
	        	
	        //the +14 is to handle the period column
		    var tableIndex = getTableIndex(dayList[i], timeList[j]) + 14;
		    dayTime.push(courseCode + "@" + tableIndex);
	        }
	    }
	
	    return dayTime;
	}
	
	function updateTable(courseAndIndex) {
	    for(var i = 0; i < courseAndIndex.length; i++) {
	        var parse = courseAndIndex[i].split("@");
	        document.getElementById(parse[1]).innerHTML = parse[0];
	    }
	}
	
	function getTableIndex(day, time2) {
	    var time;
	
	    if(time2 == "E1") {
	        time = 11;
	    }
	    else if(time2 == "E2") {
	        time = 12;
	    }
	    else if(time2 == "E3") {
	        time = 13;
	    }
	    else {
	        time = parseInt(time2);
	    }
	
	    var numberOfPeriods = 14;
	
	    if(day == "M") {
	        return time;
	    }
	    else if(day == "T") {
	        return (time + numberOfPeriods);
	    }
	    else if(day == "W") {
	        return (time + 2 * numberOfPeriods);
	    }
	    else if(day == "R") {
	        return (time + 3 * numberOfPeriods);
	    }
	    else if(day == "F") {
	        return (time + 4 * numberOfPeriods);
	    }
	    else {
	       //alert("Error: in getTableIndex method");
	        return -1;
	    }
	}
	
	function mod() {	
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
					
					var all = work(courseCode, days, periods);
					updateTable(all);
				}
				addInPeriods(); 
	        }
	    });
		
		/*for(var i = 0; i < student.schedule.classList.length; i++) {
			var courseCode = student.schedule.classList[i].course;
			var days = student.schedule.classList[i].day;
			var periods = student.schedule.classList[i].period;
			
			var all = work(courseCode, days, periods);
			updateTable(all);
		}
		addInPeriods();*/
	}
	
	function addInPeriods() {
		for(var periodNumber = 1; periodNumber < 15; periodNumber++) {

			var updateCell = document.getElementById(periodNumber);

			var temp = periodNumber;
			var time = convertToTime(temp);
			
			if(periodNumber == 12) {
				temp = "E1";
			}
			if(periodNumber == 13) {
				temp = "E2";
			}
			if(periodNumber == 14) {
				temp = "E3";
			}
		
			updateCell.innerHTML = temp + "  -------  " + time;
		}
	}
	
	function convertToTime(i) {
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
	
	function main() {
		addTable();
		mod();
		for(var i = 1; i < 85; i++) { //1 - <85
			var cell = document.getElementById(i);
			cell.innerHTML = "POOPs";
			if(i % 14 == 1) {
				$(cell).addClass("one"); //one
			}
			else if(i % 14 == 2) {
				$(cell).addClass("two");
			}
			else if(i % 14 == 3) {
				$(cell).addClass("three");
			}
			else if(i % 14 == 4) {
				$(cell).addClass("four");
			}
			else if(i % 14 == 5) {
				$(cell).addClass("five");
			}
			else if(i % 14 == 6) {
				$(cell).addClass("six");
			}
			else if(i % 14 == 7) {
				$(cell).addClass("seven");
			}
			else if(i % 14 == 8) {
				$(cell).addClass("eight");
			}
			else if(i % 14 == 9) {
				$(cell).addClass("nine");
			}
			else if(i % 14 == 10) {
				$(cell).addClass("ten");
			}
			else if(i % 14 == 11) {
				$(cell).addClass("eleven");
			}
			else if(i % 14 == 12) {
				$(cell).addClass("twelve");
			}
			else if(i % 14 == 13) {
				$(cell).addClass("thirteen");
			}
			else if(i % 14 == 0) {
				$(cell).addClass("fourteen");
			}
		}
			
	}
	</script>
</body>
</html>
