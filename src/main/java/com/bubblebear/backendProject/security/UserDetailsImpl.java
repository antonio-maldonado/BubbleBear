package com.bubblebear.backendProject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bubblebear.backendProject.entity.User;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UserDetailsImpl implements UserDetails {

	private static final long serialVersionUID = 1L;
	private User user;
	
	public UserDetailsImpl( User user) {
		this.user = user;		
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.info("Agregando roles");
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		//authorities.add(  new SimpleGrantedAuthority( "ROLE_"+this.user.getRole() )); // TODO leer ROLE de User		
		//user.getRoles().forEach( role-> authorities.add(  new SimpleGrantedAuthority( "ROLE_"+ role.getRoleName() )) );
		
		authorities.add(  new SimpleGrantedAuthority( "ROLE_CUSTOMER" ));
		
		if( this.user.getRole() == 1 ) {
			authorities.add(  new SimpleGrantedAuthority( "ROLE_ADMIN" ));			
		}
		
		return authorities ;
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {		
		return this.user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public String FullName() {
        return this.user.getFullname();
    }

}