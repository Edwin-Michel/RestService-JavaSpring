package com.edwin.springapi.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.edwin.springapi.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UsuarioPrincipal implements UserDetails {
	
	private static final long serialVersionUID = 1L;

	private Usuario user;
	private Collection<? extends GrantedAuthority> authorities;
	
	// Metodo util para no generar una instancia
	public static UsuarioPrincipal create (Usuario usuario) {
		List<GrantedAuthority> authorities = Collections
				.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		return new UsuarioPrincipal(usuario, authorities);
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
