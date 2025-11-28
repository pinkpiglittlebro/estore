<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="admin_nav.jsp" />

<h2 style="text-align:center; color:#b85c38;">Manage Users</h2>

<table border="1" cellpadding="10" cellspacing="0" style="margin:auto; width:80%;">
	<tr>
		<th>ID</th>
		<th>Username</th>
		<th>Email</th>
		<th>Name</th>
		<th>Role</th>
		<th>Actions</th>
	</tr>

	<c:forEach var="u" items="${users}">
		<tr>
			<td>${u.id}</td>
			<td>${u.username}</td>
			<td>${u.email}</td>
			<td>${u.fullName}</td>
			<td>${u.role}</td>
			<td>
				<a href="${pageContext.request.contextPath}/admin/user?id=${u.id}">
					View / Edit
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
