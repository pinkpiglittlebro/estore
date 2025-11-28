package com.estore.controller.admin;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.estore.dao.UserDAO;
import com.estore.model.User;

@WebServlet("/admin/user/update")
public class AdminUpdateUserServlet extends HttpServlet {

	private UserDAO userDAO = new UserDAO();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("id"));

		try {
			User u = userDAO.getUserById(id);

			u.setFullName(req.getParameter("fullName"));
			u.setEmail(req.getParameter("email"));
			u.setAddress(req.getParameter("address"));
			u.setCity(req.getParameter("city"));
			u.setProvince(req.getParameter("province"));
			u.setPostal(req.getParameter("postal"));
			u.setPhone(req.getParameter("phone"));
			u.setCreditCard(req.getParameter("creditCard"));

			userDAO.updateUser(u);

			resp.sendRedirect(req.getContextPath() + "/admin/user?id=" + id);

		} catch (Exception e) {
			resp.sendRedirect(req.getContextPath() + "/admin/users?error=update_failed");
		}
	}
}

