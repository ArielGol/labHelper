package ar.com.codigomariano.labHelper.domain;

public class Imagen extends Persistible {
	
	private String nombre;
	private String contentType;
	private byte[] contenido;
	
	public Imagen(Long id, String nombre, String contentType, byte[] contenido) {
        super(id);
        this.nombre = nombre;
        this.contentType = contentType;
        this.contenido = contenido;
    }

    public Imagen(Long id, String nombre, byte[] contenido) {
        super(id);
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
