package com.edwin.springapi.entity;

import javax.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name="VENTAS_LINE")
public class VentaLine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="FK_VENTA", nullable = false)
	private Venta venta;
	
	@ManyToOne
	@JoinColumn(name="FK_PRODUCTO", nullable = false)
	private Producto producto;
	
	@Column(name="PRECIO", nullable = false, length = 10)
	private Double precio;
	
	@Column(name="CANTIDAD", nullable = false)
	private Long cantidad;
	
	@Column(name="TOTAL", nullable = false)
	private Double total;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VentaLine other = (VentaLine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
