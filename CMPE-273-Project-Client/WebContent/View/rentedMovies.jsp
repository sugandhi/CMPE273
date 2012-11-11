<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="DefaultNamespace.*" %>
<%@ page import="common.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, Rented Movies</title>
<link rel="stylesheet" href="../styles/global.css" type="text/css" />
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
	    if(session.getAttribute(ClientConstants.SESSION_ID) == null)
					response.sendRedirect("index.jsp");
			
			    MovieInfo[] items = null;
				try {
		    session.removeAttribute("errMsg");
		    session.removeAttribute("successMsg"); 
		    VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
					items = proxy.getItems((String) session.getAttribute(ClientConstants.SESSION_ID), ClientConstants.SOLD_ITEMS);
				}
				catch(Exception e) {
				    out.println("<font size=\"1\" color=\"red\">" + e.getLocalizedMessage() + "</font>");
				}
				if(items == null || items.length == 0) {
				    out.println("<font size=\"3\" color=\"red\"><b>No sold item details are available</b></font>"); 
			    }
				else {
	%>
			<table id="listItems">
			<tr>
				<th width="10%">Item Name</th>
				<th width="30%">Item Description</th>
				<th width="30%">Seller Info</th>
				<th width="10%">Item Price</th>
				<th width="10%">Item Quantity</th>
				<th width="10%">Sold Quantity</th>
			</tr>
	<%
	    for(MovieInfo item: items) {
	%>		        
			<tr>
				<td><%= item.getName()%></td>
				<td><%= item.getDescription()%></td>
				<td><%= item.getSeller()%></td>
				<td><%= item.getPrice()%></td>
				<td><%= item.getQty()%></td>
				<td><%= item.getSoldQty()%></td>
			</tr>
	<%     
		    }
	%>
			</table>
	<%
		}
	%>
	</div>
</div>
</body>
</html>