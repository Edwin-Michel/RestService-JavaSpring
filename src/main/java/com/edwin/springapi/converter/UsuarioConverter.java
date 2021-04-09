package com.edwin.springapi.converter;

import com.edwin.springapi.dto.SignResquestDTO;
import com.edwin.springapi.dto.UsuarioDTO;
import com.edwin.springapi.entity.Usuario;

public class UsuarioConverter extends Converter<Usuario, UsuarioDTO> {

	@Override
	public UsuarioDTO convertirAdto(Usuario entity) {
		if(entity == null) return null;
		return UsuarioDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.apellido_paterno(entity.getApellidoPaterno())
				.apellido_materno(entity.getApellidoMaterno())
				.email(entity.getEmail())
				.build();
	}

	@Override
	public Usuario convertirAentidad(UsuarioDTO dto) {
		if(dto == null) return null;
		return Usuario.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.apellidoPaterno(dto.getApellido_paterno())
				.apellidoMaterno(dto.getApellido_materno())
				.email(dto.getEmail())
				.build();
	}

	public Usuario signup(SignResquestDTO request) {
		return Usuario.builder()
				.nombre(request.getNombre())
				.apellidoPaterno(request.getApellido_paterno())
				.apellidoMaterno(request.getApellido_materno())
				.email(request.getEmail())
				.password(request.getPassword())
				.build();
	}

}
