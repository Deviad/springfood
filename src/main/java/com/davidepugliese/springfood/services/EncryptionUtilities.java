package com.davidepugliese.springfood.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtilities {
  public static  String encryptPassword(String password) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      return passwordEncoder.encode(password);
    }
    public static  Boolean  matches(String input_password, String db_password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(input_password, db_password);
    }
}
