package com.dots.shoptest.controller;

import com.dots.shoptest.dao.CartDAO;
import com.dots.shoptest.dao.ProductDAO;
import com.dots.shoptest.model.Product;
import com.dots.shoptest.model.User;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "HomePageServlet", value = "/index.jsp")
public class HomePageServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String url = "/login.jsp";
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(getServletContext().getContextPath() + url);
      return;
    }
    url = "/WEB-INF/index.jsp";
    ArrayList<Product> products = ProductDAO.getAllProducts();
    int cartCount = CartDAO.getCartItemsCount(user.getId());
    if (products != null) {
      request.setAttribute("products", products);
      request.setAttribute("cartCount", cartCount);
    } else {
      request.setAttribute("error", "Error while loading products");
    }
    getServletContext().getRequestDispatcher(url).forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    doGet(request, response);
  }
}
