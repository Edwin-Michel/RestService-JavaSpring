package com.edwin.springapi.validator;

import com.edwin.springapi.entity.Usuario;
import com.edwin.springapi.excepcions.ValidateServiceException;

public class UsuarioValidator {

	public static void signup (Usuario user) {
		if(user.getNombre() == null || user.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre de usuario es requerido");
		}
		if(user.getNombre().length() > 30) {
			throw new ValidateServiceException("El nombre de usuario es muy largo (max. 30)");
		}
		if(user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new ValidateServiceException("El password es requerido");
		}
		if(user.getPassword().length() > 30) {
			throw new ValidateServiceException("El password es my largo (max. 30)");
		}
		if(user.getPassword().length() < 8) {
			throw new ValidateServiceException("El password es my corto (min. 8)");
		}
		if(user.getApellidoPaterno() == null || user.getApellidoPaterno().trim().isEmpty()) {
			throw new ValidateServiceException("El apellido paterno es requerido");
		}
		if(user.getApellidoMaterno() == null || user.getApellidoMaterno().trim().isEmpty()) {
			throw new ValidateServiceException("El apellido materno es requerido");
		}
		
		if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
			throw new ValidateServiceException("El email es requerido");
		}
		if (user.getEmail().length() > 20) {
			throw new ValidateServiceException("El email es muy largo");
		}
		if (user.getEmail().length() < 10) {
			throw new ValidateServiceException("El email es invalido");
		}
		
	}
	
}
