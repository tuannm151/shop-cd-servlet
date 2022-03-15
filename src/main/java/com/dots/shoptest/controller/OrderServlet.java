package com.dots.shoptest.controller;

import com.dots.shoptest.dao.OrderDAO;
import com.dots.shoptest.dto.OrderDTO;
import com.dots.shoptest.model.Order;
import com.dots.shoptest.model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

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
        ArrayList<OrderDTO> orderDTOS = OrderDAO.getOrdersByUserId(user.getId());
        String url = "/order.jsp";
        if(orderDTOS != null) {
            request.setAttribute("orders", orderDTOS);
            Order lastestOrder = OrderDAO.getOrderById(orderDTOS.get(0).getId(), user.getId());
            request.setAttribute("lastestOrder", lastestOrder);
            if(orderDTOS.size() == 0) {
                request.setAttribute("empty", true);
            }
        } else {
            request.setAttribute("error", "Error while getting orders");
        }


        getServletContext().getRequestDispatcher(url).forward(request, response);
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
        OrderDTO newOrderDTO = new OrderDTO();
        newOrderDTO.setUserId(user.getId());
        newOrderDTO.setAddress(address);
        newOrderDTO.setPhone(phone);
        newOrderDTO.setStatus("pending");
        Integer orderId = OrderDAO.initOrder(newOrderDTO);
        int result = OrderDAO.createOrder(orderId, user.getId());
        String url = "/orders";
        response.sendRedirect(getServletContext().getContextPath() + url);
    }
}
