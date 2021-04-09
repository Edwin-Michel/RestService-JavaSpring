package com.edwin.springapi.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "PRODUCTOS")
public class Producto {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "NOMBRE", nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "CATEGORIA", nullable = false, length = 50)
	private String categoria;
	
	@Column(name = "PRECIO", nullable = false, length = 10)
	private Double precio;
	
	@Column(name = "DESCRIPCION", nullable = false, length = 255)
	private String descripcion;
}
