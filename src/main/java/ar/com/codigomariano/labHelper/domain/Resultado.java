package ar.com.codigomariano.labHelper.domain;

import java.util.ArrayList;
import java.util.List;

public class Resultado extends Persistible {
 
	private static final Double VALOR_POR_DEFECTO = 0.0;
	private String parametro;
    private String unidad;
    private Double valorMaximo;
    private Double valorMinimo;
    private Double valorObtenido;
    private boolean cumple;
    private NotaTexto observaciones;
    private List<Imagen> graficos;

    public Resultado(String parametro,String unidad,Double min,Double max) {
 		this.parametro=parametro;
 		this.unidad=unidad;
 		this.valorMaximo=max;
 		this.valorMinimo=min;
 		this.graficos=new ArrayList<>();
 		
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
	


}