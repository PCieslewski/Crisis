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

	<style>
		html {
		
	        background: url(backgroundImage.jpeg) no-repeat center center fixed;
	        -webkit-background-size: cover;
	        -moz-background-size: cover;
	        -o-background-size: cover;
	        background-size: cover;
	    }
        
        .crisisBox {
        	border: 3px solid #BC0000;
	        margin-left: 15px;
	        font-family: 'Syncopate', sans-serif;
	        color: white;
	        float:left;
        }
        
        .crisis {
        	font-family: 'Syncopate', sans-serif;
	        color: white;
	        float:left;
        }
        
        .topRow {
        	margin-left: 25px;
	        margin-right: 25px;
	    	margin-top: 25px;
	    	margin-bottom: 25px;
	        display: inline-block;
        }
        
        .loginBox {
	        height: 400px;
	        width: 300px;
	        background: #212121;
	        position: fixed;
	        top: 50%;
	        left: 50%;
	        margin-top: -200px;
	        margin-left: -150px;
	        
	        border-radius: 5px;
        }
        
        .inputBoxes {
	        margin-left: 25px;
	        margin-right: auto;
	        height: 35px;
	        width: 250px;
	        border-radius: 5px;

	        font-family: 'Syncopate', sans-serif;
	        color: black;
        }
        
        .submitButton {      		
       		margin-left: 25px;
	        margin-right: auto;
	        height: 35px;
	        width: 250px;
	        background-color: #BC0000;
	        border-color: #BC0000;
	        color: white;
	        font-family: 'Syncopate', sans-serif;
        }
        
        .submitButton:hover {
        	background: #383;
        	border-color: #383;
        }
            
        .forgotPassword {
        	text-align: center;
        	color: #00CC00;
        	font-family: 'Syncopate', sans-serif;
        }
        
        .colorTheme1 {
        	color: #BC0000;
        }
        
        .colorTheme2 {
        	color: white;
        }
        
        .button {
        	color: red;
        }
        
        ::-webkit-input-placeholder { /* WebKit, Blink, Edge */
	  		color:    black;
		}
		:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
		    color:    black;
		    opacity:  .5;
		}
		::-moz-placeholder { /* Mozilla Firefox 19+ */
		    color:    black;
		    opacity:  .5;
		}
		:-ms-input-placeholder { /* Internet Explorer 10-11 */
		    color:    black;
		}
	        
	    .error {
	    	color: white;
	    	text-align: center;
	    	background-color: #BC0000;
	    	
	    
	    	
	    }
	        
	</style>

</head>
<body>
	<!-- Login form -->
	<div class="loginBox">
		
		<div class="topRow">
			<div class="crisisBox" style=''>
					<h2>CRISIS</h2>
			</div>
			<img src="logo.png" width="35%" align="right">
		</div>
		<br>
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
			<br><br><br>
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