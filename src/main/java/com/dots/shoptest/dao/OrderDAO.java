package com.dots.shoptest.dao;

import com.dots.shoptest.db.DBConnection;
import com.dots.shoptest.model.CartItem;
import com.dots.shoptest.model.Order;
import com.dots.shoptest.model.OrderItem;
import com.dots.shoptest.model.Product;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAO {
    public static Integer initOrder(Order order) {
        Integer orderId = null;
        try {
            String sql = "INSERT INTO person_order (person_id, address, phone, status, created_date) VALUES (?, ?, ?, ?, ?) RETURNING  id";
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, order.getUserId());
            ps.setString(2, order.getAddress());
            ps.setString(3, order.getPhone());
            ps.setString(4, order.getStatus());
            ps.setString(5, order.getCreatedDate());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                orderId = rs.getInt("id");
            }

            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orderId;
    }
    public static int createOrder(Integer orderId, Integer userId) {
        int result = 0;
        try {
            Connection conn = DBConnection.getConnection();
            String GET_CART_ITEMS = "SELECT product_id, price, quantity FROM cart_item, product WHERE cart_item.product_id = product.id AND cart_item.person_id = ?";
            PreparedStatement ps = conn.prepareStatement(GET_CART_ITEMS);
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            ArrayList<CartItem> cartItems = new ArrayList<>();
            while (rs.next()) {
                CartItem cartItem = new CartItem();
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setPrice(rs.getBigDecimal("price"));
                cartItem.setProduct(product);
                cartItem.setQuantity(rs.getInt("quantity"));
                cartItems.add(cartItem);
            }
            String INSERT_CART_ITEMS = "INSERT INTO order_item (order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)";
            BigDecimal orderTotalPrice = new BigDecimal(0);
            for (CartItem cartItem : cartItems) {
                ps = conn.prepareStatement(INSERT_CART_ITEMS);
                ps.setInt(1, orderId);
                ps.setInt(2, cartItem.getProduct().getId());
                ps.setInt(3, cartItem.getQuantity());
                ps.setBigDecimal(4, cartItem.getProduct().getPrice());
                BigDecimal cartItemTotalPrice = cartItem.getTotalPrice();
                orderTotalPrice = orderTotalPrice.add(cartItemTotalPrice);
                result = ps.executeUpdate();
            }
            if(result == 1) {
                String UPDATE_ORDER_TOTAL_PRICE = "UPDATE person_order SET total = ? WHERE id = ?";
                ps = conn.prepareStatement(UPDATE_ORDER_TOTAL_PRICE);
                ps.setBigDecimal(1, orderTotalPrice);
                ps.setInt(2, orderId);
                result = ps.executeUpdate();
            }

            if(result == 1 ){
                String DELETE_CART_ITEMS = "DELETE FROM cart_item WHERE person_id = ?";
                ps = conn.prepareStatement(DELETE_CART_ITEMS);
                ps.setInt(1, userId);
                result = ps.executeUpdate();
            }
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static ArrayList<Order> getOrdersByUserId(Integer userId) {
        ArrayList<Order> orders = null;
        try {
            Connection conn = DBConnection.getConnection();

            String GET_ORDERS = "SELECT id, person_id, address, phone, status, created_date FROM person_order WHERE person_id = ?";
            PreparedStatement ps = conn.prepareStatement(GET_ORDERS);
            ps.setInt(1, userId);
            ResultSet ordersRs = ps.executeQuery();

            orders = new ArrayList<>();
            while (ordersRs.next()) {
                Order order = new Order();
                order.setId(ordersRs.getInt("id"));
                order.setUserId(ordersRs.getInt("person_id"));
                order.setAddress(ordersRs.getString("address"));
                order.setPhone(ordersRs.getString("phone"));
                order.setStatus(ordersRs.getString("status"));
                order.setCreatedDate(ordersRs.getString("created_date"));
                order.setTotalPrice(ordersRs.getBigDecimal("total"));

                String GET_ORDER_ITEMS_COUNT = "SELECT COUNT(*) FROM order_item WHERE order_id = ?";
                ps = conn.prepareStatement(GET_ORDER_ITEMS_COUNT);
                ps.setInt(1, order.getId());
                ResultSet orderItemsCountRs = ps.executeQuery();
                orderItemsCountRs.next();

                order.setItemCount(orderItemsCountRs.getInt(1));
                orders.add(order);
            }
            conn.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
