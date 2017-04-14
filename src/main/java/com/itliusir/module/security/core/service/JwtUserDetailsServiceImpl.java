package com.itliusir.module.security.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itliusir.module.security.core.JwtUser;
import com.itliusir.module.security.core.JwtUserFactory;
import com.itliusir.module.security.core.controller.LoginController;
import com.itliusir.module.user.entity.User;
import com.itliusir.module.user.service.UserService;


@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    	User user = userService.findByUsername(username);
    	 if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            return JwtUserFactory.create(user);
        }

    }
}
