package com.dots.shoptest.dao;

import com.dots.shoptest.db.DBConnection;
import com.dots.shoptest.model.User;
import com.dots.shoptest.utils.Auth;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class UserDAO {

  public static int registerUser(User user) {
    String CHECK_EMAIL_EXISTS_QUERY = "SELECT * FROM person WHERE email = ?";
    String INSERT_USER_QUERY =
      "INSERT INTO person (name, password, email) VALUES (?, ?, ?)";
    int result = 0;
    try {
      Connection conn = DBConnection.getConnection();
      if (conn == null) {
        throw new SQLException("Connection is null");
      }
      PreparedStatement preparedStatement = conn.prepareStatement(
        CHECK_EMAIL_EXISTS_QUERY
      );
      preparedStatement.setString(1, user.getEmail());
      boolean emailIsExists = preparedStatement.executeQuery().next();
      if (emailIsExists) {
        result = -1;
      } else {
        preparedStatement = conn.prepareStatement(INSERT_USER_QUERY);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, Auth.hashPassword(user.getPassword()));
        preparedStatement.setString(3, user.getEmail());
        result = preparedStatement.executeUpdate();
      }
      conn.close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static User findUser(String email, String password) {
    User user = null;
    try {
      // get password from email
      String query =
        "SELECT id, name, email, password FROM person WHERE email = ?";
      Connection conn = DBConnection.getConnection();
      if (conn == null) {
        throw new SQLException("Connection is null");
      }
      PreparedStatement preparedStatement = conn.prepareStatement(
        query,
        ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_READ_ONLY
      );
      preparedStatement.setString(1, email);
      ResultSet resultSet = preparedStatement.executeQuery();
      resultSet.first();
      String dbPassword = resultSet.getString("password");

      if (dbPassword != null && BCrypt.checkpw(password, dbPassword)) {
        user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setEmail(resultSet.getString("email"));
      }
      conn.close();
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return user;
  }
}
