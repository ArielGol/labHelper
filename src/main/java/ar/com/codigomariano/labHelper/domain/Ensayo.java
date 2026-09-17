package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.Estado;
import ar.com.codigomariano.labHelper.enums.TipoEnsayo;
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
@Table(name= "ENSAYOS")
public class Ensayo extends Persistible {

    @Column(name ="NOMBRE")
	private String nombre;
    
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name= "NOTA_TEXTO_ID", nullable = true)
    private NotaTexto descripcion;
    
    @Column(name= "FECHA_EJECUCION")
    private LocalDate fechaEjecucion;
    
    @Column(name= "EQUIPO_USADO")
    private String equipoUsado;
    
   
    @Column(name= "ESTADO_ID")
    @Enumerated(EnumType.ORDINAL)
    private Estado estado;
    
    @OneToMany(cascade= CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name= "ENSAYO_ID", referencedColumnName = "ID", nullable = false)
    private List<Resultado> resultados;
    
   
    @Column(name= "TIPO_DE_ENSAYO")
    @Enumerated(EnumType.STRING)
    private TipoEnsayo tipo;
    
    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name= "USUARIO_ID", nullable = false)
    private Usuario responsable;
    
    //Sólo para Hibernate
    Ensayo(){
    	
    }
    
    
   

    public Ensayo(String nombre, TipoEnsayo tipo, Usuario analista) {
    	this.nombre=nombre;
    	this.fechaEjecucion=LocalDate.now();
    	this.estado=Estado.RECIBIDO;
    	this.tipo=tipo;
    	this.resultados=new ArrayList<>();
    	this.responsable=analista;
    	
    }
    
    public boolean esAprobado() {
    	return Estado.APROBADO.equals(this.estado);
    }
    
    public boolean esRechazado() {
    	return Estado.RECHAZADO.equals(this.estado);
    }
    
    public boolean estadoHabilitadoParaMostrarInforme() {
    	return esAprobado()||esRechazado();
    }
    
    
    
    public Ensayo(String nombre) {
		this.nombre=nombre;
	}

    public void procesarCumplimiento() {
        if (resultados == null || resultados.isEmpty()) {
            return;
        }
        for (Resultado r : resultados) {
            r.calcular(); 
        }
        System.out.println("Cálculo de límites ejecutado para el ensayo: " + nombre);
    }

    public boolean estanTodosLosItemsCargados() {
        if (resultados == null || resultados.isEmpty()) return false;
        boolean estaCargado=true;
        int index=0;
        while(index<this.resultados.size()&& estaCargado) {
        	 Resultado r = this.resultados.get(index);
        	 if(!r.tieneValor()) estaCargado=false;
        	 index++;
        }
        return estaCargado;
    
    }

    public void finalizarEnsayo() {
        if (estanTodosLosItemsCargados()) {
            this.estado = Estado.POR_VALIDAR;
            System.out.println("Ensayo " + nombre + " finalizado. Enviado a revisión.");
        } else {
            System.out.println("⚠️ No se puede finalizar: Quedan ítems sin cargar en la hoja de resultados.");
        }
    }

    public void agregarResultado(Resultado resultado) {
        this.resultados.add(resultado);
    }


    public Resultado getResultadoXId(long id) {
    	Resultado encontrado=null;
    	int index=0;
    	while(index<this.resultados.size()) {
    		Resultado r=this.resultados.get(index);
    		if(r.getId()==id) encontrado=r;
    		index++;
    	}
    	return encontrado;
    }
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public NotaTexto getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(NotaTexto descripcion) {
		this.descripcion = descripcion;
	}
	public LocalDate getFechaEjecucion() {
		return fechaEjecucion;
	}
	public void setFechaEjecucion(LocalDate fechaEjecucion) {
		this.fechaEjecucion = fechaEjecucion;
	}
	public String getEquipoUsado() {
		return equipoUsado;
	}
	public void setEquipoUsado(String equipoUsado) {
		this.equipoUsado = equipoUsado;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public List<Resultado> getResultados() {
		return resultados;
	}
	public void setResultados(List<Resultado> resultados) {
		this.resultados = resultados;
	}
	public TipoEnsayo getTipo() {
		return tipo;
	}
	public void setTipo(TipoEnsayo tipo) {
		this.tipo = tipo;
	}
	public Usuario getResponsable() {
		return responsable;
	}
	public void setResponsable(Usuario responsable) {
		this.responsable = responsable;
	}
    


}