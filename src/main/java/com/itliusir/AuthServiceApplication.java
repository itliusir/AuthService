package com.itliusir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.itliusir.module.security.core.MyFilterSecurityInterceptor;
import com.itliusir.module.security.core.MyUserDetailService;

@EnableCaching
@SpringBootApplication
public class AuthServiceApplication extends WebMvcConfigurerAdapter{
	
	@Autowired
    private MyUserDetailService myUserDetailService;
    @Autowired
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    
    /**
     * 配置权限
     */
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    @Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
    class SecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers(
                            "/admin/**"
                    ).authenticated();
            http
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/");
            http.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class);
            http.csrf().ignoringAntMatchers("/api/**");
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(myUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
        }
    }

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}
}
