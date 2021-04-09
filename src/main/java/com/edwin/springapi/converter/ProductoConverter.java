package com.edwin.springapi.converter;

import com.edwin.springapi.dto.ProductoDTO;
import com.edwin.springapi.entity.Producto;

public class ProductoConverter extends Converter<Producto, ProductoDTO> {

	@Override
	public ProductoDTO convertirAdto (Producto entity) {
		if(entity == null) return null;
		return ProductoDTO.builder()
				.id(entity.getId())
				.nombre(entity.getNombre())
				.categoria(entity.getCategoria())
				.precio(entity.getPrecio())
				.descripcion(entity.getDescripcion())
				.build();
	}

	@Override
	public Producto convertirAentidad (ProductoDTO dto) {
		if(dto == null) return null;
		return Producto.builder()
				.id(dto.getId())
				.nombre(dto.getNombre())
				.categoria(dto.getCategoria())
				.precio(dto.getPrecio())
				.descripcion(dto.getDescripcion())
				.build();
	}

}
