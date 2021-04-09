package com.edwin.springapi.validator;

import com.edwin.springapi.entity.Venta;
import com.edwin.springapi.entity.VentaLine;
import com.edwin.springapi.excepcions.ValidateServiceException;

public class VentaValidator {
	
	public static void validar (Venta venta) {
		
		if (venta.getVentas() ==null || venta.getVentas().isEmpty()) {
			throw new ValidateServiceException("Las lineas son requeridas");
		}
		
		for(VentaLine line : venta.getVentas()) {
			
			if(line.getProducto() == null || line.getProducto().getId() == null) {
				throw new ValidateServiceException("El producto es incorrecto");
			}
			if(line.getCantidad() == null) {
				throw new ValidateServiceException("La cantidad es requerida");
			}
			if(line.getCantidad() < 0) {
				throw new ValidateServiceException("La cantidad es incorrecta");
			}
			
		}
	}
}
