package com.edwin.springapi.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="VENTAS")
public class Venta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Long id;
	
	@Column(name="DATE", nullable = false, updatable = false)
	private LocalDateTime date;
	
	@OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<VentaLine> ventas;
	
	@Column(name="TOTAL", nullable = false)
	private Double total;
	
	@ManyToOne
	@JoinColumn (name = "FK_USER", updatable = false)
	private Usuario usuario;
	

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
		Venta other = (Venta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
