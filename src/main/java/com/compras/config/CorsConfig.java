package com.compras.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

	@Bean
	CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();

		// Permitir solicitudes desde estos dominios
		config.addAllowedOrigin("http://localhost:5173");

		// Permitir los métodos HTTP que desees
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");

		// Permitir encabezados personalizados, si es necesario
		config.addAllowedHeader("Authorization");

		// Permitir credenciales (cookies, tokens), si es necesario
		config.setAllowCredentials(true);

		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
}
