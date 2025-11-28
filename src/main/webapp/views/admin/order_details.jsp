<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>Order Details</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<jsp:include page="/views/components/navbar.jsp" />

<h2 style="text-align:center; margin-top:20px; color:#b85c38;">
	Order #${order.id} Details
</h2>

<div style="width:60%; margin:auto; background:#fffaf7; padding:20px; border:1px solid #e3c5b1; border-radius:8px;">

	<h3>Customer Info</h3>
	<p><b>Name:</b> ${order.fullName}</p>
	<p><b>Address:</b> ${order.address}, ${order.city}, ${order.postal}</p>
	<p><b>Phone:</b> ${order.phone}</p>
	<p><b>Date:</b> ${order.createdAt}</p>
	<p><b>Total:</b> $${order.total}</p>

	<hr>

	<h3>Items</h3>
	<table border="1" cellpadding="10" cellspacing="0" style="width:100%;">
		<tr>
			<th>Product</th>
			<th>Price</th>
			<th>Qty</th>
			<th>Subtotal</th>
		</tr>

		<c:forEach var="item" items="${items}">
			<tr>
				<td>${item.productName}</td>
				<td>$${item.price}</td>
				<td>${item.quantity}</td>
				<td>$${item.quantity * item.price}</td>
			</tr>
		</c:forEach>
	</table>

	<br>
	<a href="${pageContext.request.contextPath}/admin/sales"
	   style="color:#b85c38; font-weight:bold;">← Back to Sales</a>

</div>

</body>
</html>
