package com.edwin.springapi.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import com.edwin.springapi.entity.Usuario;
import com.edwin.springapi.excepcions.NoDataFoudException;
import com.edwin.springapi.repository.UsuarioRepository;
import com.edwin.springapi.service.UsuarioService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TokenAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UsuarioService service;
	
	@Autowired
	private UsuarioRepository repo;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String jwt = getTokenFromRequest(request);
			if (StringUtils.hasText(jwt) && service.validateToken(jwt)) {
				
				//String email = service.getUsernameFromToken(jwt);
				String nombre = service.getUsernameFromToken(jwt);
				Usuario user = repo.findByNombre(nombre)
						.orElseThrow(() -> new NoDataFoudException("El usuario no existe"));
				
				/*Usuario user = repo.findByNombre(username)
						.orElseThrow(() -> new NoDataFoudException("El usuario no existe"));*/
				
				
				UsuarioPrincipal principal = UsuarioPrincipal.create(user);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		} catch (Exception e) {
			log.error("Error al autenticar usuario ", e);
		}
		filterChain.doFilter(request, response);
	}

	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		
		return null;
	}
}
