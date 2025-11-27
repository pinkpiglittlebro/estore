<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.estore.model.User" %>

<jsp:include page="components/navbar.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Login - Pastry Store</title>
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>

<body class="auth-body">

<div class="login-container">
	<h2>Login</h2>

	<p style="color:red; font-size:14px;">
		${error}
	</p>

	<form action="<%=request.getContextPath()%>/login" method="post">
		<input type="text" name="username" placeholder="Username" required>
		<input type="password" name="password" placeholder="Password" required>
		<button type="submit" class="login-btn">Sign In</button>
	</form>

	<a href="<%=request.getContextPath()%>/views/register.jsp" class="register-link">
		New user? Create an account →
	</a>
</div>

</body>
</html>
