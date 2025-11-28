package com.estore.controller.admin;

import com.estore.dao.OrderDAO;
import com.estore.model.Order;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/sales")
public class AdminSalesServlet extends HttpServlet {

	private OrderDAO orderDAO = new OrderDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// Filters
		String customer = request.getParameter("customer");
		String product = request.getParameter("product");
		String dateFrom = request.getParameter("dateFrom");
		String dateTo = request.getParameter("dateTo");

		List<Order> orders = orderDAO.searchOrders(customer, product, dateFrom, dateTo);

		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/views/admin/admin_sales.jsp").forward(request, response);
	}
}
