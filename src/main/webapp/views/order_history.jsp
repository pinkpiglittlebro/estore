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

<div style="width:60%; margin:auto; margin-top:20px; background:#fffaf7; padding:20px; border:1px solid #e3c5b1; border-radius:8px;">
	<h3 style="color:#b85c38;">Your Profile</h3>

	<form action="${pageContext.request.contextPath}/profile/update" method="post" style="margin-top:20px;">

		<label>Full Name:</label><br>
		<input type="text" name="fullName" value="${sessionScope.user.fullName}" /><br><br>

		<label>Email:</label><br>
		<input type="email" name="email" value="${sessionScope.user.email}" /><br><br>

		<label>Address:</label><br>
		<input type="text" name="address" value="${sessionScope.user.address}" /><br><br>

		<label>City:</label><br>
		<input type="text" name="city" value="${sessionScope.user.city}" /><br><br>

		<label>Province:</label><br>
		<select name="province" style="width:150px; padding:6px; border-radius:6px;">
			<option value="">-- Select --</option>

			<option value="AB" ${sessionScope.user.province == 'AB' ? 'selected' : ''}>AB</option>
			<option value="BC" ${sessionScope.user.province == 'BC' ? 'selected' : ''}>BC</option>
			<option value="MB" ${sessionScope.user.province == 'MB' ? 'selected' : ''}>MB</option>
			<option value="NB" ${sessionScope.user.province == 'NB' ? 'selected' : ''}>NB</option>
			<option value="NL" ${sessionScope.user.province == 'NL' ? 'selected' : ''}>NL</option>
			<option value="NS" ${sessionScope.user.province == 'NS' ? 'selected' : ''}>NS</option>
			<option value="NT" ${sessionScope.user.province == 'NT' ? 'selected' : ''}>NT</option>
			<option value="NU" ${sessionScope.user.province == 'NU' ? 'selected' : ''}>NU</option>
			<option value="ON" ${sessionScope.user.province == 'ON' ? 'selected' : ''}>ON</option>
			<option value="PE" ${sessionScope.user.province == 'PE' ? 'selected' : ''}>PE</option>
			<option value="QC" ${sessionScope.user.province == 'QC' ? 'selected' : ''}>QC</option>
			<option value="SK" ${sessionScope.user.province == 'SK' ? 'selected' : ''}>SK</option>
			<option value="YT" ${sessionScope.user.province == 'YT' ? 'selected' : ''}>YT</option>
		</select>
		<br><br>

		<label>Postal Code:</label><br>
		<input type="text" name="postal" value="${sessionScope.user.postal}" /><br><br>

		<label>Credit Card:</label><br>
		<input type="text" name="creditCard" value="${sessionScope.user.creditCard}" /><br><br>

		<input type="submit" value="Update Profile" />
	</form>

	<c:if test="${not empty sessionScope.message}">
		<p style="color:green; font-weight:bold;">
				${sessionScope.message}
		</p>
		<c:remove var="message" scope="session"/>
	</c:if>

	<c:if test="${not empty sessionScope.error}">
		<p style="color:red; font-weight:bold;">
				${sessionScope.error}
		</p>
		<c:remove var="error" scope="session"/>
	</c:if>


</div>

<h2 style="text-align:center; margin-top:20px; color:#b85c38;">
	Your Order History
</h2>




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
