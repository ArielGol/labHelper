package ar.com.codigomariano.labHelper.domain;

import ar.com.codigomariano.labHelper.enums.TipoCliente;

public class Cliente extends Persistible {
    
    private String nombreEmpresa;
    private TipoCliente tipo;
    private Usuario cliente;

    public Cliente(String nombreEmpresa, TipoCliente tipo, Usuario cliente) {
        setNombreEmpresa(nombreEmpresa);
        this.tipo = tipo;
        setCliente(cliente);
    }
    
    public boolean perteneceUsuario(Usuario usuarioLogueado) {
    	return this.cliente !=null && this.cliente.getId().equals(usuarioLogueado.getId());
    }

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}

	public TipoCliente getTipo() {
		return tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}



}