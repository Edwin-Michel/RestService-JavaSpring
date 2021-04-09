package com.edwin.springapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

	private Long id;
	
	private String nombre;
	
	private String apellido_paterno;
	
	private String apellido_materno;
	
	private String email;
}
