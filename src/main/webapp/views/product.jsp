<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${product.name} - Details</title>
    <style>
        body { font-family: 'Segoe UI'; background: #fffaf7; margin: 0; padding: 0; text-align: center; }
        .product-container { width: 60%; margin: 40px auto; background: white; border-radius: 12px; padding: 20px; box-shadow: 0 0 10px #eee; }
        img { max-width: 300px; border-radius: 10px; }
        h2 { color: #b85c38; margin-bottom: 10px; }
        .price { font-size: 22px; font-weight: bold; color: #a24e2f; }
        .inventory { margin-top: 8px; font-size: 14px; color: #666; }
        button { background: #b85c38; color: white; border: none; padding: 8px 16px; border-radius: 6px; cursor: pointer; margin-top: 12px; }
        button:disabled { background: #ccc; cursor: not-allowed; }
        a { color: #b85c38; text-decoration: none; display: block; margin-top: 20px; }
    </style>
</head>
<body>
<div class="product-container">
    <img src="${pageContext.request.contextPath}/images/${product.imageUrl}" alt="${product.name}">
    <h2>${product.name}</h2>
    <p>${product.category}</p>
    <p class="price">$${product.price}</p>
    <p>${product.description}</p>
    <p class="inventory">
        <c:choose>
            <c:when test="${product.inventory > 0}">
                In stock: ${product.inventory} left
            </c:when>
            <c:otherwise>
                <span style="color:red;">Out of stock</span>
            </c:otherwise>
        </c:choose>
    </p>

    <form action="cart" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="id" value="${product.id}">
        <button type="submit" <c:if test="${product.inventory <= 0}">disabled</c:if>>🛒 Add to Cart</button>
    </form>

    <a href="home">← Back to Catalog</a>
</div>
</body>
</html>
