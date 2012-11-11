<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="DefaultNamespace.*" %>
<%@ page import="common.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, My Account</title>
<link rel="stylesheet" href="../styles/global.css" type="text/css" />
<SCRIPT>
	<!--
	function validatecardfunc ()
	{
		validcard = false;
		var retElem = document.getElementById('cardNum');
		var ret = retElem.value;
		var selElem = document.getElementById('cardType');
		result = selElem.options[selElem.selectedIndex].value;
		if (result == "Master") {
			if (ret.length == 16) {
				if ((ret.substring (0, 2) >= "51") && (ret.substring (0, 2) <= "55")) {
					validcard = true;
				}
				else {
					validcard = false;
				}
			}
			else {
				validcard = false;
			}
		}
		if (result == "Visa") {
			if ((ret.length == 16) || (ret.length ==13)) {
				if (ret.substring (0, 1) != "4") {
					validcard = false;
				}
				else {
					validcard = true;
				}
			}
			else {
				validcard = false;
			}
		}
		if (result == "AmericanExpress") {
			if (ret.length == 15) {
				if ((ret.substring (0, 2) != "34") && (ret.substring (0, 2) != "37")) {
					validcard = false;
				}
				else {
					validcard = true;
				}
			}
			else {
				validcard = false;
			}
		}
		if (result == "Discover") {
			if (ret.length == 16) {
				if (ret.substring (0, 4) != "6011") {
					validcard = false;
				}
				else {
					validcard = true;
				}
			}
			else {
				validcard = false;
			}
		}
		return (validcard);
	}

	function doSubmit()
	{
		var selElem;
		var textElemVal;
		if (validatecardfunc() != true)
		{
			alert ("Credit card number entered is not valid! Please provide valid credit card number.");
			return;
		}
		document.forms[0].submit();
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
	<%
	    if(session.getAttribute(LibraryConstants.SESSION_ID) == null)
					response.sendRedirect("index.jsp");
			
				String errMsg = (String) session.getAttribute("errMsg");
				if( errMsg != null ) {
				    out.println("<font size=\"1\" color=\"red\">" + errMsg + "</font>"); 
				}
				String successMsg = (String) session.getAttribute("successMsg");
				if( successMsg != null ) {
				    out.println("<font size=\"1\" color=\"green\">" + successMsg + "</font>"); 
				}	

				String itemId = (String) request.getParameter("itemId");
				MovieInfo[] items = null;
				try {
		    session.removeAttribute("errMsg");
		    session.removeAttribute("successMsg"); 
		    VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(LibraryConstants.END_POINT);
					if(itemId != null) {
					    items = proxy.removeFromCart((String) session.getAttribute(LibraryConstants.SESSION_ID), itemId);	  
					}
					else {
						items = proxy.getItems((String) session.getAttribute(LibraryConstants.SESSION_ID), LibraryConstants.CART_ITEMS);
					}
				}
				catch(Exception e) {
				    out.println("<font size=\"1\" color=\"red\">" + e.getLocalizedMessage() + "</font>");
				}
	%>
		<table id="listItems">
		<tr>
			<th width="10%">Item Name</th>
			<th width="30%">Item Description</th>
			<th width="30%">Seller Info</th>
			<th width="10%">Item Price</th>
			<th width="10%">Item Quantity</th>
			<th width="5%">Sold Quantity</th>
			<th width="5%">Action</th>
		</tr>	
	<%
		    if(items != null && items.length > 0) {
					    for(MovieInfo item: items) {
		%>		        
			<tr>
				<td><%= item.getName()%></td>
				<td><%= item.getDescription()%></td>
				<td><%= item.getSeller()%></td>
				<td><%= item.getPrice()%></td>
				<td><%= item.getQty()%></td>
				<td><%= item.getSoldQty()%></td>
				<td><a href="/CMPE-273-Lab2-Client/View/viewCart.jsp?itemId=<%= item.getId()%>">Remove</a></td>
			</tr>
	<%     
		    }
		}
		else {
	%>
			<tr><td colspan="7"><font size="3" color="red">Cart is empty.</font></td></tr>
	<% 	}
	%>
		</table>
	<%
		if(items != null && items.length > 0) {
	%>		    
		<h4>Payment Information</h4>
		<form name="checkoutForm" method="Post" action="../action/checkout">
			<span class="label">Card Type</span><br/>
			<select id="cardType" name="cardType">
				<option value="" selected>Please choose</option>
				<option value="Visa">Visa</option>
				<option value="Master">MasterCard/Eurocard</option>
				<option value="AmericanExpress">American Express</option>
				<option value="Discover">Discover</option>
			</select><br/>
			<span class="label">Name on Card</span><br/>
			<input type="text" name="cardName" maxlength="64"><br/>
			<span class="label">Card Number</span><br/>
			<input type="text" id="cardNum" name="cardNum" maxlength="20"><br/>
			<input type="button" value="Checkout/Order" name="B1" onclick="doSubmit();">
		</form>
	<%    
		}

	%>
	</div>
</div>
</body>
</html>