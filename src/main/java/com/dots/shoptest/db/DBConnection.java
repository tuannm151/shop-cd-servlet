package com.dots.shoptest.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

  public static Connection getConnection()
    throws SQLException, ClassNotFoundException {
    // postgresql
    Class.forName("org.postgresql.Driver");
    String url = "jdbc:postgresql://localhost:5432/shoptest";
    String user = "postgres";
    String password = "05112001";

    Connection connection = DriverManager.getConnection(url, user, password);
    System.out.println("Connected to the PostgreSQL server successfully.");
    return connection;
  }
}
