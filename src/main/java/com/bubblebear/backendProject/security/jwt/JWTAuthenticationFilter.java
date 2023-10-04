package com.bubblebear.backendProject.security.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bubblebear.backendProject.security.UserDetailsImpl;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;

@Log4j2
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter  {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		AuthCredentials authCredentials = new AuthCredentials();
		
		try {
			// Asumimos que el body de la petición vendrá en el formato JSON: { "email": "pato@gmail.com", "password": "123"
			// Realizamos un mapeo a nuestra clase AuthCredentials
			authCredentials = new ObjectMapper().readValue( request.getReader(), AuthCredentials.class );
			log.info( authCredentials.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (StreamReadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DatabindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
				authCredentials.getEmail(),
				authCredentials.getPassword()
				);
		// Autenticamos el usuario con authManager
		return getAuthenticationManager().authenticate(usernamePAT);
	}
	
	// Si la autenticación fue correcta agregamos el token a la respuesta, en la parte de header
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException, java.io.IOException {
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authResult.getPrincipal();
		String token = TokenUtils.createToken( 
				userDetails.FullName(), 
				userDetails.getUsername(),
				userDetails.getAuthorities()
				);
		
		// Crear un objeto JSON para la respuesta
		JSONObject jsonResponse = new JSONObject();
		jsonResponse.put("token", token);

		// Configurar la respuesta HTTP
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");		
		//response.addHeader("Authorization", "Bearer " + token);
		// Establecer el cuerpo de la respuesta como el objeto JSON
		response.getWriter().write(jsonResponse.toString());	
		response.getWriter().close();
		
		super.successfulAuthentication(request, response, chain, authResult);
	}
	
	
}