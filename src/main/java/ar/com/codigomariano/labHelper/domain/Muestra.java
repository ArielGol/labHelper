package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.Estado;



public class Muestra extends Persistible{

    private String codigoMuestra;
    private Cliente cliente;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private Estado estado;
    private List<Ensayo> ensayosAsignados;
    private NotaTexto descripcion;
    private static int next_id=1;

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
        for (Ensayo ensayo : ensayosAsignados) {
            if (ensayo.getNombre().equalsIgnoreCase(nombre)) {
                aEliminar = ensayo;
                break;
            }
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
            return 0.0;
        }
        double suma = 0.0;
        for (Ensayo ensayo : ensayosAsignados) {
            switch (ensayo.getEstado()) {
                case RECIBIDO: suma += 0.0; break;
                case EN_ANALISIS: suma += 30.0; break;
                case POR_VALIDAR: suma += 75.0; break;
                case APROBADO: suma += 100.0; break;
          
            }
        }
        return Math.round((suma / ensayosAsignados.size()) * 100.0) / 100.0;
    }

	public String getCodigoMuestra() {
		return codigoMuestra;
	}

	public void setCodigoMuestra() {
		this.codigoMuestra = "MUE-"+LocalDate.now().getYear()+"-"+next_id;
		next_id++;
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


