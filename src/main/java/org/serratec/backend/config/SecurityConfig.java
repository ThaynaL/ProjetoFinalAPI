package org.serratec.backend.config;

import java.util.Arrays;

import org.serratec.backend.security.JwtAuthenticationFilter;
import org.serratec.backend.security.JwtAuthorizationFilter;
import org.serratec.backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.csrf(csrf -> csrf.disable())
	        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
	        .authorizeHttpRequests(requests -> requests
	        	    .requestMatchers("/public/**").permitAll()
	        	    .requestMatchers("/h2-console/**").permitAll()
					.requestMatchers("/auth/login").permitAll()
	        	    .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
	        	    .requestMatchers("/funcionarios").permitAll()

	        	    .requestMatchers(HttpMethod.GET, "/clientes").hasAnyRole("USER", "ADMIN")
	        	    .requestMatchers(HttpMethod.PUT, "/clientes/**").hasRole("USER")
	        	    .requestMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.PUT, "/clientes/ativar/**").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.PUT, "/clientes/desativar/**").hasRole("ADMIN")

	        	    .requestMatchers(HttpMethod.GET, "/categorias/**").permitAll()
	        	    .requestMatchers(HttpMethod.POST, "/categorias").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.PUT, "/categorias/**").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.DELETE, "/categorias/**").hasRole("ADMIN")

	        	    .requestMatchers(HttpMethod.GET, "/produtos/**").permitAll()
	        	    .requestMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.PUT, "/produtos/**").hasRole("ADMIN")
	        	    .requestMatchers(HttpMethod.DELETE, "/produtos/**").hasRole("ADMIN")

	        	    .requestMatchers(HttpMethod.GET, "/pedidos/**").hasAnyRole("USER", "ADMIN")
	        	    .requestMatchers(HttpMethod.POST, "/pedidos").hasRole("USER")
	        	    .requestMatchers(HttpMethod.PUT, "/pedidos/**").hasRole("USER")
	        	    .requestMatchers(HttpMethod.DELETE, "/pedidos/**").hasRole("USER")

	        	    .requestMatchers(HttpMethod.POST, "/emails").hasRole("ADMIN")

	        	    .requestMatchers(HttpMethod.POST, "/nota-fiscal/pdf").hasAnyRole("USER", "ADMIN")

	        	    .anyRequest().authenticated()
	        )
	        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .headers(headers -> headers.frameOptions().disable());

	    http.addFilterBefore(new JwtAuthenticationFilter(
	            authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil),
	            UsernamePasswordAuthenticationFilter.class);

	    http.addFilterBefore(new JwtAuthorizationFilter(
	            authenticationManager(http.getSharedObject(AuthenticationConfiguration.class)), jwtUtil, userDetailsService),
	            UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:2000"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration.applyPermitDefaultValues());
		return source;
	}

}

