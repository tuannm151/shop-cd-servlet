package com.dots.shoptest.controller;

import com.dots.shoptest.dao.OrderDAO;
import com.dots.shoptest.model.Order;
import com.dots.shoptest.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "OrderServlet", value = "/orders")
public class OrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(
                    getServletContext().getContextPath() + "/login.jsp"
            );
            return;
        }
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("orders", OrderDAO.getOrdersByUserId(user.getId()));
        getServletContext().getRequestDispatcher("/order.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            response.sendRedirect(
                    getServletContext().getContextPath() + "/login.jsp"
            );
            return;
        }
        Order newOrder = new Order();
        newOrder.setUserId(user.getId());
        newOrder.setAddress(address);
        newOrder.setPhone(phone);
        newOrder.setStatus("PENDING");
        Integer orderId = OrderDAO.initOrder(newOrder);
        int result = OrderDAO.createOrder(orderId, user.getId());
        String url = "/cart";
        if(result == 1) {
            url = "/orders";
        }
        response.sendRedirect(getServletContext().getContextPath() + url);
    }
}
