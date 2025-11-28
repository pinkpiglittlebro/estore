<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.estore.model.User" %>

<%
	User admin = (User) session.getAttribute("user");
	String ctx = request.getContextPath();
%>

<div style="background:#b85c38; padding:12px; color:white; display:flex; gap:20px;">
	<a href="${pageContext.request.contextPath}/admin">Dashboard</a>
	<a href="${pageContext.request.contextPath}/admin/sales">Sales History</a>
	<a href="${pageContext.request.contextPath}/admin/inventory">Inventory</a>
	<a href="${pageContext.request.contextPath}/admin/users">Manage Users</a>


	<span style="margin-left:auto;">
        Admin: <%= admin != null ? admin.getUsername() : "Unknown" %>
    </span>
</div>
