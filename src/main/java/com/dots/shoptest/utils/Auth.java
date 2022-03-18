package com.dots.shoptest.utils;

import com.dots.shoptest.model.User;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

public class Auth {

  public static boolean isEmailValid(String email) {
    final Pattern EMAIL_REGEX = Pattern.compile(
      "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
      Pattern.CASE_INSENSITIVE
    );
    return !EMAIL_REGEX.matcher(email).matches();
  }

  public static String hashPassword(String password) {
    return BCrypt.hashpw(password, BCrypt.gensalt(12));
  }
}
