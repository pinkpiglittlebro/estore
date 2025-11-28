package com.estore.controller;

import com.estore.dao.UserDAO;
import com.estore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/profile/update")
public class ProfileServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
			return;
		}

		// Get updated fields
		user.setEmail(request.getParameter("email"));
		user.setFullName(request.getParameter("fullName"));
		user.setAddress(request.getParameter("address"));
		user.setCity(request.getParameter("city"));
		user.setProvince(request.getParameter("province"));
		user.setPostal(request.getParameter("postal"));
		user.setPhone(request.getParameter("phone"));
		user.setCreditCard(request.getParameter("creditCard"));

		boolean updated = userDAO.updateUser(user);

		if (updated) {
			session.setAttribute("user", user);
			session.setAttribute("message", "Profile updated successfully!");

			response.sendRedirect(request.getContextPath() + "/orders");
			return;
		} else {
			session.setAttribute("error", "Failed to update profile.");

			response.sendRedirect(request.getContextPath() + "/orders");
			return;
		}

		// request.getRequestDispatcher("/views/order_history.jsp").forward(request, response);
	}
}
