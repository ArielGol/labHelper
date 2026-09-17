package ar.com.codigomariano.labHelper.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="RESULTADOS")
public class Resultado extends Persistible {
 
	private static final Double VALOR_POR_DEFECTO = 0.0;
	
	@Column(name= "PARAMETRO")
	private String parametro;
	
	@Column(name= "UNIDAD")
    private String unidad;
	
	@Column(name= "VALOR_MAXIMO")
    private Double valorMaximo;
	
	@Column(name= "VALOR_MINIMO")
    private Double valorMinimo;
	
	@Column(name= "VALOR_OBTENIDO")
    private Double valorObtenido;
	
	@Column(name= "CUMPLE")
    private boolean cumple;
	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "NOTA_TEXTO_ID")
    private NotaTexto observaciones;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval = true)
	@JoinColumn(name= "RESULTADO_ID", referencedColumnName = "ID", nullable = false)
    private List<Imagen> graficos;
	
	@ManyToOne
	@JoinColumn(name = "ENSAYO_ID", nullable = false)
	private Ensayo ensayo;

	//Sólo para Hibernate
	Resultado(){
		
	}

	public Resultado(String parametro,String unidad,Double min,Double max) {
 		this.parametro=parametro;
 		this.unidad=unidad;
 		this.valorMaximo=max;
 		this.valorMinimo=min;
 		this.graficos=new ArrayList<Imagen>();
 		
 	}



	 public void calcular() {
		 if(this.valorObtenido!=null) {
			 this.cumple = (this.valorObtenido >= this.valorMinimo && this.valorObtenido <= this.valorMaximo);
		  }
	 	}

	 public Double getValorObtenido() {
		 return valorObtenido;
	 }



	 public String getParametro() {
		 return parametro;
	 }



	 public void setParametro(String parametro) {
		 this.parametro = parametro;
	 }



	 public String getUnidad() {
		 return unidad;
	 }



	 public void setUnidad(String unidad) {
		 this.unidad = unidad;
	 }



	 public Double getValorMaximo() {
		 return valorMaximo;
	 }



	 public void setValorMaximo(Double valorMaximo) {
		 this.valorMaximo = valorMaximo;
	 }



	 public Double getValorMinimo() {
		 return valorMinimo;
	 }



	 public void setValorMinimo(Double valorMinimo) {
		 this.valorMinimo = valorMinimo;
	 }



	 public NotaTexto getObservaciones() {
		 return observaciones;
	 }



	 public void setObservaciones(NotaTexto observaciones) {
		 this.observaciones = observaciones;
	 }



	 public void setValorObtenido(Double valorObtenido) {
		 this.valorObtenido = valorObtenido;
	 }



	 public boolean isCumple() {
		return this.cumple;
	 }

	public void agregarGrafico(Imagen nuevaImagen) {
	     if (nuevaImagen != null) {
	          this.graficos.add(nuevaImagen);
	          System.out.println("Gráfico '" + nuevaImagen.getNombre() + "' adjuntado con éxito al ensayo.");
	        }
	    }

    public List<Imagen> getGraficos() {
        return this.graficos;
    }

    public void setGraficos(List<Imagen> graficos) {
        this.graficos = graficos;
    }



	public boolean tieneValor() {
		return this.valorObtenido !=null && this.valorObtenido !=VALOR_POR_DEFECTO;
	}
	
	public Ensayo getEnsayo() {
			return ensayo;
		}
	
	public void setEnsayo(Ensayo ensayo) {
			this.ensayo = ensayo;
		}






}