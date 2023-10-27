package com.compras.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.compras.auth.filters.JwtAuthenticationFilter;
import com.compras.auth.filters.JwtValidationFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager() throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				.authorizeHttpRequests((authz) -> authz
						.requestMatchers(HttpMethod.GET, "/user/users", "/product/products").permitAll()
						.requestMatchers(HttpMethod.GET, "/user/{id}").hasAnyRole("USER", "ADMIN")
						.requestMatchers(HttpMethod.POST, "/user").hasRole("ADMIN")
						.requestMatchers("/user/**").hasRole("ADMIN")
						.anyRequest().authenticated())
				.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
				.addFilter(new JwtValidationFilter(authenticationConfiguration.getAuthenticationManager()))
				.csrf(config -> config.disable()) // para evitar exploit, lo desabilitamos porque la sec va con jwt
				.sessionManagement(managment -> managment.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

}
