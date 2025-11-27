<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.estore.model.User" %>

<!DOCTYPE html>
<html>
<head>
	<title>Dave's Pastry Shop</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

	<style>
		.cart-button {
			position: absolute;
			top: 20px;
			right: 40px;
			background: #b85c38;
			color: white;
			border: none;
			border-radius: 8px;
			padding: 8px 16px;
			cursor: pointer;
			font-weight: bold;
		}
		.cart-button:hover {
			background: #a24e2f;
		}
	</style>
</head>

<body>

<jsp:include page="components/navbar.jsp" />

<h2 style="text-align: center;">🍰 来福甜品店 🍰</h2>

<!-- Cart button on upper-right -->
<form action="cart" method="get" style="position: relative;">
	<button type="submit" class="cart-button">
		🛒 Cart
		<c:if test="${not empty sessionScope.cart && sessionScope.cart.totalCount > 0}">
			(<c:out value="${sessionScope.cart.totalCount}"/>)
		</c:if>
	</button>
</form>

<!-- Sort dropdown -->
<form action="home" method="get" style="display:inline-block; margin-right:20px;">
	<input type="hidden" name="category" value="${selectedCategory}">
	<label for="sort">Sort by:</label>
	<select name="sort" id="sort" onchange="this.form.submit()">
		<option value="">Default</option>
		<option value="priceAsc" ${selectedSort=='priceAsc'?'selected':''}>Price: Low → High</option>
		<option value="priceDesc" ${selectedSort=='priceDesc'?'selected':''}>Price: High → Low</option>
		<option value="nameAsc" ${selectedSort=='nameAsc'?'selected':''}>Name: A → Z</option>
		<option value="nameDesc" ${selectedSort=='nameDesc'?'selected':''}>Name: Z → A</option>
	</select>
</form>

<!-- Category filter dropdown -->
<form action="home" method="get" style="display:inline-block;">
	<input type="hidden" name="sort" value="${selectedSort}">
	<label for="category">Filter by:</label>
	<select name="category" id="category" onchange="this.form.submit()">
		<option value="">All</option>
		<option value="Cake" ${selectedCategory=='Cake'?'selected':''}>Cake</option>
		<option value="Cupcake" ${selectedCategory=='Cupcake'?'selected':''}>Cupcake</option>
		<option value="Bread" ${selectedCategory=='Bread'?'selected':''}>Bread</option>
	</select>
</form>

<!-- Product grid -->
<div class="grid">
	<c:forEach var="p" items="${products}">
		<div class="card">
			<a href="product?id=${p.id}">
				<img src="${pageContext.request.contextPath}/images/${p.imageUrl}" alt="${p.name}" />
			</a>
			<div class="name">${p.name}</div>
			<div class="category">${p.category}</div>
			<div class="price">$${p.price}</div>

			<!-- Add to cart form -->
			<form action="cart" method="post">
				<input type="hidden" name="action" value="add">
				<input type="hidden" name="id" value="${p.id}">
				<input type="hidden" name="category" value="${selectedCategory}">
				<input type="hidden" name="sort" value="${selectedSort}">
				<button type="submit">🛒 Add to Cart</button>
			</form>

		</div>
	</c:forEach>
</div>

</body>
</html>
