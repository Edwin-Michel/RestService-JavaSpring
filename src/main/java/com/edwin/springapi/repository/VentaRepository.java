package com.edwin.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.edwin.springapi.entity.Venta;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

}
