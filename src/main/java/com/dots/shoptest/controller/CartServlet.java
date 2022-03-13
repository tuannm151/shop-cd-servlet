package com.dots.shoptest.controller;

import com.dots.shoptest.dao.CartDAO;
import com.dots.shoptest.model.Cart;
import com.dots.shoptest.model.User;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "CartServlet", value = "/cart")
public class CartServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(
        getServletContext().getContextPath() + "/login.jsp"
      );
      return;
    }
    Cart cart = CartDAO.getCart(user.getId());
    if (cart != null) {
      request.setAttribute("cart", cart);
    } else {
      request.setAttribute("error", "Something went wrong");
    }
    request.getRequestDispatcher("/cart.jsp").forward(request, response);
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String action = request.getParameter("action");
    if (action != null) {
      if (action.equals("add")) {
        doPost_Add(request, response);
      }
      if (action.equals("reduce")) {
        doPost_Reduce(request, response);
      }
      if (action.equals("delete")) {
        doPost_Delete(request, response);
      }
    }
  }

  protected void doPost_Add(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    int productId = Integer.parseInt(request.getParameter("productId"));
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(
        getServletContext().getContextPath() + "/login.jsp"
      );
      return;
    }
    int result = CartDAO.addToCart(user.getId(), productId);
    if (result == 1) {
      System.out.println("Added to cart");
    } else {
      System.out.println("Failed to add to cart");
    }
  }

  protected void doPost_Reduce(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    int productId = Integer.parseInt(request.getParameter("productId"));
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(
        getServletContext().getContextPath() + "/login.jsp"
      );
      return;
    }
    int result = CartDAO.reduceCartItemQuantity(user.getId(), productId);
    if (result == 1) {
      System.out.println("Reduced cart item quantity");
    } else {
      System.out.println("Failed to reduce cart item quantity");
    }
  }

  protected void doPost_Delete(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    int productId = Integer.parseInt(request.getParameter("productId"));
    User user = (User) request.getSession().getAttribute("user");
    if (user == null) {
      response.sendRedirect(
        getServletContext().getContextPath() + "/login.jsp"
      );
      return;
    }
    int result = CartDAO.deleteCartItem(user.getId(), productId);
    if (result == 1) {
      System.out.println("Deleted from cart");
    } else {
      System.out.println("Failed to delete from cart");
    }
  }
}
