package com.estore.controller;

import com.estore.dao.OrderDAO;
import com.estore.dao.ProductDAO;
import com.estore.model.Order;
import com.estore.model.OrderItem;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/order")
public class OrderDetailServlet extends HttpServlet {

	private final OrderDAO orderDAO = new OrderDAO();
	private final ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		try {
			int orderId = Integer.parseInt(req.getParameter("id"));

			Order order = orderDAO.getOrderById(orderId);
			List<OrderItem> items = orderDAO.getItemsByOrderId(orderId);

			// set product names
			for (OrderItem item : items) {
				String name = productDAO.findById(item.getProductId()).getName();
				item.setProductName(name);
			}

			req.setAttribute("order", order);
			req.setAttribute("items", items);

			req.getRequestDispatcher("/views/order_detail.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendError(404, "Order not found");
		}
	}
}
