package com.hrms.model;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.hrms.exception.DisabledException;
import com.hrms.util.UserSecurityUtil;

@Component
public class CustomUserDetail implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserSecurityUtil user;
	private final static String ROLE = "ROLE_";
	
	
	
	
	public CustomUserDetail(UserSecurityUtil user) {

		this.user = user;
		System.out.println("custome user detail ");
		
		
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		System.out.println("get athorites");
		HashSet<SimpleGrantedAuthority> set = new HashSet<>();

		for (UserRole role : user.getUserRoles()) {

			set.add(new SimpleGrantedAuthority(ROLE+role.getRole().getRoleName()));

		}

		return set;
	}

	@Override
	public String getPassword() {

		return user.getUserEntity().getUserPass();
	}

	@Override
	public String getUsername() {
		return user.getUserEntity().getUserCode();
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
