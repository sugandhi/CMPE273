<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="DefaultNamespace.*" %>
<%@ page import="common.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, Rent Movie</title>
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
    	if(document.addToCartForm.itemQty.value == "") {
    		alert("Item Quantity cannot be blank");
	        document.addToCartForm.itemQty.focus();
	        return;
	    }
    	if(document.addToCartForm.itemQty.value > document.addToCartForm.itemSellQty.value) {
    		alert("Item Quantity cannot be more than quantity available for sell");
	        document.addToCartForm.itemQty.focus();
	        return;
	    }	    
	    document.addToCartForm.submit();
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
	    String movieId = (String) request.getParameter("movieId");
		MovieInfo item = null;
		if(movieId != null) {
			try {
	            session.removeAttribute("errMsg");
	            session.removeAttribute("successMsg"); 
			}
			catch(Exception e) {
			    out.println("<font size=\"1\" color=\"red\">" + e.getLocalizedMessage() + "</font>"); 
			}
		}
		if(item == null) {
		    out.println("<font size=\"2\" color=\"red\"><b>Item not found for Item Id:" + movieId + "</b></font>");
		}
		else {
	%>
			<h4>Add Item to cart</h4>
			<form name="addToCartForm" method="Post" action="../action/addToCart">
				<input type="hidden" id="itemId" name="itemId" value="<%= movieId %>">
				<input type="hidden" id="itemSellQty" name="itemSellQty" value="<%= item.getQty() %>">
				<table id="showItem">
					<tr><td>Item Name</td><td><%= item.getName() %></td></tr>
					<tr><td>Item Description</td><td><%= item.getDescription() %></td></tr>
					<tr><td>Seller Info</td><td><%= item.getSeller() %></td></tr>
					<tr><td>Item Price</td><td><%= item.getPrice() %></td></tr>
					<tr><td>Item Quantity</td><td><%= item.getQty() %></td></tr>
					<tr><td>Purchase Quantity</td><td><input type="text" id="itemQty" name="itemQty" onkeypress="return isNumberKey(event)"></td></tr>
					<tr><td colspan="2" align="right"><input class="submit" type="button" value="Add to Cart" onclick="doSubmit();"></td></tr>
				</table>
			</form>
	<%
		}
	%>
	</div>
</div>
</body>
</html>