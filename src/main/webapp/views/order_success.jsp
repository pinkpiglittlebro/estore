<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
	<title>Order Success</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>

<body class="auth-body">

<jsp:include page="components/navbar.jsp" />

<div class="register-container" style="max-width:500px; text-align:center; margin: 50px auto;">
	<h2>Order Placed Successfully!</h2>


	<p><b>Order ID:</b> ${param.id}</p>

	<br>

	<a href="${pageContext.request.contextPath}/home"
	   class="nav-btn" style="background:#b85c38; color:white;">
		Continue Shopping
	</a>

	<br><br>

	<a href="${pageContext.request.contextPath}/orders"
	   class="nav-btn">
		View Order History
	</a>
</div>

</body>
</html>
