package com.edwin.springapi.dto;

import java.util.List;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {

	private Long id;
	
	private String date_time;
	
	private List<VentaLineDTO> ventas;
	
	private Double total;
	
	private UsuarioDTO user;
}
