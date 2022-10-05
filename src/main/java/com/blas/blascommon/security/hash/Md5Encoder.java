package com.blas.blascommon.security.hash;

import static com.blas.blascommon.security.SecurityUtils.md5Encode;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Md5Encoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return md5Encode(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return md5Encode(rawPassword.toString()).equals(encodedPassword);
  }

  @Override
  public boolean upgradeEncoding(String encodedPassword) {
    return PasswordEncoder.super.upgradeEncoding(encodedPassword);
  }
}