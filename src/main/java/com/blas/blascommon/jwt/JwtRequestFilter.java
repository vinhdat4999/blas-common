package com.blas.blascommon.jwt;

import static com.blas.blascommon.constants.SecurityConstant.AUTHORIZATION;
import static com.blas.blascommon.constants.SecurityConstant.BEARER_SPACE;
import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_ACCOUNT_BLOCKED;
import static org.apache.commons.codec.CharEncoding.UTF_8;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

  @Lazy
  private final JwtUserDetailsService jwtUserDetailsService;

  @Lazy
  private final JwtTokenUtil jwtTokenUtil;

  @Lazy
  private final ObjectMapper objectMapper;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain chain)
      throws ServletException, IOException {
    String requestTokenHeader = request.getHeader(AUTHORIZATION);
    String username = null;
    String jwtToken = null;
    if (requestTokenHeader != null && requestTokenHeader.startsWith(BEARER_SPACE)) {
      jwtToken = requestTokenHeader.substring(7);
      try {
        username = jwtTokenUtil.getUsernameFromToken(jwtToken);
      } catch (IllegalArgumentException | ExpiredJwtException | InvalidAlgorithmParameterException |
               UnrecoverableKeyException | IllegalBlockSizeException | NoSuchPaddingException |
               CertificateException | KeyStoreException | NoSuchAlgorithmException |
               BadPaddingException | InvalidKeyException exception) {
        log.error(exception.getMessage());
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
      if (!userDetails.isAccountNonLocked()) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(UTF_8);
        response.getWriter().write(objectMapper.writeValueAsString(MSG_ACCOUNT_BLOCKED));
        return;
      }
      boolean isValidToken = false;
      try {
        isValidToken = jwtTokenUtil.validateToken(jwtToken, userDetails);
      } catch (IllegalArgumentException | ExpiredJwtException | InvalidAlgorithmParameterException |
               UnrecoverableKeyException | IllegalBlockSizeException | NoSuchPaddingException |
               CertificateException | KeyStoreException | NoSuchAlgorithmException |
               BadPaddingException | InvalidKeyException exception) {
        log.error(exception.getMessage());
      }
      if (isValidToken) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
            userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext()
            .setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }
}
