package com.itliusir.module.security.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.itliusir.module.security.core.JwtAuthenticationEntryPoint;
import com.itliusir.module.security.core.JwtAuthenticationTokenFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
/**
 * 配置类 通过@EnableWebSecurity注解开启Spring Security的功能
 * 继承WebSecurityConfigurerAdapter，并重写它的方法来设置一些Web安全的细节
 * 使用 @EnableGlobalMethodSecurity 注解来启用基于注解的安全性。
 * */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configureAuthentication(
			AuthenticationManagerBuilder authenticationManagerBuilder)
			throws Exception {
		authenticationManagerBuilder
		 		// 设置UserDetailsService,使用BCrypt进行密码的hash
				.userDetailsService(this.userDetailsService).passwordEncoder(
						passwordEncoder());
	}
	// 装载BCrypt密码编码器
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	//新建一个filter，并把它配置在 WebSecurityConfig 中。
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean()
			throws Exception {
		return new JwtAuthenticationTokenFilter();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
				// we don't need CSRF because our token is invulnerable
				.csrf()
				.disable()

				.exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler)
				.and()
				// don't create session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				//通过authorizeRequests()定义哪些URL需要被保护、哪些不需要被保护。例如/*.html和/*.js不需要任何认证就可以访问，其他路径需要通过身份验证
				//authorizeRequests() 方法有多个子节点，每个macher按照他们的声明顺序执行。
				.authorizeRequests()
				// .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				// allow anonymous resource requests
				//.permitAll() 方法允许所有URL的所有用户的访问。
				.antMatchers(
						"/*.html",
						"/*.js",
						"/*.jsp",
                        "/favicon.ico",
                        "/**/*.ttf",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.jsp",
                        "/**/*.js").permitAll()
				//.antMatchers(HttpMethod.GET, "/user").permitAll()
				.antMatchers(HttpMethod.POST, "/login").permitAll()
				.antMatchers(HttpMethod.POST, "/**").permitAll() // debug 时, 不用对
																	// Authorization
																	// 作验证
				.anyRequest().authenticated();

		// 添加JWT filter
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
				UsernamePasswordAuthenticationFilter.class);
		
		// 禁用缓存
		httpSecurity.headers().cacheControl();
	}
	
}