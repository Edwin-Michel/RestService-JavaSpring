package com.edwin.springapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edwin.springapi.converter.VentaConverter;
import com.edwin.springapi.dto.VentaDTO;
import com.edwin.springapi.entity.Venta;
import com.edwin.springapi.service.VentaService;
import com.edwin.springapi.utils.StandardResponse;

@RestController
public class VentaController {

	@Autowired
	private VentaService service ;
	
	private VentaConverter converter = new VentaConverter();
	
	// Obtener un venta por id
	@GetMapping(value = "/orders-auth/{id}")
	public ResponseEntity<StandardResponse<VentaDTO>> getVenta(@PathVariable("id") Long id) {
		Venta venta = service.obtenerVenta(id);
		VentaDTO ventaDTO = converter.convertirAdto(venta);
		StandardResponse<VentaDTO> response = new StandardResponse<>(true, "successful", ventaDTO);
		return new ResponseEntity<StandardResponse<VentaDTO>>(response, HttpStatus.OK);
	}

	// Eliminar una venta
	@DeleteMapping(value = "/oreders-auth/{id}")
	public ResponseEntity<StandardResponse<?>> deleteVenta(@PathVariable("id") Long id) {
		service.eliminarVenta(id);
		StandardResponse<?> response = new StandardResponse<>(true, "Venta eliminada", null);
		return new ResponseEntity<StandardResponse<?>>(response, HttpStatus.OK);
	}

	// Obtener todas las ventas
	@GetMapping(value = "/orders-auth")
	public ResponseEntity<StandardResponse<List<VentaDTO>>> getVentas (
			@RequestParam (value="pageNumber", required=false, defaultValue = "0") int pageNumber,
			@RequestParam (value="pageSize", required=false, defaultValue = "20") int pageSize ){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Venta> venta = service.obtenerVentas(page);
		List<VentaDTO> ventasDTO = converter.convertirAlistDto(venta);
		StandardResponse<List<VentaDTO>> response = new StandardResponse<>(true, "successful", ventasDTO);
		return new ResponseEntity<StandardResponse<List<VentaDTO>>>(response, HttpStatus.OK);
	}

	// Registrar ua nueva venta
	@PostMapping(value = "/orders-auth")
	public ResponseEntity<StandardResponse<VentaDTO>> saveVenta(@RequestBody VentaDTO dto) {
		Venta venta = service.guardarVenta(converter.convertirAentidad(dto));
		VentaDTO ventaDTO = converter.convertirAdto(venta);
		StandardResponse<VentaDTO> response = new StandardResponse<>(true, "successful", ventaDTO);
		return new ResponseEntity<StandardResponse<VentaDTO>>(response, HttpStatus.CREATED);
	}

	// Actualizar una venta
	@PutMapping(value = "/orders-auth")
	public ResponseEntity<StandardResponse<VentaDTO>> updateVenta(@RequestBody VentaDTO dto) {
		Venta venta = service.actualizarVenta(converter.convertirAentidad(dto));
		VentaDTO ventaDTO = converter.convertirAdto(venta);
		StandardResponse<VentaDTO> response = new StandardResponse<>(true, "successful", ventaDTO);
		return new ResponseEntity<StandardResponse<VentaDTO>>(response, HttpStatus.OK);
	}
}
