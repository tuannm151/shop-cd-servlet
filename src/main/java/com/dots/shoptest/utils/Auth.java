package com.dots.shoptest.utils;

import java.util.regex.Pattern;
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
