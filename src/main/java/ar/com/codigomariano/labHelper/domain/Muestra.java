package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.Estado;



public class Muestra extends Persistible{

    private static final double VALOR_INICIAL = 0;
	private String codigoMuestra;
    private Cliente cliente;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Estado estado;
    private List<Ensayo> ensayosAsignados;
    private NotaTexto descripcion;
   

    public Muestra(Long id,Cliente cliente,NotaTexto descripcion) {
    	super(id);
    	setCodigoMuestra();
    	setDescripcion(descripcion);
    	this.cliente=cliente;
    	this.fechaIngreso=LocalDate.now();
    	this.ensayosAsignados=new ArrayList<Ensayo>();
    	this.estado=Estado.RECIBIDO;
 
  
    }
    

    public void asignarEnsayo(Ensayo ensayo) {
        this.ensayosAsignados.add(ensayo);
     
    }

    public void eliminarEnsayo(String nombre) {
        Ensayo aEliminar = null;
        int index=0;
        while(index<this.ensayosAsignados.size()) {
        	Ensayo e=this.ensayosAsignados.get(index);
        	if(e.getNombre().equalsIgnoreCase(nombre)) aEliminar=e;
        	index++;
        }
        if (aEliminar != null) {
            this.ensayosAsignados.remove(aEliminar);
            System.out.println("Ensayo: " + nombre + " eliminado.");
        } else {
            System.out.println("No se encontró el ensayo: " + nombre);
        }
    }
    public double calcularProgreso() {
        if (ensayosAsignados == null || ensayosAsignados.isEmpty()) {
            return VALOR_INICIAL;
        }
        double suma = VALOR_INICIAL;
        for (Ensayo ensayo : ensayosAsignados) {
        	suma=ensayo.getEstado().getProgreso();
            }
        return Math.round((suma / ensayosAsignados.size()) * 100.0) / 100.0;
    }

	public String getCodigoMuestra() {
		return codigoMuestra;
	}

	public void setCodigoMuestra() {
		this.codigoMuestra = "MUE-"+LocalDate.now().getYear()+"-"+getId();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDate fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public List<Ensayo> getEnsayosAsignados() {
		return ensayosAsignados;
	}


	public NotaTexto getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(NotaTexto descripcion) {
		this.descripcion = descripcion;
	}

 
    


}


