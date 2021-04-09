package com.edwin.springapi.converter;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import com.edwin.springapi.dto.VentaDTO;
import com.edwin.springapi.dto.VentaLineDTO;
import com.edwin.springapi.entity.Venta;
import com.edwin.springapi.entity.VentaLine;

public class VentaConverter extends Converter<Venta, VentaDTO>{

	private static final DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	
	private ProductoConverter prodConverter = new ProductoConverter();
	
	private UsuarioConverter userConverter = new UsuarioConverter();
	
	
	@Override
	public VentaDTO convertirAdto(Venta entity) {
		if (entity == null) return null;
		List<VentaLineDTO> lines = aDto (entity.getVentas()); 
		return VentaDTO.builder()
				.id(entity.getId())
				.date_time(entity.getDate().format(date))
				.ventas(lines)
				.total(entity.getTotal())
				.user(userConverter.convertirAdto(entity.getUsuario()))
				.build();
	}

	@Override
	public Venta convertirAentidad(VentaDTO dto) {
		if (dto == null) return null;
		List<VentaLine> lines = aEntidad (dto.getVentas()); 
		return Venta.builder()
				.id(dto.getId())
				.ventas(lines)
				.total(dto.getTotal())
				.usuario(userConverter.convertirAentidad(dto.getUser()))
				.build();
	}
	
	private List<VentaLineDTO>  aDto (List<VentaLine> lines){
		if (lines == null) return null;
		return lines.stream().map(line ->{
			return VentaLineDTO.builder()
					.id(line.getId())
					.producto(prodConverter.convertirAdto(line.getProducto()))
					.precio(line.getPrecio())
					.cantidad(line.getCantidad())
					.total(line.getTotal())
					.build();
		}).collect(Collectors.toList());
	}
	
	private List<VentaLine>  aEntidad (List<VentaLineDTO> lines){
		if (lines == null) return null;
		return lines.stream().map(line ->{
			return VentaLine.builder()
					.id(line.getId())
					.producto(prodConverter.convertirAentidad(line.getProducto()))
					.precio(line.getPrecio())
					.cantidad(line.getCantidad())
					.total(line.getTotal())
					.build();
		}).collect(Collectors.toList());
	}
	
}
