package com.estore.controller;

import com.estore.dao.ProductDAO;
import com.estore.model.Product;
import com.estore.model.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "CartServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {


        Cart cart = getOrCreateCart(req);

        System.out.println("Viewing cart | cart size = " + cart.getTotalCount());

        req.setAttribute("cart", cart);
        req.getRequestDispatcher("/views/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");
        Cart cart = getOrCreateCart(req);

        if ("add".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            Product product = productDAO.findAll().stream()
                    .filter(p -> p.getId() == id)
                    .findFirst().orElse(null);
            if (product != null) {
                cart.addItem(product, 1);
                req.getSession().setAttribute("cart", cart);
                System.out.println("Added product ID " + id + " | cart size = " + cart.getTotalCount());
            }

            // only add goes back to home (preserving filters)
            String category = req.getParameter("category");
            String sort = req.getParameter("sort");

            String redirectUrl = "home";
            boolean hasQuery = false;

            if (category != null && !category.isEmpty()) {
                redirectUrl += "?category=" + category;
                hasQuery = true;
            }
            if (sort != null && !sort.isEmpty()) {
                redirectUrl += hasQuery ? "&" : "?";
                redirectUrl += "sort=" + sort;
            }

            resp.sendRedirect(redirectUrl);
            return; // stop further execution
        }

        //for update/remove/clear
        if ("remove".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            cart.removeItem(id);
        } else if ("clear".equals(action)) {
            cart.clear();
        } else if ("update".equals(action)) {
            long id = Long.parseLong(req.getParameter("id"));
            int qty = Integer.parseInt(req.getParameter("quantity"));
            cart.updateQuantity(id, qty);
        }

        req.getSession().setAttribute("cart", cart);

        //stay on the cart page
        resp.sendRedirect("cart");
    }


    private Cart getOrCreateCart(HttpServletRequest req) {
        HttpSession session = req.getSession(true);
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}
