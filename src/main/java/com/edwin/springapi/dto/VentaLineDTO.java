package com.edwin.springapi.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VentaLineDTO {

	private Long id;
	
	private ProductoDTO producto;
	
	private Double precio;
	
	private Long cantidad;
	
	private Double total;

}
