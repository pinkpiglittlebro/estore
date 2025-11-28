package com.estore.controller.admin;

import com.estore.dao.ProductDAO;
import com.estore.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/inventory")
public class AdminInventoryServlet extends HttpServlet {

	private ProductDAO productDAO = new ProductDAO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		List<Product> products = productDAO.findAll();
		req.setAttribute("products", products);

		req.getRequestDispatcher("/views/admin/inventory.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {

		int productId = Integer.parseInt(req.getParameter("id"));
		int newQty = Integer.parseInt(req.getParameter("inventory"));

		if (newQty < 0) newQty = 0;

		productDAO.updateInventory(productId, newQty);

		resp.sendRedirect(req.getContextPath() + "/admin/inventory?updated=1");
	}
}
