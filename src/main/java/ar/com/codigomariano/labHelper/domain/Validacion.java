package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;

import ar.com.codigomariano.labHelper.enums.Estado;

public class Validacion extends Persistible {

	private Ensayo ensayoEvaluado;
	private NotaTexto observacionesFinales;
	private Usuario supervisor;
	private Resultado resultadoValidado;
	
	
	public Validacion(Long id,Ensayo ensayoEvaluado,Usuario supervisor) {
		super(id);
		this.ensayoEvaluado=ensayoEvaluado;
		this.supervisor=supervisor;
	
	
	}
	
	public void aprobarEnsayo(String motivo) {
		if(this.ensayoEvaluado.getEstado().equals(Estado.APROBADO)) {
			LocalDateTime.now();
			this.observacionesFinales.setContenido(motivo);
			System.out.println(motivo);
		} else {
			this.observacionesFinales.setContenido(motivo);
			System.out.println(motivo);
		}
	}

	public Ensayo getEnsayoEvaluado() {
		return ensayoEvaluado;
	}

	public void setEnsayoEvaluado(Ensayo ensayoEvaluado) {
		this.ensayoEvaluado = ensayoEvaluado;
	}

	public NotaTexto getObservacionesFinales() {
		return observacionesFinales;
	}

	public void setObservacionesFinales(String observacionesFinales) {
		this.observacionesFinales.setContenido(observacionesFinales);
	}

	public Usuario getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Usuario supervisor) {
		this.supervisor = supervisor;
	}
	
	
	

}
