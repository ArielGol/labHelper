package ar.com.codigomariano.labHelper.domain;

import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.TipoCliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "CLIENTES")
public class Cliente extends Persistible {
    
	@Column(name= "NOMBRE_EMPRESA")
    private String nombreEmpresa;
	
	@Column(name= "TIPO_DE_CLIENTE")
	@Enumerated(EnumType.STRING)
    private TipoCliente tipo;
    
	
	@OneToOne(cascade= CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "USUARIO_ID")
    private Usuario cliente;
	
	@OneToMany
	@JoinColumn(name = "CLIENTE_ID", referencedColumnName = "ID", nullable = false)
	private List<Muestra> muestras;
	
	// Sólo para Hibernate
	Cliente(){
		
	}

    public Cliente(String nombreEmpresa, TipoCliente tipo, Usuario cliente) {
        setNombreEmpresa(nombreEmpresa);
        this.tipo = tipo;
        setCliente(cliente);
        this.muestras= new ArrayList<Muestra>();
    }
    
    public boolean perteneceUsuario(Usuario usuarioLogueado) {
    	return this.cliente !=null && this.cliente.getId().equals(usuarioLogueado.getId());
    }
    
    public void asignarMuestra(Muestra muestra) {
        this.muestras.add(muestra);
    }

    public void eliminarMuestra(String codigoMuestra) {
        Muestra aEliminar = null;
        int index = 0;
        while (index < this.muestras.size()) {
            Muestra m = this.muestras.get(index);
            if (m.getCodigoMuestra().equalsIgnoreCase(codigoMuestra)) aEliminar = m;
            index++;
        }
        if (aEliminar != null) {
            this.muestras.remove(aEliminar);
            System.out.println("Muestra: " + codigoMuestra + " eliminada.");
        } else {
            System.out.println("No se encontró la muestra: " + codigoMuestra);
        }
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
	public List<Muestra> getMuestras() {
        return muestras;
    }



}