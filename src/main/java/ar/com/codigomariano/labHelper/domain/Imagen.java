package ar.com.codigomariano.labHelper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "IMAGENES")
public class Imagen extends Persistible {
	
	@Column(name= "NOMBRE")
	private String nombre;
	
	@Column(name= "CONTENT_TYPE")
	private String contentType;
	
	@Column(name= "CONTENIDO")
	private byte[] contenido;
	
	//Sólo para Hibernate
	Imagen(){
		
	}
	
	public Imagen(String nombre, String contentType, byte[] contenido) {
        this.nombre = nombre;
        this.contentType = contentType;
        this.contenido = contenido;
    }

    public Imagen(String nombre, byte[] contenido) {
        this.nombre = nombre;
        this.contentType = "image/png"; 
        this.contenido = contenido;
    }

    public String getNombre() { 
    	return nombre; 
    	}
    public String getContentType() { 
    	return contentType;
    	}
    public byte[] getContenido() {
    	return contenido; 
    	}


}
