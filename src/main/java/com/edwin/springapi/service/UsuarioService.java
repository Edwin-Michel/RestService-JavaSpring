package com.edwin.springapi.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.edwin.springapi.converter.UsuarioConverter;
import com.edwin.springapi.dto.LoginRequestDTO;
import com.edwin.springapi.dto.LoginResponseDTO;
import com.edwin.springapi.entity.Usuario;
import com.edwin.springapi.excepcions.*;
import com.edwin.springapi.repository.UsuarioRepository;
import com.edwin.springapi.validator.UsuarioValidator;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsuarioService {

	private String jwtSecret = "jwt998e55089n#strt!entry-dest?mqXX12UtRw67_2334";
	
	@Autowired
	private UsuarioRepository repo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private UsuarioConverter converter = new UsuarioConverter();
	
	// Crear un nuevo usuario
	public Usuario createUser (Usuario usuario) {
		try {
			UsuarioValidator.signup(usuario);
			Usuario user = repo.findByEmail(usuario.getEmail())
			//Usuario user = repo.findByNombre(usuario.getNombre())
					.orElse(null);
			if (user != null) throw new ValidateServiceException("Usuario invalido");
			
			//Encriptar el password
			String passwordEncriptado = encoder.encode(usuario.getPassword());
			usuario.setPassword(passwordEncriptado);
			
			return repo.save(usuario);
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
	// Login
	public LoginResponseDTO login (LoginRequestDTO request) {
		try {
			Usuario usuario = repo.findByEmail(request.getEmail())
			//Usuario usuario = repo.findByNombre(request.getNombre())
					.orElseThrow(() -> new ValidateServiceException("Email o password incorrecto"));
			
			//Comprobar si el password encriptado y el sin encriptar coinciden
			if (! encoder.matches(request.getPassword(), usuario.getPassword())) throw new ValidateServiceException("Password incorrecto");
			//if (! usuario.getPassword().equals(request.getPassword())) throw new ValidateServiceException("Password incorrecto");
				
			String token = createToken(usuario);
			return new LoginResponseDTO(converter.convertirAdto(usuario), token);
			
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
	public String createToken (Usuario user) {
		Date now = new Date();
		Date expired = new Date(now.getTime() + (1000*60*60*24*(30*2)));
		
		return Jwts.builder()
				//.setSubject(user.getEmail())
				.setSubject(user.getNombre())
				.setIssuedAt(now)
				.setExpiration(expired)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}
	
	public Boolean validateToken  (String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("Formato JWT no coincide con el formato esperado por la aplicacion");
		} catch (MalformedJwtException e) {
			log.error("JWT rechazado, se construyó de manera incorrecta.");
		} catch (SignatureException e) {
			log.error("Error al calcular una firma o al verificar una firma existente de un JWT.");
		} catch (ExpiredJwtException e) {
			log.error("JWT vencido y rechazado.");
		} catch (IllegalArgumentException e) {
			log.error("Método ilegal o argumento inapropiado.");
		}
		return false;
	}

	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("Token invalido");
		}
		
	}
}
