package com.blas.blascommon.jwt;

import static com.blas.blascommon.utils.StringUtils.EMPTY;

import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.Role;
import com.blas.blascommon.core.service.AuthUserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

  @Lazy
  private final AuthUserService authUserService;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    AuthUser authUser = authUserService.getAuthUserByUsername(username);
    if (authUser == null) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }
    String userRole = Optional.ofNullable(authUser.getRole()).map(Role::getRoleName).orElse(EMPTY);
    List<GrantedAuthority> grantList = new ArrayList<>();
    GrantedAuthority authority = new SimpleGrantedAuthority(userRole);
    grantList.add(authority);
    return org.springframework.security.core.userdetails.User.builder()
        .username(authUser.getUsername())
        .password(authUser.getPassword())
        .disabled(!authUser.isActive())
        .accountExpired(false)
        .credentialsExpired(false)
        .accountLocked(authUser.isBlock())
        .authorities(grantList)
        .build();
  }
}
