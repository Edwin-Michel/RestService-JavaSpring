package com.edwin.springapi.converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Converter<E, D> {
	
	//Convertir de Entidad a DTO
	public abstract D convertirAdto (E entity);
	
	//Convertir de DTO a Entidad
	public abstract E convertirAentidad (D dto);
	
	//Convertir Entidades a DTOs
	public List<D> convertirAlistDto (List<E> entidades){
		return entidades.stream()
				.map(e -> convertirAdto(e))
		.collect(Collectors.toList());
	}
	
	//Convertir DTOs a Entidades
	public List<E> convertirAlistEntidad (List<D> dtos){
		return dtos.stream()
				.map(dto -> convertirAentidad(dto))
		.collect(Collectors.toList());
	}
	
	
}
