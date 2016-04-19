package me.wonwoo.config.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

import static java.util.Locale.US;

/**
 * Created by wonwoo on 2016. 4. 19..
 */
public class UserDetail extends User {
  public UserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }
}
