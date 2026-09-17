package ar.com.codigomariano.labHelper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="NOTAS_TEXTO")
public class NotaTexto extends Persistible {
	
	@Column(name= "CONTENIDO")
	private String contenido;
	
	//Sólo para Hibernate
	NotaTexto(){
		
	}
	
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
