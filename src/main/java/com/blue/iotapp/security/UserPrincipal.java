package com.blue.iotapp.security;

import com.blue.iotapp.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UserPrincipal implements UserDetails {
  private Long id;

  private String name;

  private String username;

  @JsonIgnore
  private String password;

  private Collection<? extends GrantedAuthority> authorities;

  public static UserPrincipal create(User user) {
    List<GrantedAuthority> authorities = user.getRoles().stream().map(role ->
        new SimpleGrantedAuthority(role.getName())
    ).collect(Collectors.toList());

    return new UserPrincipal(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getPassword(),
        authorities
    );
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
