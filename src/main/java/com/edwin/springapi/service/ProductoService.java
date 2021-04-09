package com.edwin.springapi.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.edwin.springapi.entity.Producto;
import com.edwin.springapi.excepcions.GeneralException;
import com.edwin.springapi.excepcions.NoDataFoudException;
import com.edwin.springapi.excepcions.ValidateServiceException;
import com.edwin.springapi.repository.ProductoRepository;
import com.edwin.springapi.validator.ProductoValidator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductoService {

	@Autowired
	private ProductoRepository repo;

	// Obtener un producto por id
	public Producto obtenerProducto(Long id) {
		try {
			Producto prod = repo.findById(id)
					.orElseThrow(() -> new NoDataFoudException("El producto no existe"));
			return prod;
		}catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Eliminar un producto
	@Transactional
	public void eliminarProducto(Long id) {
		try {
			Producto prod = repo.findById(id)
					.orElseThrow(() -> new NoDataFoudException("El producto no existe"));
			repo.delete(prod);
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Obtener todos los productos
	public List<Producto> obtenerProductos(Pageable page) {
		try {
			List<Producto> productos = repo.findAll(page).toList();
			return productos;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Registrar un nuevo producto
	@Transactional
	public Producto guadarProducto(Producto prod) {
		try {

			ProductoValidator.validar(prod);
			Producto producto = repo.save(prod);
			return producto;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Actualizar un nuevo producto
	@Transactional
	public Producto actualizarProducto(Producto prod) {
		try {
			Producto producto = repo.findById(prod.getId())
					.orElseThrow(() -> new NoDataFoudException("El producto no existe"));
			producto.setNombre(prod.getNombre());
			producto.setCategoria(prod.getCategoria());
			producto.setPrecio(prod.getPrecio());
			producto.setDescripcion(prod.getDescripcion());
			repo.save(producto);
			return producto;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}
}
