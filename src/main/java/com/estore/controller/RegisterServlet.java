package com.estore.controller;

import com.estore.dao.UserDAO;
import com.estore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	// Handle GET -> show the registration form
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		request.getRequestDispatcher("views/register.jsp").forward(request, response);
	}

	// Handle POST -> submit the form
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);

		boolean ok = userDAO.register(user);

		if (ok) {
			// redirect to LoginServlet (NOT login.jsp)
			response.sendRedirect("login");
		} else {
			request.setAttribute("error", "Username already exists");
			request.getRequestDispatcher("views/register.jsp").forward(request, response);
		}
	}
}
