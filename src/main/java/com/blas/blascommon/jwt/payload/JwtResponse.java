package com.blas.blascommon.jwt.payload;

public class JwtResponse {

    private final String jwtToken;

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getToken() {
        return this.jwtToken;
    }

    @Override
    public String toString() {
        return "JwtResponse{" +
                "jwtToken='" + jwtToken + '\'' +
                '}';
    }
}

