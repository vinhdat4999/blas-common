package com.blas.blascommon.security.hash;

import static com.blas.blascommon.security.SecurityUtils.sha256Encode;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha256Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return sha256Encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return sha256Encode(rawPassword.toString()).equals(encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
