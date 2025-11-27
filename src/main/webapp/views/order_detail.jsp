<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<title>Order Details</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<jsp:include page="/views/components/navbar.jsp" />

<h2 style="text-align:center; margin-top:20px; color:#b85c38;">
	Order Details #${order.id}
</h2>

<div style="width:60%; margin:auto; font-size:18px;">

	<p><b>Placed on:</b> ${order.createdAt}</p>
	<p><b>Total:</b> $${order.total}</p>

	<h3 style="color:#b85c38; margin-top:25px;">Shipping</h3>
	<p>${order.fullName}</p>
	<p>${order.address}</p>
	<p>${order.city}, ${order.postal}</p>
	<p>${order.phone}</p>

	<h3 style="color:#b85c38; margin-top:30px;">Items</h3>

	<table border="1" cellpadding="10" cellspacing="0" style="margin:auto;">
		<tr>
			<th>Product</th>
			<th>Qty</th>
			<th>Price</th>
		</tr>

		<c:forEach var="i" items="${items}">
			<tr>
				<td>${i.productName}</td>
				<td>${i.quantity}</td>
				<td>$${i.price}</td>
			</tr>
		</c:forEach>
	</table>

</div>

</body>
</html>
