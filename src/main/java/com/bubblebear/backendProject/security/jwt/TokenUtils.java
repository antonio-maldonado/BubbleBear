package com.bubblebear.backendProject.security.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class TokenUtils {
	
	private final static String ACCESS_TOKEN_SECRET = "miClaveSecreta123miClaveSecreta123";
	private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 8_886_400L;
	
	public static String createToken(
			String id,
			String name, 
			String email,  
			Collection<? extends GrantedAuthority> authorities ) {
		
		long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1000;
		Date expirationDate = new Date( System.currentTimeMillis() + expirationTime );
		
		Map< String, Object> extra = new HashMap<>();
		extra.put("name", name );
		extra.put("authorities", authorities );
		
		return  Jwts
					.builder()
					.setId(id)
					.setSubject(email)
					.setExpiration(expirationDate)
					.addClaims(extra)
					.signWith( Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes() ) )
					.compact();
	}
	
	//Validar el token recibido por el cliente
	public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
		try {
		Claims claims = Jwts
				.parserBuilder()
				.setSigningKey(ACCESS_TOKEN_SECRET.getBytes() )
				.build()
				.parseClaimsJws(token)
				.getBody();
		
		String email = claims.getSubject();
		
		
		List<Map<String,String>> authoritiesLists = (List<Map<String, String>>) claims.get("authorities");
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		if(authoritiesLists!=null) {
			for(Map<String,String> authorityMap : authoritiesLists) {
				String authority = authorityMap.get("authority");
				authorities.add(new SimpleGrantedAuthority(authority));
				
			}
		}
		
		UsernamePasswordAuthenticationToken userAuth = 
				new UsernamePasswordAuthenticationToken(email,null,authorities);
		return userAuth;
		}catch(JwtException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}