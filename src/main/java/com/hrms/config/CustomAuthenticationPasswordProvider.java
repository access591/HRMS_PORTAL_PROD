package com.hrms.config;


import java.util.Base64;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.hrms.model.UserEntity;
import com.hrms.model.UserRole;
import com.hrms.service.UserRoleService;
import com.hrms.service.UserService;


@Component
public class CustomAuthenticationPasswordProvider implements AuthenticationProvider{

	@Autowired UserService userService; 
	@Autowired UserRoleService userRoleService;
	private final static String ROLE = "ROLE_";
	@Autowired BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
        //String username = token.getName();
       // Object password =  token.getCredentials(); // retrieve the password 
        
        
        String userName = authentication.getName();
        String pass = (String) authentication.getCredentials();
        
        Base64.Decoder decoder = Base64.getDecoder();  
        // Decoding string  
        String dStr = new String(decoder.decode(pass));  
        System.out.println("Decoded string: "+dStr); 
        
        System.out.println("user pass : "+ pass);
        // do something here

        
        String encrypt = passwordEncoder.encode(dStr);
        System.out.println("encrypt password is : "+ encrypt);
        UserEntity u = userService.findByUserCode(userName);
        
        
        if (u == null || !u.getUserCode().equalsIgnoreCase(userName)) {
            throw new BadCredentialsException("Username not found.");
        }
 
        if (!passwordEncoder.matches(dStr, u.getUserPass())) {
            throw new BadCredentialsException("Wrong password.");
        }
 
        
        
        List<UserRole> userRole = userRoleService.findUserRoleBuUser(userName);
        // if ok then return the authentication
        HashSet<SimpleGrantedAuthority> authorities = new HashSet<>();
        
        for (UserRole role : userRole) {

        	if(role.getRole()==null) {
        		System.out.println("role name : null");
        	}else {
        		authorities.add(new SimpleGrantedAuthority(ROLE+role.getRole().getRoleName()));
        	}
        	

		}
        
        u.setActiveStatus("Y");
        userService.update(u);
        return new UsernamePasswordAuthenticationToken(userName, dStr,authorities);
    }
    
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
    
    
}