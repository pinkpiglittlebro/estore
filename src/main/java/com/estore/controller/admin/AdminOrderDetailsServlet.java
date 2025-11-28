package com.estore.controller.admin;

import com.estore.dao.OrderDAO;
import com.estore.model.Order;
import com.estore.model.OrderItem;
import com.estore.dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/order")
public class AdminOrderDetailsServlet extends HttpServlet {

	private OrderDAO orderDAO = new OrderDAO();
	private ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		int orderId = Integer.parseInt(req.getParameter("id"));

		try {
			Order order = orderDAO.getOrderById(orderId);
			List<OrderItem> items = orderDAO.getItemsByOrderId(orderId);

			for (OrderItem item : items) {
				item.setProductName(productDAO.findById(item.getProductId()).getName());
			}

			req.setAttribute("order", order);
			req.setAttribute("items", items);
			req.getRequestDispatcher("/views/admin/order_details.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(500, "Failed to load order details.");
		}
	}
}

