package ar.com.codigomariano.labHelper.domain;

public class Rol extends Persistible {
	
	private String nombre;
	
	public Rol(Long id,String nombre) {
		super(id);
		setNombre(nombre);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
