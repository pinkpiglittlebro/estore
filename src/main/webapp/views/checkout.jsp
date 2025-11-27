<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<title>Checkout</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">

	<style>
		/* ADDED: CSS to stack inputs vertically */
		.checkout-form {
			display: flex;
			flex-direction: column; /* Forces elements to stack vertically */
			gap: 15px;              /* Adds space between the input boxes */
			max-width: 400px;       /* Optional: limits width for better look */
			margin: 0 auto;         /* Optional: centers the form */
		}

		.checkout-form input {
			padding: 10px;
			border: 1px solid #ccc;
			border-radius: 4px;
		}

		.checkout-form h3 {
			margin-bottom: 5px;
			margin-top: 20px;
		}
	</style>
</head>
<body class="auth-body">

<jsp:include page="components/navbar.jsp" />

<div class="checkout-wrapper">
	<div class="checkout-box">

		<h2>Checkout</h2>
		<p style="color:red">${error}</p>

		<form action="${pageContext.request.contextPath}/checkout" method="post" class="checkout-form">

			<h3>Shipping Information</h3>

			<input name="fullName" placeholder="Full Name" required>
			<input name="address" placeholder="Address" required>
			<input name="city" placeholder="City" required>
			<input name="postal" placeholder="Postal Code" required>
			<input name="phone" placeholder="Phone Number" required>

			<h3>Payment Information</h3>

			<input name="cardNumber" placeholder="Card Number" required>
			<input name="expiry" placeholder="MM/YY" required>
			<input name="cvv" placeholder="CVV" required>

			<button type="submit" class="register-btn">Place Order</button>

		</form>

	</div>
</div>

</body>
</html>
