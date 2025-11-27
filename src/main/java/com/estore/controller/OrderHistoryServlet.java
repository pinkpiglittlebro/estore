package com.estore.controller;

import com.estore.dao.OrderDAO;
import com.estore.model.Order;
import com.estore.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/orders")
public class OrderHistoryServlet extends HttpServlet {

	private final OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");

		if (user == null) {
			resp.sendRedirect("login");
			return;
		}

		try {
			List<Order> orders = orderDAO.getOrdersByUser(user.getId());
			req.setAttribute("orders", orders);
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Unable to load order history.");
		}

		req.getRequestDispatcher("/views/order_history.jsp")
			.forward(req, resp);
	}


}
