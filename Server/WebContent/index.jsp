<!DOCTYPE html>
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
	
	<link href='https://fonts.googleapis.com/css?family=Syncopate' rel='stylesheet' type='text/css'>

	<link rel=stylesheet href="css/login.css" type="text/css" media="screen">
	<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Lato:100,300,400,700,900">
	<link rel=stylesheet href="sharpCalendar/css/jquery.sharpCalendar.css" type="text/css" media="screen">
	<link rel=stylesheet href="css/reset.css" type="text/css" media="screen">
	<link rel=stylesheet href="css/main.css" type="text/css" media="screen">
        
</head>
<body>

	<input type="hidden" id="selectedDates" value="2014-8-13,2014-8-10,2014-8-27,2014-7-27,2014-9-25,2014-9-20"/>
        <div id="page" class="page">
            <section id="main">


                <div class="wrapperHor">

                </div>


            </section>
        </div>
        <!-- jQuery -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
        <!-- SharpCalendar -->
        <script src="sharpCalendar/script/jquery.sharpCalendar.js"></script>
        <!-- libs -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-mousewheel/3.1.11/jquery.mousewheel.js"></script>
        <script src="scripts/main.js"></script>
        <script>
            $(".wrapperHor").SC({
                selectedDatesObj: 'selectedDates',
                animate: true,
                useWheel: true,
                vertical: false,
                sizes: 'auto',
                callbackDelay: 500,
                years: 1,
                months: 3,
                days: 5,
                invert: false,
                combineMonthYear: false,
                showDayArrows: false,
                showDayNames: true,
                monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                dayNames: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"],
                doubleDigitsDays: true,
                allowSelectSpans: true,
                callback:function(cal) {
                  $("#wtf").html("Selected date: " + cal.currentDate);
                }
		    });
        </script>
        <script type="text/javascript">

		  var _gaq = _gaq || [];
		  _gaq.push(['_setAccount', 'UA-36251023-1']);
		  _gaq.push(['_setDomainName', 'jqueryscript.net']);
		  _gaq.push(['_trackPageview']);
		
		  (function() {
		    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'https://www') + '.google-analytics.com/ga.js';
		    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
		  })();
		
		</script>

	<!-- Login form -->
	<div class="loginBox">
		
		<div class="topRow">
			<div class="crisisBox" style=''>
					<h2>CRISIS</h2>
			</div>
			<img src="logo.png" width="35%" align="right">
		</div>
		<br><br><br>
		<form role="form" class="form-inline" action="Login" name="loginForm" method="post">
			<div class="form-group has-feedback">
				<input name="username" type="Username" class="inputBoxes"
					placeholder="Username">
				<i class="form-control-feedback glyphicon glyphicon-user colorTheme1"></i>
			</div>
			<br><br>
			<div class="form-group has-feedback">
				<input name="password" type="Password" class="inputBoxes"
					placeholder="Password">
				<i class="form-control-feedback glyphicon glyphicon-lock colorTheme1"></i>
			</div>
			<br><br>
			<!-- If the login credentials fail, display error. -->
			<%
				Boolean invalidCredentials = (Boolean) request.getAttribute("invalidCredentials");
				if (invalidCredentials != null && invalidCredentials == true) {
			%>
			<div class="error">
				<strong>Invalid Login Credentials.</strong>
			</div>
			<%
				}
			%>
			
			<!-- If the login credentials fail, display error. -->
			<%
				Boolean timeout = (Boolean) request.getAttribute("timeout");
				if (timeout != null && timeout == true) {
			%>
			<div class="error">
				<strong>Something went wrong. Please try again!</strong>
			</div>
			<%
				}
			%>
			<br><br><br><br>
			<div class="form-group has-feedback">
				<input type="submit" value="Log In" class="btn btn-default submitButton">
				<i class="form-control-feedback glyphicon glyphicon-log-in colorTheme2"></i>
				
			</div>
		</form>
		<br>
		<div class="forgotPassword">
			<a href="https://my.ufl.edu/psp/ps_pwd/EMPLOYEE/EMPL/c/MAINTAIN_SECURITY.UF_PA_FORGOT_PSWD.GBL"> Forgot password? </a>
		</div>
	</div>
	
</body>
</html>