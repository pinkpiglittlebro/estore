package com.estore.controller;

import com.estore.dao.ProductDAO;
import com.estore.model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    private final ProductDAO productDAO = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String category = req.getParameter("category");
        String sort = req.getParameter("sort");

        List<Product> products = productDAO.findAll();

        //Filter by category
        if (category != null && !category.isEmpty()) {
            products = products.stream()
                    .filter(p -> p.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
            req.setAttribute("selectedCategory", category);
        }

        // Sort results
        if (sort != null && !sort.isEmpty()) {
            switch (sort) {
                case "priceAsc":
                    products.sort(Comparator.comparing(Product::getPrice));
                    break;
                case "priceDesc":
                    products.sort(Comparator.comparing(Product::getPrice).reversed());
                    break;
                case "nameAsc":
                    products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER));
                    break;
                case "nameDesc":
                    products.sort(Comparator.comparing(Product::getName, String.CASE_INSENSITIVE_ORDER).reversed());
                    break;
            }
            req.setAttribute("selectedSort", sort);
        }

        req.setAttribute("products", products);

        // Save the user's last viewed filter/sort to session
        HttpSession session = req.getSession();
        session.setAttribute("lastCategory", category);
        session.setAttribute("lastSort", sort);

        req.getRequestDispatcher("/views/catalog.jsp").forward(req, resp);
    }
}
