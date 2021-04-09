package com.edwin.springapi.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.edwin.springapi.converter.ProductoConverter;
import com.edwin.springapi.dto.ProductoDTO;
import com.edwin.springapi.entity.Producto;
import com.edwin.springapi.service.ProductoService;
import com.edwin.springapi.utils.StandardResponse;

@RestController
public class ProductoController {
	
	@Autowired
	private ProductoService service;
	
	private ProductoConverter converter = new ProductoConverter();
	
	//Obtener un producto por id
	@GetMapping(value="/products/{id}")
	public ResponseEntity<StandardResponse<ProductoDTO>> getProduct (@PathVariable("id") Long id) {
		Producto prod = service.obtenerProducto(id);
		ProductoDTO proDTO = converter.convertirAdto(prod);
		StandardResponse<ProductoDTO> response = new StandardResponse<>(true, "successful", proDTO);
	 	return new ResponseEntity<StandardResponse<ProductoDTO>>(response, HttpStatus.OK);
	}
	
	//Eliminar un producto
	@DeleteMapping(value="/products/{id}")
	public ResponseEntity<StandardResponse<?>> updateProduct (@PathVariable("id") Long id) {
		service.eliminarProducto(id);
		StandardResponse<?> response = new StandardResponse<>(true, "Producto eliminado", null);
	return new ResponseEntity<StandardResponse<?>>(response, HttpStatus.OK);
	}
	
	//Obtener todos los productos
	@GetMapping(value="/products")
	public ResponseEntity<StandardResponse<List<ProductoDTO>>> getProducts (
			@RequestParam (value="pageNumber", required=false, defaultValue = "0") int pageNumber,
			@RequestParam (value="pageSize", required=false, defaultValue = "20") int pageSize ){
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Producto> productos = service.obtenerProductos(page);
		List<ProductoDTO> productosDto = converter.convertirAlistDto(productos);
		StandardResponse<List<ProductoDTO>> response = new StandardResponse<>(true, "successful", productosDto);
		return new ResponseEntity<StandardResponse<List<ProductoDTO>>>(response, HttpStatus.OK);
	}
	
	//Registrar un nuevo producto
	@PostMapping(value="/products")
	public ResponseEntity<StandardResponse<ProductoDTO>> saveProduct (@RequestBody ProductoDTO dto) {
		Producto prod = service.guadarProducto(converter.convertirAentidad(dto));
		ProductoDTO proDTO = converter.convertirAdto(prod);		
		StandardResponse<ProductoDTO> response = new StandardResponse<>(true, "successful", proDTO);
		return new ResponseEntity<StandardResponse<ProductoDTO>>(response, HttpStatus.CREATED);
	}
	
	//Actualizar un nuevo producto
	@PutMapping(value="/products")
	public ResponseEntity<StandardResponse<ProductoDTO>> updateProduct (@RequestBody ProductoDTO dto) {
		Producto prod = service.actualizarProducto(converter.convertirAentidad(dto));
		ProductoDTO proDTO = converter.convertirAdto(prod);
		StandardResponse<ProductoDTO> response = new StandardResponse<>(true, "successful", proDTO);
	return new ResponseEntity<StandardResponse<ProductoDTO>>(response, HttpStatus.OK);
	}
}
