package ar.com.codigomariano.labHelper.domain;

import ar.com.codigomariano.labHelper.helpers.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name= "PASSWORDS")
public class Password extends Persistible {
	
	public final static String VALOR_OBLIGATORIO="El valor de la contraseña es obligatorio.";
	
	@Column(name= "VALOR")
	private String valor;
	
	//Sólo para Hibernate
	Password(){
		
	}
	
	public Password (String contrasenia) {
		setValor(contrasenia);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		if(ValidationUtils.isEmply(valor)) {
			throw new IllegalArgumentException(VALOR_OBLIGATORIO);
		}
		this.valor = valor;
	}

}
