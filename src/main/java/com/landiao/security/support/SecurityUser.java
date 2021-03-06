package com.landiao.security.support;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.landiao.security.entity.SystemRole;
import com.landiao.security.entity.User;

public class SecurityUser extends User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
	
	private List<SystemRole> userRoles;

	public SecurityUser(User user, List<SystemRole> userRoles) {
		if (user != null) {
			this.setId(user.getId());
			this.setName(user.getName());
			this.setPassword(user.getPassword());
			this.setCreateTime(user.getCreateTime());
			this.userRoles = userRoles;
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		Collection<GrantedAuthority> authorities = new ArrayList<>();

		if (userRoles != null) {
			for (SystemRole role : userRoles) {
				SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
				authorities.add(authority);
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return super.getPassword();
	}

	@Override
	public String getUsername() {
		return super.getName();
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
