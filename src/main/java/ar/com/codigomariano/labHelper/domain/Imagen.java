package ar.com.codigomariano.labHelper.domain;

import ar.com.codigomariano.labHelper.helpers.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "IMAGENES")
public class Imagen extends Persistible {
	
	public final static String NOMBRE_OBLIGATORIO="El nombre de la imagen es obligatorio.";
	public final static String NOMBRE_MAX_LONGITUD="El nombre de la imagen no debe superar los %d caracteres.";
	public final static String CONTENIDO_OBLIGATORIO="El contenido de la imagen es obligatorio.";
	public final static int NOMBRE_MAX=100;
	public final static String CONTENT_TYPE_POR_DEFECTO="image/png";
	

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
        setNombre(nombre);
        setContenido(contenido);
        setContentType(contentType);
    }

    public Imagen(String nombre, byte[] contenido) {
        this(nombre,CONTENT_TYPE_POR_DEFECTO,contenido);
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
    
	public void setNombre(String nombre) {
		if(ValidationUtils.isEmply(nombre)) {
			throw new IllegalArgumentException(NOMBRE_OBLIGATORIO);
		} else if(ValidationUtils.textoTieneMasCaracteres(nombre, NOMBRE_MAX)) {
			throw new IllegalArgumentException(String.format(NOMBRE_MAX_LONGITUD,NOMBRE_MAX));
		}
		this.nombre = nombre;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public void setContenido(byte[] contenido) {
		if(contenido==null || contenido.length==0) {
			throw new IllegalArgumentException(CONTENIDO_OBLIGATORIO);
		}
		this.contenido = contenido;
	}


}
