package com.edwin.springapi.validator;

import com.edwin.springapi.entity.Producto;
import com.edwin.springapi.excepcions.ValidateServiceException;

public class ProductoValidator {

	public static void validar (Producto producto) {
		if(producto.getNombre() == null || producto.getNombre().trim().isEmpty()) {
			throw new ValidateServiceException("Se requiere el nombre del producto");
		}
		if(producto.getNombre().length() > 100) {
			throw new ValidateServiceException("Nombre de producto muy largo");
		}
		if(producto.getPrecio() == null) {
			throw new ValidateServiceException("El campo 'precio' es requerido");
		}
		if(producto.getPrecio() < 0) {
			throw new ValidateServiceException("Precio incorrecto");
		}
		
		if(producto.getCategoria() == null || producto.getCategoria().trim().isEmpty()) {
			throw new ValidateServiceException("Se requiere la categoria del producto");
		}
	}
	
}
