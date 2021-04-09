package com.edwin.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edwin.springapi.entity.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
	
}
