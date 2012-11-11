<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, Sign-Up</title>
<link rel="stylesheet" href="../styles/global.css" type="text/css" />
<SCRIPT>
	<!--
	function doSubmit() {
		var email = document.signUpForm.email.value;
		var atpos=email.indexOf("@");
	    var dotpos=email.lastIndexOf(".");

    	if(document.signUpForm.fname.value == "") {
    		alert("First Name cannot be blank");
	        document.signUpForm.fname.focus();
	        return;
	    }
	    if(document.signUpForm.lname.value == "") {
	        alert("Last Name cannot be blank");
	        document.signUpForm.lname.focus();
	        return;
	    }
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length){
	       alert("Invalid e-mail address");
	       document.signUpForm.email.focus();
	       return;
	    }
	    if(document.signUpForm.pwd.value == "") {
    	    alert("Password cannot be blank");
	        document.signUpForm.pwd.focus();
	        return;
	    }
	    document.signUpForm.submit();
	}
	//-->
</SCRIPT>	
</head>
<body>
<div id="main">
	<div id="headerNav" class="header_nav">
		<div id="header" class="header">
			<div id="title" class="header_left">
				<h3>CMPE-273 Sec-1 Team7 - Video Library</h3>
			</div>
			<div id="loginUser" class="header_right"></div>
		</div>
		<div id="navBar" class="nav_bar"><ul class="basictab"></ul></div>
	</div>
	<div id="main_box" class="main_box">
		<h4>Sign up to access CMPE-273 Lab-2</h4>
		<form name="signUpForm" method="Post" action="../action/signUp">
			<table class="formTable" id="signUP">
				<tr><td><span class="label">Firstname</span></td><td><input type="text" name="fname" maxlength="64"></td></tr>
				<tr><td><span class="label">Lastname</span></td><td><input type="text" name="lname" maxlength="64"></td></tr>
				<tr><td><span class="label">Email Address / Username</span></td><td><input type="text" name="email" maxlength="255"></td></tr>
				<tr><td><span class="label">Password</span></td><td><input type="password" name="pwd" maxlength="64"></td></tr>
				<tr><td colspan="2" align="right"><input class="submit" type="button" value="Submit" onclick="doSubmit();"></td></tr>
			</table>		
		</form>
	</div>
</div>
</body>
</html>