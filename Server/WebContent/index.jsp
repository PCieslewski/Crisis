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

<style>
	html {
        background: url(London.jpg) no-repeat center center fixed;
        -webkit-background-size: cover;
        -moz-background-size: cover;
        -o-background-size: cover;
        background-size: cover;
        }
        
        .loginBox {
        height: 400px;
        width: 500px;
        background: white;

        position: fixed;
        top: 50%;
        left: 50%;
        margin-top: -200px;
        margin-left: -250px;
        }
        
        .inputBoxes {
        margin-left: auto;
        margin-right: auto;
            width: 300px;

        }
</style>

</head>
<body>
	<!-- Login form -->
	<div class="loginBox">
		<h2 align="center">CRISIS Login</h2>
		<form role="form" action="Login" name="loginForm" method="post">
			<div class="form-group">
				<input name="username" type="Username" class="inputBoxes"
					placeholder="Username">
			</div>
			<div class="form-group">
				<input name="password" type="Password" class="inputBoxes"
					placeholder="Password">
			</div>
			
			<!-- If the login credentials fail, display error. -->
			<%
				Boolean invalidCredentials = (Boolean) request.getAttribute("invalidCredentials");
				if (invalidCredentials != null && invalidCredentials == true) {
			%>
			<div class="alert alert-danger">
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
			<div class="alert alert-danger">
				<strong>Something went wrong. Please try again!</strong>
			</div>
			<%
				}
			%>
			
			<input type="submit" value="Login" class="btn btn-primary btn-block">
		</form>
	</div>
</body>
</html>