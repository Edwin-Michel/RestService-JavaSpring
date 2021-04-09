package com.edwin.springapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResquestDTO {
	
	private String nombre;
	
	private String apellido_paterno;
	
	private String apellido_materno;
	
	private String email;
	
	private String password;

}
