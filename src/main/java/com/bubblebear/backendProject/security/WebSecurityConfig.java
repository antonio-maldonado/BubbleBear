package com.bubblebear.backendProject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.bubblebear.backendProject.security.jwt.JWTAuthenticationFilter;
import com.bubblebear.backendProject.security.jwt.JWTAuthorizationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JWTAuthorizationFilter jwtAuthorizationFilter;
	
	// STEP 1 Deshabilitar la seguridad en filter chain	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager ) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager( authManager );
		//jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		
		http.authorizeHttpRequests( authorize -> authorize
				// STEP 2.1 configurar las reglas de autorización para las solicitudes HTTP
				.requestMatchers(  "/api/products" ).permitAll()
				.requestMatchers(  "/api/products/**" ).permitAll()
				.requestMatchers(  "/api/user/**" ).permitAll()
				.requestMatchers(  "/api/orders/**" ).permitAll()
				.requestMatchers(  "/api/ordershasproducts/**" ).permitAll()
				.requestMatchers(  "/api/categories/**" ).permitAll()
				.requestMatchers(  "/api/categories" ).permitAll()
				//.requestMatchers( HttpMethod.POST, "/api/products" ).permitAll()
				.requestMatchers( "/api/orders" ).permitAll()
				.requestMatchers( "/api/user" ).permitAll()
				.requestMatchers( "/**" ).permitAll()
				.requestMatchers( "api/ordershasproducts" ).permitAll()
				//.requestMatchers( HttpMethod.POST, "/api/v2/users" ).permitAll()
				//.requestMatchers( "/api/orders/**" ).hasRole("ADMIN")
				//.requestMatchers( "/api/v2/users/**" ).hasRole("ADMIN")	
				.anyRequest().authenticated() )
			.addFilter(jwtAuthenticationFilter)
			 .addFilterBefore(  jwtAuthorizationFilter , UsernamePasswordAuthenticationFilter.class ) //TODO verificar token
			.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .csrf(csrf -> csrf.disable()) // deshabilitando lka protección Cross-Site Request Forgery
			.httpBasic( withDefaults() ); // habilitando la autenticación básica http
		return http.build();
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		//TODO: Poner el link del deploy en lugar de 127.0.0.1:5500
		configuration.setAllowedOrigins( List.of("http://127.0.0.1:5500") ); 
		configuration.setAllowedOrigins( List.of("https://admin-kqcu.onrender.com/") ); 
		configuration.setAllowedMethods( List.of("GET", "POST", "PUT", "DELETE") );
		configuration.setAllowedHeaders( List.of("Authorization","Content-Type") );
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
		
	}
		
	
	// STEP 2 Autenticación basada en usuarios en memoria
	/*
	@Bean
	UserDetailsService userDetailsService() {
		
		UserDetails edwin = User.builder()
				.username("edwin@ninja.com")
				.password( passwordEncoder().encode("123")   )
				.roles("ADMIN")
				.build();
				
		UserDetails lalo = User.builder()
				.username("eduardo")
				//.password("{noop}456")
				.password( passwordEncoder().encode("456")   )
				.roles("CUSTOMER", "SAYAJIN") // "ROLE_CUSTOMER"
				.build();
		
		return new InMemoryUserDetailsManager( edwin, lalo)  ;
	}*/
	
	// STEP 3 Leer usuarios de la DB
	@Bean
	AuthenticationManager authManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
		
		AuthenticationManagerBuilder authManagerBuilder = http
				.getSharedObject( AuthenticationManagerBuilder.class);
		
		authManagerBuilder
			.userDetailsService( userDetailsService ) 
			.passwordEncoder( passwordEncoder );
		
		return authManagerBuilder.build();
	}
	
	
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	public static void main(String[] args) {
//		System.out.println("Password: " +  new BCryptPasswordEncoder().encode("123") );
//	}
	
	

}