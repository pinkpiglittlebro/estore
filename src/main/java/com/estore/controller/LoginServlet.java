package com.estore.controller;

import com.estore.dao.UserDAO;
import com.estore.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		// If already logged in, redirect to home
		if (request.getSession().getAttribute("user") != null) {
			response.sendRedirect("home");
			return;
		}

		request.getRequestDispatcher("views/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User u = userDAO.login(username, password);

		if (u != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", u);   // store logged-in user

			// redirect to home after login
			response.sendRedirect("home");

		} else {
			request.setAttribute("error", "Invalid username or password");
			request.getRequestDispatcher("views/login.jsp").forward(request, response);
		}
	}
}
