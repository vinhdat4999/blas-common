package com.blas.blascommon.jwt;

import static com.blas.blascommon.constants.SecurityConstant.AUTHORIZATION;
import static com.blas.blascommon.constants.SecurityConstant.BEARER_SPACE;

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
               BadPaddingException | InvalidKeyException e) {
        log.error(e.getMessage());
      }
    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
      boolean isValidToken = false;
      try {
        isValidToken = jwtTokenUtil.validateToken(jwtToken, userDetails);
      } catch (IllegalArgumentException | ExpiredJwtException | InvalidAlgorithmParameterException |
               UnrecoverableKeyException | IllegalBlockSizeException | NoSuchPaddingException |
               CertificateException | KeyStoreException | NoSuchAlgorithmException |
               BadPaddingException | InvalidKeyException e) {
        log.error(e.getMessage());
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
