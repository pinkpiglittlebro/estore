package com.estore.controller;

import com.estore.dao.OrderDAO;
import com.estore.model.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {

	private final OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		HttpSession session = req.getSession();
		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null || cart.getTotalCount() == 0) {
			req.setAttribute("error", "Your cart is empty!");
			req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
			return;
		}

		// read form fields
		String fullName = req.getParameter("fullName");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		String postal = req.getParameter("postal");
		String phone = req.getParameter("phone");

		// payment info (ignored, just simulate)
		String card = req.getParameter("cardNumber");
		if (card == null || card.length() < 8) {
			req.setAttribute("error", "Invalid card number.");
			req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
			return;
		}

		User user = (User) session.getAttribute("user");

		Order order = new Order();
		order.setUserId(user != null ? user.getId() : null);
		order.setTotal(cart.getTotal().doubleValue());
		order.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

		order.setFullName(fullName);
		order.setAddress(address);
		order.setCity(city);
		order.setPostal(postal);
		order.setPhone(phone);

		order.setItems(
			cart.getItems().stream()
				.map(i -> new OrderItem(
					i.getProduct().getId(),
					i.getQuantity(),
					i.getProduct().getPrice().doubleValue()
				))
				.collect(Collectors.toList())
		);

		try {
			long orderId = orderDAO.saveOrder(order);

			// clear cart
			cart.clear();
			session.setAttribute("cart", cart);

			resp.sendRedirect(req.getContextPath() + "/views/order_success.jsp?id=" + orderId);

		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Failed to place order.");
			req.getRequestDispatcher("/views/checkout.jsp").forward(req, resp);
		}
	}
}
