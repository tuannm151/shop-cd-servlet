package com.dots.shoptest.controller;

import com.dots.shoptest.dao.UserDAO;
import com.dots.shoptest.model.User;
import com.dots.shoptest.utils.Auth;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

  @Override
  protected void doGet(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    if (request.getSession().getAttribute("isAuthenticated") == null) {
      response.sendRedirect(
        getServletContext().getContextPath() + "/login.jsp"
      );
      return;
    }
    response.sendRedirect(getServletContext().getContextPath() + "/");
  }

  @Override
  protected void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException {
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    if (
      password == null ||
      password.trim().equals("") ||
      email == null ||
      Auth.isEmailValid(email)
    ) {
      return;
    }
    User user = UserDAO.findUser(email, password);
    if (user == null) {
      request.setAttribute("error", "Invalid email or password");
      request.setAttribute("email", email);
      getServletContext()
        .getRequestDispatcher("/login.jsp")
        .forward(request, response);
      return;
    }
    HttpSession session = request.getSession();
    session.setAttribute("isAuthenticated", true);
    session.setAttribute("user", user);
    response.sendRedirect(getServletContext().getContextPath() + "/");
  }
}
