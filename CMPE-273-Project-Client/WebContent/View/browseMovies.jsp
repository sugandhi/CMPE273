<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="DefaultNamespace.*" %>
<%@ page import="common.*" %>
<%@ page import="beans.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CMPE-273, Sec-1, Class Project, Browse Movies</title>
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
		String errMsg = (String) session.getAttribute("errMsg");
		if( errMsg != null ) {
		    out.println("<font size=\"1\" color=\"red\">" + errMsg + "</font>"); 
		}
		String successMsg = (String) session.getAttribute("successMsg");
		if( successMsg != null ) {
		    out.println("<font size=\"1\" color=\"green\">" + successMsg + "</font>"); 
		}

		MovieInfo[] movies = null;
		try {
		    session.removeAttribute("errMsg");
		    session.removeAttribute("successMsg");  
		    
		    VideoLibrarySystemProxy proxy = new VideoLibrarySystemProxy(ClientConstants.END_POINT);
		    movies = proxy.getMovies(memberInfo.getEmail());
		}
		catch(Exception e) {
		    out.println("<font size=\"1\" color=\"red\">" + e.getLocalizedMessage() + "</font>"); 
		}
		if(movies == null || movies.length == 0) {
		    out.println("<font size=\"3\" color=\"red\"><b>No Movies in system.</b></font>"); 
	    }
		else {
	%>
			<b>SEARCH UI...Below we should show UI with pagination. In case of ADMIN we will have option to Add/Edit/Delete movies.</b>
			<table id="listItems">
				<tr>
					<th width="25%">Movie Name</th>
					<th width="25%">Movie Banner</th>
					<th width="25%">Release Date</th>
					<th width="25%">Category</th>
				</tr>
	<%			for(MovieInfo movie: movies) { %>		        
					<tr>
						<td><a href="/CMPE-273-Project-Client/View/rentMovie.jsp?movieId=<%= movie.getId()%>"><%= movie.getName()%></a></td>
						<td><%= movie.getDescription()%></td>
						<td><%= movie.getSeller()%></td>
						<td><%= movie.getPrice()%></td>
					</tr>
	<%     		} 	%>
			</table>
	<%	}	%>
	</div>
</div>
</body>
</html>