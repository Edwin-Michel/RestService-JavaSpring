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
@Table(name="USUARIOS")
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NOMBRE", nullable = false, length = 30)
	private String nombre;
	
	@Column(name = "APELLIDO_PATERNO", nullable = false, length = 30)
	private String apellidoPaterno;

	@Column(name = "APELLIDO_MATERNO", nullable = false, length = 30)
	private String apellidoMaterno;
	
	@Column(name = "EMAIL", nullable = false, length = 30)
	private String email;
	
	@Column(name = "PASSWORD", nullable = false, length = 150)
	private String password;
	

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
		Usuario other = (Usuario) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
