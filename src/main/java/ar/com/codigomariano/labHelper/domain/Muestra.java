package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.labHelper.enums.Estado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name= "MUESTRAS")
public class Muestra extends Persistible{

    private static final double VALOR_INICIAL = 0;
    
    @Column(name= "CODIGO_MUESTRA")
	private String codigoMuestra;
   
    
    @Column(name= "FECHA_INGRESO")
    private LocalDate fechaIngreso;
    
    @Column(name= "FECHA_SALIDA")
    private LocalDate fechaSalida;
    
    
    @Column(name = "ESTADO_ID")
    @Enumerated(EnumType.ORDINAL)
    private Estado estado;
    

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "MUESTRA_ID", referencedColumnName = "ID", nullable = false)
    private List<Ensayo> ensayosAsignados;
    
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "NOTA_TEXTO_ID", nullable = false)
    private NotaTexto descripcion;
    
    //Sólo para Hibernate
    Muestra(){
    	
    }
   

    public Muestra(NotaTexto descripcion) {
    	setCodigoMuestra();
    	setDescripcion(descripcion);
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


