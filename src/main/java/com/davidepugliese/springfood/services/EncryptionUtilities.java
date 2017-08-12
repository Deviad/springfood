package com.davidepugliese.springfood.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncryptionUtilities {
  public static  String encryptPassword(String password) {
      BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
      return passwordEncoder.encode(password);
    }
}
