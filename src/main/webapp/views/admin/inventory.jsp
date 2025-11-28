<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="admin_nav.jsp" />


<h2 style="text-align:center; margin-top:20px;">Inventory Management</h2>

<c:if test="${param.updated == '1'}">
	<p style="color:green; text-align:center; font-weight:bold;">
		Inventory updated successfully!
	</p>
</c:if>

<table border="1" cellpadding="10" cellspacing="0" style="margin:auto; margin-top:20px; width:80%;">
	<tr>
		<th>ID</th>
		<th>Name</th>
		<th>Category</th>
		<th>Price</th>
		<th>Inventory</th>
		<th>Action</th>
	</tr>

	<c:forEach var="p" items="${products}">
		<tr>
			<td>${p.id}</td>
			<td>${p.name}</td>
			<td>${p.category}</td>
			<td>$${p.price}</td>
			<td>${p.inventory}</td>

			<td>
				<form action="${pageContext.request.contextPath}/admin/inventory"
					  method="post"
					  style="display:flex; gap:6px; align-items:center;">

					<input type="hidden" name="id" value="${p.id}">
					<input type="number" name="inventory" min="0" value="${p.inventory}"
						   style="width:70px; padding:4px;">

					<button type="submit" style="padding:4px 10px; background:#b85c38; color:white; border:none;">
						Update
					</button>
				</form>
			</td>
		</tr>
	</c:forEach>
</table>
