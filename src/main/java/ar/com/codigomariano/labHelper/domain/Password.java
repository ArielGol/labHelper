package ar.com.codigomariano.labHelper.domain;

public class Password extends Persistible {
	
	private String valor;
	
	public Password (Long id,String contrasenia) {
		super(id);
		setValor(contrasenia);
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
