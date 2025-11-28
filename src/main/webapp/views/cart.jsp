<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.estore.model.User" %>
<jsp:include page="components/navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
    <title>Your Shopping Cart</title>
    <style>
        body {
            font-family: 'Segoe UI';
            background: #fffaf7;
            text-align: center;
            margin: 0;
            padding: 0;
        }
        h2 {
            color: #b85c38;
            margin: 20px 0;
        }
        table {
            border-collapse: collapse;
            width: 80%;
            margin: auto;
            background: white;
        }
        th, td {
            border: 1px solid #e3c5b1;
            padding: 10px;
            text-align: center;
        }
        th {
            background: #f9dec9;
        }
        button {
            background: #b85c38;
            color: white;
            border: none;
            padding: 6px 12px;
            border-radius: 6px;
            cursor: pointer;
        }
        button:hover {
            background: #a24e2f;
        }
        input[type="number"] {
            width: 60px;
            text-align: center;
            border: 1px solid #ccc;
            border-radius: 4px;
            padding: 4px;
        }
        .actions form {
            display: inline;
        }
    </style>
</head>
<body>

<h2>Your Cart</h2>

<c:if test="${not empty sessionScope.error}">
	<p style="color:red; font-weight:bold; text-align:center;">
			${sessionScope.error}
	</p>
	<c:remove var="error" scope="session"/>
</c:if>


<c:if test="${empty sessionScope.cart.items}">
    <p>Your cart is empty.</p>
    <a href="home">← Continue Shopping</a>
</c:if>

<c:if test="${not empty sessionScope.cart.items}">    <table>
        <tr>
            <th>Product</th>
            <th>Category</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Subtotal</th>
            <th>Action</th>
        </tr>

    <c:forEach var="item" items="${sessionScope.cart.items}">
            <tr>
                <td>${item.product.name}</td>
                <td>${item.product.category}</td>
                <td>$${item.product.price}</td>
                <td>
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="id" value="${item.product.id}">
                        <input type="number" name="quantity" value="${item.quantity}" min="1">
                        <button type="submit">🔁 Update</button>
                    </form>
                </td>
                <td>$${item.subtotal}</td>
                <td class="actions">
                    <form action="cart" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="id" value="${item.product.id}">
                        <button type="submit">❌ Remove</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <h3>Total: $${sessionScope.cart.total}</h3>

    <form action="cart" method="post">
        <input type="hidden" name="action" value="clear">
        <button type="submit">🧹 Clear Cart</button>
    </form>

	<br><br>
	<form action="checkout" method="get">
		<button type="submit" style="background:#3a8f3a; color:white; padding:10px 20px; border-radius:6px;">
			Check Out
		</button>
	</form>


	<br>
    <!-- continue shopping link that remembers filters/sort -->
    <c:url var="backUrl" value="home">
        <c:if test="${not empty sessionScope.lastCategory}">
            <c:param name="category" value="${sessionScope.lastCategory}" />
        </c:if>
        <c:if test="${not empty sessionScope.lastSort}">
            <c:param name="sort" value="${sessionScope.lastSort}" />
        </c:if>
    </c:url>

    <a href="${backUrl}" style="color:#b85c38; text-decoration:none; font-weight:bold;">
        ← Continue Shopping
    </a>

</c:if>
</body>
</html>
