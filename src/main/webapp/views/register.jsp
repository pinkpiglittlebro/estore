<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.estore.model.User" %>

<!DOCTYPE html>
<html>
<head>
	<title>Register - Pastry</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body class="auth-body">

<jsp:include page="components/navbar.jsp" />

<form action="<%= request.getContextPath() %>/register" method="post" class="register-container">
	<h2>Register</h2>
	<p style="color:red">${error}</p>

	<input name="username" placeholder="Username" required><br>
	<input type="password" name="password" placeholder="Password" required><br>
	<input type="email" name="email" placeholder="Email" required><br>

	<button type="submit" class="register-btn">Create Account</button>
</form>

</body>
</html>
