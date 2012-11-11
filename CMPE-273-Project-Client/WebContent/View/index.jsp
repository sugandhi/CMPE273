<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project</title>
<link rel="stylesheet" href="../styles/global.css" type="text/css" />
<SCRIPT>
	<!--
	function doSubmit() {
		var email = document.signInForm.user.value;
		var atpos=email.indexOf("@");
	    var dotpos=email.lastIndexOf(".");

    	if(document.signInForm.user.value == "") {
    		alert("Username cannot be blank");
	        document.signInForm.user.focus();
	        return;
	    }
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length){
	       alert("Username is not a valid e-mail address");
	       document.signInForm.user.focus();
	       return;
	    }
	    if(document.signInForm.pwd.value == "") {
    	    alert("Password should not be left blank");
	        document.signInForm.pwd.focus();
	        return;
	    }
	    document.signInForm.submit();
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
		<div id="leftFrame" class="left_box">
			<b>Place Holder Text</b>
			<p>The client will be a Java application that allows a user to do the following:</p>
			<ol>
				<li>Create a new Premium Member</li>
				<li>Delete an existing Premium Member</li>
				<li>Create a new Simple Customer</li>
				<li>Delete an existing Simple Customer</li>
				<li>Create a new Movie</li>
				<li>Delete an existing Movie</li>
				<li>Issue (rent) a Movie</li>
				<li>Submit (return) a Movie</li>
				<li>List all Premium Members known by the system</li>
				<li>List all Simple Customers known by the system</li>
				<li>List all Movies known by the system</li>
				<li>List all persons known by the system</li>
				<li>Change a persons information (name, address, etc): This function must support the ability to change ALL attributes</li>
				<li>Change a movies information (name, banner etc): This function must support the ability to change ALL attributes</li>
				<li>Search for a person based on attributes. You do not have to consider attributes that are not listed above.</li>
				<li>Search for a Movie based on attributes</li>
				<li>Display information about a person (attributes plus movies issued)</li>
				<li>Display information about a movie (attributes plus list of person who issued this movie)</li>
				<li>Generate Bill for Premium Members (Monthly Subscription) and Simple Customers</li>
				<li>Premium Members are allowed to rent out maximum of 10 movies and Simple customers are allowed to rent only 2 movies.</li>
			</ol>
			<p>The Service should take care of exception that means validation is extremely important for this server. Good Exception Handling and validation would attract good marks.</p>
		</div>
		<div id="rightFrame" class="right_box">
			<h4>Sign in to Team7 Video Library</h4>
			<br/>
		<%
			String errMsg = (String) session.getAttribute("errMsg");
			if( errMsg != null ) {
			    out.println("<font size=\"1\" color=\"red\">" + errMsg + "</font>"); 
			}
			String successMsg = (String) session.getAttribute("successMsg");
			if( successMsg != null ) {
			    out.println("<font size=\"1\" color=\"green\">" + successMsg + "</font>"); 
			}
		%>
			<form name="signInForm" method="Post" action="../action/signIn">
				<span class="label">Username</span><br/>
				<input type="text" name="user" maxlength="255"><br/>
				<span class="label">Password</span><br/>
				<input type="password" name="pwd" maxlength="64"><br/>
				<input class="submit" type="button" value="Sign-In" onclick="doSubmit();">
			</form>
			<br/>
			<h4>Don't have account yet?</h4>
			<a href="/CMPE-273-Project-Client/View/signup.jsp">Create New Account</a>
		</div>
	</div>
</div>		
</body>
</html>