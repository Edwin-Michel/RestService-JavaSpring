package com.edwin.springapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.edwin.springapi.converter.UsuarioConverter;
import com.edwin.springapi.dto.LoginRequestDTO;
import com.edwin.springapi.dto.LoginResponseDTO;
import com.edwin.springapi.dto.SignResquestDTO;
import com.edwin.springapi.dto.UsuarioDTO;
import com.edwin.springapi.entity.Usuario;
import com.edwin.springapi.service.UsuarioService;
import com.edwin.springapi.utils.StandardResponse;

@RestController
public class UsuarioController {

	@Autowired
	private UsuarioService service;
	
	private UsuarioConverter converter = new UsuarioConverter();
	
	// Registrar un nuevo usuario
	@PostMapping(value = "/signup")
	public ResponseEntity<StandardResponse<UsuarioDTO>> signUp (@RequestBody SignResquestDTO request){
		Usuario user = service.createUser(converter.signup(request));
		UsuarioDTO userDTO = converter.convertirAdto(user);		
		StandardResponse<UsuarioDTO> response = new StandardResponse<>(true, "successful", userDTO);
		return new ResponseEntity<StandardResponse<UsuarioDTO>>(response, HttpStatus.CREATED);
	}
	
	// Login
	@PostMapping(value = "/login")
	public ResponseEntity<StandardResponse<LoginResponseDTO>> login (@RequestBody LoginRequestDTO request){
		LoginResponseDTO loginDTO = service.login(request);
		StandardResponse<LoginResponseDTO> response = new StandardResponse<>(true, "successful", loginDTO); 
		return new ResponseEntity<StandardResponse<LoginResponseDTO>>(response, HttpStatus.OK);
	}
	
}
