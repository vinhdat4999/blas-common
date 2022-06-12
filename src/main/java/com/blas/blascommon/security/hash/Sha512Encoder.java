package com.blas.blascommon.security.hash;

import static com.blas.blascommon.security.SecurityUtils.sha256Encode;
import static com.blas.blascommon.security.SecurityUtils.sha512Encode;

import org.springframework.security.crypto.password.PasswordEncoder;

public class Sha512Encoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return sha512Encode(rawPassword.toString());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return sha512Encode(rawPassword.toString()).equals(encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return PasswordEncoder.super.upgradeEncoding(encodedPassword);
    }
}
