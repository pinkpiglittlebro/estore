package com.estore.controller.admin;
import com.estore.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.estore.dao.OrderDAO;
import com.estore.dao.UserDAO;

@WebServlet("/admin/user")
public class AdminUserDetailsServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();
	private OrderDAO orderDAO = new OrderDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		int userId = Integer.parseInt(req.getParameter("id"));

		try {
			User user = userDAO.getUserById(userId);
			req.setAttribute("user", user);

			req.setAttribute("orders", orderDAO.getOrdersByUser(userId));
		} catch (Exception e) {
			req.setAttribute("error", "Failed to load user details.");
		}

		req.getRequestDispatcher("/views/admin/user_details.jsp")
			.forward(req, resp);
	}
}

