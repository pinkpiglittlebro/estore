<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="admin_nav.jsp" />

<h2 style="color:#b85c38;">User Details</h2>

<form action="${pageContext.request.contextPath}/admin/user/update" method="post">

	<input type="hidden" name="id" value="${user.id}"/>

	<label>Full Name:</label><br>
	<input type="text" name="fullName" value="${user.fullName}"/><br><br>

	<label>Email:</label><br>
	<input type="email" name="email" value="${user.email}"/><br><br>

	<label>Address:</label><br>
	<input type="text" name="address" value="${user.address}"/><br><br>

	<label>City:</label><br>
	<input type="text" name="city" value="${user.city}"/><br><br>

	<label>Province:</label><br>
	<input type="text" name="province" value="${user.province}"/><br><br>

	<label>Postal:</label><br>
	<input type="text" name="postal" value="${user.postal}"/><br><br>

	<label>Phone:</label><br>
	<input type="text" name="phone" value="${user.phone}"/><br><br>

	<label>Credit Card:</label><br>
	<input type="text" name="creditCard" value="${user.creditCard}"/><br><br>

	<button type="submit">Update User</button>

</form>

<hr>

<h3>Order History</h3>

<c:if test="${empty orders}">
	<p>This user has no orders.</p>
</c:if>

<c:if test="${not empty orders}">
	<table border="1" cellpadding="10" cellspacing="0">
		<tr><th>ID</th><th>Total</th><th>Date</th></tr>

		<c:forEach var="o" items="${orders}">
			<tr>
				<td>${o.id}</td>
				<td>$${o.total}</td>
				<td>${o.createdAt}</td>
			</tr>
		</c:forEach>
	</table>
</c:if>
