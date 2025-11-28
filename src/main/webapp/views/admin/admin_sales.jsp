<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="admin_nav.jsp" />


<h2>Sales History</h2>

<form method="get" style="margin-bottom:20px;">
	Customer: <input type="text" name="customer" />
	Product: <input type="text" name="product" />
	Date From: <input type="date" name="dateFrom" />
	Date To: <input type="date" name="dateTo" />
	<button type="submit">Filter</button>
</form>

<table border="1" cellpadding="8">
	<tr>
		<th>Order ID</th>
		<th>Customer</th>
		<th>Total</th>
		<th>Date</th>
		<th>Details</th>
	</tr>

	<c:forEach var="o" items="${orders}">
		<tr>
			<td>${o.id}</td>
			<td>${o.fullName}</td>
			<td>$${o.total}</td>
			<td>${o.createdAt}</td>
			<td>
				<a href="${pageContext.request.contextPath}/admin/order?id=${o.id}">
					View
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
