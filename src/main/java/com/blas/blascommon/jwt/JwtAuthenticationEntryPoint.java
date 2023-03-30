package com.blas.blascommon.jwt;

import static com.blas.blascommon.constants.SecurityConstant.EXPIRED;
import static com.blas.blascommon.constants.SecurityConstant.UNAUTHORIZED;
import static java.util.Objects.requireNonNullElse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException {
    final String expired = request.getAttribute(EXPIRED.toLowerCase()).toString();
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
        requireNonNullElse(expired, UNAUTHORIZED));
  }
}
