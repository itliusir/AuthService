package com.itliusir.module.security.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itliusir.module.security.core.JwtTokenUtil;
import com.itliusir.module.security.core.JwtUser;
import com.itliusir.module.security.core.service.JwtAuthenticationResponse;
import com.itliusir.module.user.entity.User;
import com.itliusir.module.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@RestController
public class LoginController {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    //public static final String loginname = "admin";
    
//    public static final String loginpwd = "$2a$10$vrRXrBN7.5fAkaHXN7HFY..4GuYh2zAnPrp/.f0qVHmhl6v6GGa5C";
    @RequestMapping(method = RequestMethod.POST, path = "/login", produces = "application/json;charset=utf8")
    public Map<String, String> login(@RequestParam(value = "username") String username,@RequestParam(value = "password") String password, Device device) {
    	/*User user = userService.findByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("invalid key");
        }*/
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // Perform the security
        final String token = jwtTokenUtil.generateToken(userDetails, device);

        // Return the token
        // return ResponseEntity.ok(new JwtAuthenticationResponse(token));
        HashMap<String, String> r = new HashMap<String, String>();
        r.put("token", token);

        return r;
    }
    
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(new JwtAuthenticationResponse(refreshedToken));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
