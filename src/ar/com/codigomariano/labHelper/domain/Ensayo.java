package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.EstadoEnsayo;
import ar.com.codigomariano.labHelper.enums.TipoEnsayo;


public class Ensayo extends Persistible {

    private String nombre;
    private NotaTexto descripcion;
    private LocalDate fechaEjecucion;
    private String equipoUsado;
    private EstadoEnsayo estado;
    private List<Resultado> resultados;
    private TipoEnsayo tipo;
    private List<Imagen> graficos;
    private Usuario responsable;
   

    public Ensayo(Long id, String nombre, TipoEnsayo tipo, Usuario analista) {
    	super(id);
    	this.nombre=nombre;
    	this.fechaEjecucion=LocalDate.now();
    	this.estado=EstadoEnsayo.PENDIENTE;
    	this.tipo=tipo;
    	this.resultados=new ArrayList<>();
    	this.responsable=analista;
    	this.graficos=new ArrayList<>();
    }
    public Ensayo(Long id,String nombre) {
    	super(id);
		this.nombre=nombre;
	}
	public void agregarGrafico(Imagen nuevaImagen) {
        if (nuevaImagen != null) {
            this.graficos.add(nuevaImagen);
            System.out.println("Gráfico '" + nuevaImagen.getNombre() + "' adjuntado con éxito al ensayo.");
        }
    }
    public void procesarCumplimiento() {
        if (resultados == null || resultados.isEmpty()) {
            return;
        }
        for (Resultado r : resultados) {
            r.calcularSiCumple(); 
        }
        System.out.println("Cálculo de límites ejecutado para el ensayo: " + nombre);
    }

    public boolean estanTodosLosItemsCargados() {
        if (resultados == null || resultados.isEmpty()) return false;
        for (Resultado r : resultados) {
            if (r.getValorObtenido() == null || r.getValorObtenido() == 0.0) {
                return false;
            }
        }
        return true;
    }

    public void finalizarEnsayo() {
        if (estanTodosLosItemsCargados()) {
            this.estado = EstadoEnsayo.POR_VALIDAR;
            System.out.println("Ensayo " + nombre + " finalizado. Enviado a revisión.");
        } else {
            System.out.println("⚠️ No se puede finalizar: Quedan ítems sin cargar en la hoja de resultados.");
        }
    }

    public void agregarResultado(Resultado resultado) {
        this.resultados.add(resultado);
    }

    public List<Imagen> getGraficos() {
        return this.graficos;
    }

    public void setGraficos(List<Imagen> graficos) {
        this.graficos = graficos;
    }
    public Resultado getResultadoXId(long id) {
        for (Resultado r : this.resultados) {
            if (r.getId() == id) {
                return r; 
            }
        }
        return null; 
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
	public EstadoEnsayo getEstado() {
		return estado;
	}
	public void setEstado(EstadoEnsayo estado) {
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