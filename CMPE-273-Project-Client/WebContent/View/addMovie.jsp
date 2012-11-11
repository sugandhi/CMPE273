<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="common.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, Add Movie</title>
<link rel="stylesheet" href="../styles/global.css" type="text/css" />
<SCRIPT language=Javascript>
	<!--
	function isNumberKey(evt) {
    	var charCode = (evt.which) ? evt.which : event.keyCode;
		if (charCode != 46 && charCode > 31 && (charCode < 48 || charCode > 57))
        	return false;
		return true;
	}
  	function doSubmit() {
		if(document.addItemForm.name.value == "") {
  			alert("Item Name cannot be blank");
			document.addItemForm.name.focus();
			return;
		}
		if(document.addItemForm.desc.value == "") {
			alert("Item Desc cannot be blank");
			document.addItemForm.desc.focus();
			return;
		}
		if(document.addItemForm.seller.value == "") {
			alert("Seller Location cannot be blank");
			document.addItemForm.seller.focus();
			return;
		}		
		if(document.addItemForm.price.value == "") {
			alert("Price cannot be blank");
			document.addItemForm.price.focus();
			return;
		}
		if(document.addItemForm.qty.value == "") {
			alert("Quantity cannot be blank");
			document.addItemForm.qty.focus();
			return;
		}
	    document.addItemForm.submit();
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
			<div id="loginUser" class="header_right">
				<%
					MemberInfo memberInfo = (MemberInfo) session.getAttribute(ClientConstants.MEMBER);
					if(memberInfo == null) 
					    response.sendRedirect("index.jsp");
					
					out.println("<font size=\"2\">Welcome " + memberInfo.getFirstName() + " " + memberInfo.getLastName() + "</font><br/>");
					out.println("<font size=\"2\">Last Login: " + memberInfo.getLoginTime() + "</font><br/><br/>");
					out.println("<font size=\"2\"><a href=\"/CMPE-273-Project-Client/action/signOut\">Signout</a></font>");
				%>
			</div>
		</div>
		<div id="navBar" class="nav_bar">
			<ul class="basictab">
				<%	if(memberInfo.getMemberType() != ClientConstants.MEMBER_TYPE_ADMIN) { %>
						<li class="selected"><a href="/CMPE-273-Project-Client/View/browseMovies.jsp">Movies</a></li>
						<li><a href="/CMPE-273-Project-Client/View/browserMembers.jsp">Members</a></li>
						<li><a href="/CMPE-273-Project-Client/View/myProfile.jsp">My Profile</a></li>
				<% 	} else { %>
						<li class="selected"><a href="/CMPE-273-Project-Client/View/browseMovies.jsp">Browse Movies</a></li>
						<li><a href="/CMPE-273-Project-Client/View/rentedMovies.jsp">Rented Movies</a></li>
						<li><a href="/CMPE-273-Project-Client/View/myProfile.jsp">My Profile</a></li>
						<li><a href="/CMPE-273-Project-Client/View/myAccount.jsp">My Account</a></li>
				<% 	} %>
			</ul>
		</div>
	</div>	
	<div id="main_box" class="main_box">
		<h4>Add Item to Sell</h4>
		<form name="addItemForm" method="Post" action="../action/addItem">
			<table id="signUP">
				<tr><td><span class="label">Item Name</span></td><td><input type="text" name="name" maxlength="64"></td></tr>
				<tr><td><span class="label">Item Description</span></td><td><input type="text" name="desc" maxlength="1024"></td></tr>
				<tr><td><span class="label">Seller Info</span></td><td><input type="text" name="seller" maxlength="1024"></td></tr>
				<tr><td><span class="label">Item Price</span></td><td><input type="text" id="price" name="price" onkeypress="return isNumberKey(event)"></td></tr>
				<tr><td><span class="label">Item Quantity</span></td><td><input type="text" id="qty" name="qty" onkeypress="return isNumberKey(event)"></td></tr>
				<tr><td colspan="2" align="right"><input class="submit" type="button" value="Add Item" onclick="doSubmit();"></td></tr>
			</table>
		</form>
	</div>
</div>
</body>
</html>