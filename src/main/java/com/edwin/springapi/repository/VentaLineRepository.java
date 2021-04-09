package com.edwin.springapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edwin.springapi.entity.VentaLine;

@Repository
public interface VentaLineRepository extends JpaRepository<VentaLine, Long> {

}
