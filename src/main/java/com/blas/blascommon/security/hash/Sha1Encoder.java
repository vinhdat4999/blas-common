package com.blas.blascommon.security.hash;

import static com.blas.blascommon.security.SecurityUtils.sha1Encode;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Sha1Encoder implements PasswordEncoder {

  @Override
  public String encode(CharSequence rawPassword) {
    return sha1Encode(rawPassword.toString());
  }

  @Override
  public boolean matches(CharSequence rawPassword, String encodedPassword) {
    return sha1Encode(rawPassword.toString()).equals(encodedPassword);
  }

  @Override
  public boolean upgradeEncoding(String encodedPassword) {
    return PasswordEncoder.super.upgradeEncoding(encodedPassword);
  }
}