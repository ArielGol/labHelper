package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;

import ar.com.codigomariano.labHelper.enums.EstadoEnsayo;

public class Validacion extends Persistible {

	private boolean aprobado;
	private LocalDateTime fechaFirma;
	private Ensayo ensayoEvaluado;
	private NotaTexto observacionesFinales;
	private Usuario supervisor;
	
	
	
	public Validacion(Long id,Ensayo ensayoEvaluado,Usuario supervisor) {
		super(id);
		this.ensayoEvaluado=ensayoEvaluado;
		this.supervisor=supervisor;
		this.observacionesFinales = new NotaTexto("");
	
	}
	
	public void aprobarEnsayo(String motivo) {
		if(this.ensayoEvaluado.getEstado().equals(EstadoEnsayo.APROBADO)) {
			this.aprobado=true;
			this.fechaFirma=LocalDateTime.now();
			this.observacionesFinales.setContenido(motivo);
			System.out.println(motivo);
		} else {
			this.aprobado=false;
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
