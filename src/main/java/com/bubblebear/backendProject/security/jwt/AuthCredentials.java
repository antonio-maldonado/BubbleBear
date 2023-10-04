package com.bubblebear.backendProject.security.jwt;

import lombok.Data;

@Data
public class AuthCredentials {
	public String email;
	public String password;
	
}
