package com.dots.shoptest.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Order {
    private int id;
    private int userId;
    private String address;
    private String phone;
    private String status;
    private final String created_date;

    public Order() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.created_date = LocalDate.now().format(formatter);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedDate() {
        return created_date;
    }
}


