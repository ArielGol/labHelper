package ar.com.codigomariano.labHelper.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "PASSWORDS")
public class Password extends Persistible {
	
	@Column(name= "VALOR")
	private String valor;
	
	public Password (String contrasenia) {
		setValor(contrasenia);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		//this.valor=encriptar(valor);
		this.valor = valor;
	}

}
