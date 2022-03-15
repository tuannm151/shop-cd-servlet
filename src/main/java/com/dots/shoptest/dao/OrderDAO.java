package com.dots.shoptest.dao;

import com.dots.shoptest.db.DBConnection;
import com.dots.shoptest.model.CartItem;
import com.dots.shoptest.model.Order;
import com.dots.shoptest.model.Product;

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
            for (CartItem cartItem : cartItems) {
                ps = conn.prepareStatement(INSERT_CART_ITEMS);
                ps.setInt(1, orderId);
                ps.setInt(2, cartItem.getProduct().getId());
                ps.setInt(3, cartItem.getQuantity());
                ps.setBigDecimal(4, cartItem.getProduct().getPrice());
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
}
