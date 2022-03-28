package com.dots.shoptest.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnectionPool {

  private static DataSource dataSource;

  static {
    try {
      InitialContext ctx = new InitialContext();
      dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/shoptest");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static Connection getConnection() throws SQLException {
    return dataSource.getConnection();
  }
}
