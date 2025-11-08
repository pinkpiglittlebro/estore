package com.estore.controller;

import com.estore.dao.ProductDAO;
import com.estore.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect("home");
            return;
        }

        try {
            long id = Long.parseLong(idParam);
            Product product = productDAO.findById(id);

            if (product == null) {
                req.setAttribute("errorMessage", "Product not found!");
                req.getRequestDispatcher("/views/error.jsp").forward(req, resp);
                return;
            }

            req.setAttribute("product", product);
            req.getRequestDispatcher("/views/product.jsp").forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendRedirect("home");
        }
    }
}
