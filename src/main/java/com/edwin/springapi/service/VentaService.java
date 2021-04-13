package com.edwin.springapi.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.edwin.springapi.entity.Producto;
import com.edwin.springapi.entity.Usuario;
import com.edwin.springapi.entity.Venta;
import com.edwin.springapi.entity.VentaLine;
import com.edwin.springapi.excepcions.*;
import com.edwin.springapi.repository.ProductoRepository;
import com.edwin.springapi.repository.VentaLineRepository;
import com.edwin.springapi.repository.VentaRepository;
import com.edwin.springapi.security.UsuarioPrincipal;
import com.edwin.springapi.validator.VentaValidator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VentaService {

	@Autowired
	private VentaRepository repo;
	
	@Autowired
	private VentaLineRepository ventaRepo;
	
	@Autowired
	private ProductoRepository prodRepo;

	// Obtener Venta por id
	public Venta obtenerVenta(Long id) {
		try {
			Venta venta = repo.findById(id)
					.orElseThrow(() -> new NoDataFoudException("La venta no existe"));
			return venta;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Eliminar venta TODO: No se pudo eliminar la venta
	@Transactional
	public void eliminarVenta(Long id) {
		try {
			Venta venta = repo.findById(id).
					orElseThrow(() -> new NoDataFoudException("La venta no existe"));
			repo.delete(venta);
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

	// Obtener todas las ventas
	public List<Venta> obtenerVentas(Pageable page) {
		try {
			List<Venta> ventas = repo.findAll(page).toList();
			return ventas;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
	//Registrar una venta
	@Transactional
	public Venta guardarVenta (Venta venta) {
		try {
			Double total = 0.0;
			VentaValidator.validar(venta);
/****************TODO: Obtener el usuario al registrar una venta***********************/		
			UsuarioPrincipal principal = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario user = principal.getUser();
		
			for (VentaLine line: venta.getVentas()) {
				Producto producto = prodRepo.findById(line.getProducto().getId())
				.orElseThrow(() -> new NoDataFoudException("No existe el producto " + line.getProducto().getId()));	
				line.setPrecio(producto.getPrecio());
				line.setProducto(producto);
				line.setTotal(producto.getPrecio() * line.getCantidad());
				total += line.getTotal();
			}
			venta.setTotal(total);
			venta.getVentas().forEach(line -> line.setVenta(venta));
/****************TODO: Obtener el usuario al registrar una venta***********************/
			venta.setUsuario(user);
			venta.setDate(LocalDateTime.now());
			Venta newVenta = repo.save(venta);
			return newVenta;
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}
	
	// Actualizar una venta
	@Transactional
	public Venta actualizarVenta (Venta venta) {
		try {
			Double total = 0.0;
			VentaValidator.validar(venta);
/****************TODO: Obtener el usuario al registrar una venta***********************/		
			UsuarioPrincipal principal = (UsuarioPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			Usuario user = principal.getUser();
			
			for (VentaLine line: venta.getVentas()) {
				Producto producto = prodRepo.findById(line.getProducto().getId())
				.orElseThrow(() -> new NoDataFoudException("No existe el producto " + line.getProducto().getId()));	
				line.setPrecio(producto.getPrecio());
				line.setTotal(producto.getPrecio() * line.getCantidad());
				total += line.getTotal();
			}
			venta.setTotal(total);
			
			venta.getVentas().forEach(line -> line.setVenta(venta));
			Venta order = repo.findById(venta.getId())
					.orElseThrow(()-> new NoDataFoudException("La venta no existe"));
			
/****************TODO: Obtener el usuario al registrar una venta***********************/
			venta.setUsuario(user);
			venta.setDate(order.getDate());
			
			List<VentaLine> deletedLines = order.getVentas();
			deletedLines.removeAll(venta.getVentas());
			ventaRepo.deleteAll(deletedLines);
			
			return repo.save(venta);
		} catch (ValidateServiceException | NoDataFoudException e) {
			log.info(e.getMessage().toString(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralException(e.getMessage(), e);
		}
	}

}
