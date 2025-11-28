package com.estore.filter;

import com.estore.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
		throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		User u = (session != null) ? (User) session.getAttribute("user") : null;

		if (u == null || !"ADMIN".equals(u.getRole())) {
			resp.sendRedirect(req.getContextPath() + "/access-denied.jsp");
			return;
		}

		chain.doFilter(request, response);
	}
}
