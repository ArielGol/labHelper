package ar.com.codigomariano.labHelper.domain;


public class Resultado extends Persistible {
 
	private String parametro;
    private String unidad;
    private Double valorMaximo;
    private Double valorMinimo;
    private Double valorObtenido;
    private boolean cumple;
    private NotaTexto observaciones;

    public Resultado(Long id,String parametro,String unidad,Double min,Double max) {
 		super(id);
 		this.parametro=parametro;
 		this.unidad=unidad;
 		this.valorMaximo=max;
 		this.valorMinimo=min;
 		
 	}



	 public void calcularSiCumple() {
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



	 
	


}