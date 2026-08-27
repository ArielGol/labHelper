package ar.com.codigomariano.labHelper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "ROLES")
public class Rol extends Persistible {
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	public Rol(String nombre) {
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
