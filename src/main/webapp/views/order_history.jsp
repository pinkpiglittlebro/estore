<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Your Order History</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!-- Include Navbar -->
<jsp:include page="/views/components/navbar.jsp" />

<h2 style="text-align:center; margin-top:20px; color:#b85c38;">
	Your Order History
</h2>

<c:if test="${not empty error}">
	<p style="color:red; text-align:center;">${error}</p>
</c:if>

<c:if test="${empty orders}">
	<p style="text-align:center; margin-top:20px;">You have no orders yet.</p>
</c:if>

<c:if test="${not empty orders}">
	<table border="1" cellpadding="10" cellspacing="0" style="margin:auto; margin-top:20px;">
		<tr>
			<th>Order ID</th>
			<th>Date</th>
			<th>Name</th>
			<th>Total</th>
			<th>Details</th>
		</tr>

		<c:forEach var="o" items="${orders}">
			<tr>
				<td>${o.id}</td>
				<td>${o.createdAt}</td>
				<td>${o.fullName}</td>
				<td>$${o.total}</td>
				<td>
					<a href="${pageContext.request.contextPath}/order?id=${o.id}">
						View Items
					</a>
				</td>
			</tr>
		</c:forEach>

	</table>
</c:if>

</body>
</html>
