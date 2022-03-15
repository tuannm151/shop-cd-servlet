package com.dots.shoptest.model;

import java.math.BigDecimal;

public class OrderItem {
    private int id;
    private Product product;
    private BigDecimal price;
    private int quantity;
    private int orderId;

    public OrderItem() {
    }

    public OrderItem(int id, Product product, BigDecimal price, int quantity) {
        this.id = id;
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTotalPrice() {
        return price.multiply(new BigDecimal(quantity));
    }
}
