package ar.com.codigomariano.labHelper.domain;

import ar.com.codigomariano.labHelper.enums.TipoRol;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table(name= "ROLES")
public class Rol extends Persistible {
	
	@Column(name= "NOMBRE")
	@Enumerated(EnumType.STRING)
	private TipoRol nombre;
	
	//Sólo para Hibernate
	Rol(){
		
	}
	
	public Rol(TipoRol nombre) {
		setNombre(nombre);
	}

	public TipoRol getNombre() {
		return nombre;
	}

	public void setNombre(TipoRol nombre) {
		this.nombre = nombre;
	}
	
	
	
}
