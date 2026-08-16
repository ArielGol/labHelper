package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.Estado;
import ar.com.codigomariano.labHelper.enums.TipoEnsayo;


public class Ensayo extends Persistible {

    private String nombre;
    private NotaTexto descripcion;
    private LocalDate fechaEjecucion;
    private String equipoUsado;
    private Estado estado;
    private List<Resultado> resultados;
    private TipoEnsayo tipo;
    private Usuario responsable;
    private List<Muestra> muestras;
   

    public Ensayo(Long id, String nombre, TipoEnsayo tipo, Usuario analista) {
    	super(id);
    	this.nombre=nombre;
    	this.fechaEjecucion=LocalDate.now();
    	this.estado=Estado.RECIBIDO;
    	this.tipo=tipo;
    	this.resultados=new ArrayList<>();
    	this.responsable=analista;
    	this.muestras=new ArrayList<>();
    	
    }
    
    public void agregarMuestras(Muestra muestra) {
    	this.muestras.add(muestra);
    }
    
    public void eliminarMuestras(Long id) {
    	
    }
    
    
    public Ensayo(Long id,String nombre) {
    	super(id);
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