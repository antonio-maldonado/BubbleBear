package com.bubblebear.backendProject.security;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.bubblebear.backendProject.security.jwt.JWTAuthenticationFilter;
import com.bubblebear.backendProject.security.jwt.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JWTAuthorizationFilter jwtAuthorizationFilter;
	
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http, AuthenticationManager authManager ) throws Exception {
		
		JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter();
		jwtAuthenticationFilter.setAuthenticationManager( authManager );
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		
		http.authorizeHttpRequests( authorize -> authorize
				.requestMatchers(HttpMethod.DELETE, "/api/**" ).hasRole("ADMIN")
				.requestMatchers(HttpMethod.PUT, "/api/**" ).hasRole("CUSTOMER")
				.requestMatchers(HttpMethod.POST, "/api/**" ).hasRole("CUSTOMER")
				.requestMatchers(HttpMethod.GET, "/api/orders/**" ).hasRole("CUSTOMER")
				.requestMatchers(HttpMethod.GET, "/api/products/**" ).permitAll()
				.requestMatchers( "/**" ).permitAll()
				.anyRequest().authenticated() )
		.formLogin(Customizer.withDefaults())
			.addFilter(jwtAuthenticationFilter)
			 .addFilterBefore(  jwtAuthorizationFilter , UsernamePasswordAuthenticationFilter.class ) 
			.sessionManagement(management->management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			 .csrf(csrf -> csrf.disable()) 
			.httpBasic( Customizer.withDefaults() ); 
		return http.build();
	}
	

//	 @Bean
//    CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedOrigin("http://127.0.0.1:5500"); // Reemplaza con tu origen permitido
//        config.addAllowedMethod("*");
//        config.addAllowedHeader("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
//
//@Bean
//CorsConfigurationSource corsConfigurationSource() {
//	CorsConfiguration configuration = new CorsConfiguration();
//	//TODO: Poner el link del deploy en lugar de 127.0.0.1:5500
//	configuration.setAllowedOrigins( List.of("http://127.0.0.1:5500") ); 
//	configuration.setAllowedMethods( List.of("GET", "POST", "PUT", "DELETE") );
//	configuration.setAllowedHeaders( List.of("Authorization","Content-Type","Access-Control-Allow-Origin") );
//	UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	source.registerCorsConfiguration("/**", configuration);
//	return source;
//	
//}
	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//	    CorsConfiguration configuration = new CorsConfiguration();
//	    configuration.setAllowedOrigins(Arrays.asList("*"));
//	    configuration.setAllowedMethods(Arrays.asList("*"));
//	    configuration.setAllowedHeaders(Arrays.asList("*"));
//	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//	    source.registerCorsConfiguration("/**", configuration);
//	    return source;
//	}
	
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
}