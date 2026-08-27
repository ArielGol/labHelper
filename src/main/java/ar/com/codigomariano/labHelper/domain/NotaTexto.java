package ar.com.codigomariano.labHelper.domain;

public class NotaTexto extends Persistible {
	
	private String contenido;
	
	public NotaTexto(String texto) {
		this.contenido=texto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	

}
