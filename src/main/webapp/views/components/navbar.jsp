<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.estore.model.User" %>

<%
	User user = (User) session.getAttribute("user");
	String ctx = request.getContextPath();
%>

<div class="navbar">
	<div class="nav-left">
		<a class="logo" href="<%= ctx %>/home">Home</a>
	</div>

	<div class="nav-right">

		<% if (user == null) { %>
		<!-- User NOT logged in: show Sign In / Sign Up -->
		<a class="nav-btn" href="<%= ctx %>/login">Sign In</a>
		<a class="nav-btn sign-up" href="<%= ctx %>/register">Sign Up</a>

		<% } else { %>
		<!-- User logged in -->
		<span class="welcome">Hi, <%= user.getUsername() %></span>

		<!-- My Orders link -->
		<a class="nav-btn" href="<%= ctx %>/orders">My Orders</a>

		<a class="nav-btn" href="<%= ctx %>/logout">Logout</a>
		<% } %>

	</div>
</div>
