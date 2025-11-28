package com.estore.controller.admin;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.estore.dao.UserDAO;
import com.estore.model.User;

@WebServlet("/admin/users")
public class AdminUsersServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		try {
			req.setAttribute("users", userDAO.getAllUsers());
		} catch (Exception e) {
			req.setAttribute("error", "Failed to load users.");
		}

		req.getRequestDispatcher("/views/admin/manage_users.jsp")
			.forward(req, resp);
	}
}
