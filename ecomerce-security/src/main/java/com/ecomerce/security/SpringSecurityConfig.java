package com.ecomerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ecomerce.constant.SecurityConstant;

/**
 * SpringSecurityConfig class
 * Di sini Kita tidak menggunakan XML Config untuk Spring Security 
 * 
 * @author Adik
 */
@Configuration
@EnableWebSecurity
@Order(2)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
	
	private final UserService userService;
	private final TokenAuthenticationService tokenAuthenticationService;

	public SpringSecurityConfig() {
		super(true);
		this.userService = new UserService();
		tokenAuthenticationService = new TokenAuthenticationService(
				SecurityConstant.ecomerce, userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				//restAuthenticationEntryPoint
				.authenticationEntryPoint(restAuthenticationEntryPoint)
				
				.and()
				.anonymous()
				.and()
				.servletApi()
				.and()
				.headers()
				.cacheControl()
				.and()
				.authorizeRequests()				
				
				
				// Allow anonymous resource requests 				
				 .antMatchers("/favicon.ico")
				 .permitAll()
				 .antMatchers("**/*.html")
				 .permitAll()
				 .antMatchers("**/*.css")
				 .permitAll()
				 .antMatchers("**/*.js")
				 .permitAll()

				// Allow anonymous logins
				.antMatchers("/auth/**")
				.permitAll()

				// Allow SMS gateway
				.antMatchers("/registrasi-pasien-sms/**")
				.permitAll()
				
				// Allow SMS gateway
				.antMatchers("/report/**")
				.permitAll()

				// URL tanpa auth deklarasikan di sini
				.antMatchers("/test-tanpa-auth/**")
				.permitAll()
				.antMatchers("/test/**")
				.permitAll()
				
				.antMatchers("/api-docs.json")
				.permitAll()

				.antMatchers("/api-docs/**")
				.permitAll()
				
				
				// All other request need to be authenticated
				.anyRequest()
				.authenticated()
				.and()			
				
				// Custom Token based authentication based on the header
				// previously given to the client
				.addFilterBefore(
						new StatelessAuthenticationFilter(
								tokenAuthenticationService),
						UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(
				new BCryptPasswordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserService userDetailsService() {
		return userService;
	}

	@Bean
	public TokenAuthenticationService tokenAuthenticationService() {
		return tokenAuthenticationService;
	}
}
