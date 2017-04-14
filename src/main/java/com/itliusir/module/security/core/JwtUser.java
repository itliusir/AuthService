package com.itliusir.module.security.core;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collection;
import java.util.Date;

/**
 * 默认密码为空字符串 , 并且设置为启用,没有锁定 . 没有过期.
 */
public class JwtUser implements UserDetails {
	private final int id;
	private final String username;
	private final String password;
	private final Collection<? extends GrantedAuthority> roles;
	private final Date lastPasswordResetDate;

	public JwtUser(int id, String username, String password,
			Collection<? extends GrantedAuthority> roles,
			Date lastPasswordResetDate) {
		this.id = id;
		this.username = username;
		// this.firstname = firstname;
		// this.lastname = lastname;
		// this.email = email;
		// this.password = LoginController.loginpwd;
		this.password = password;
		this.roles = roles;
		// this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
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
	
	@JsonIgnore
    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

}
