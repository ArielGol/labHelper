package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "VALIDACIONES")
public class Validacion extends Persistible {
	
    @Column(name = "APROBADO")
    private boolean aprobado;

    @Column(name = "FECHA_FIRMA")
    private LocalDateTime fechaFirma;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "NOTA_TEXTO_ID", nullable = true)
    private NotaTexto observacionesFinales;

    @ManyToOne
    @JoinColumn(name = "USUARIO_ID", nullable = false)
    private Usuario supervisor;

    @ManyToOne
    @JoinColumn(name = "RESULTADO_ID", nullable = false)
    private Resultado resultadoValidado;
    
    //Sólo para Hibernate
    Validacion(){
    	
    }

    public Validacion(Resultado resultadoValidado, Usuario supervisor) {
        this.resultadoValidado = resultadoValidado;
        this.supervisor = supervisor;
    }

    public void mostrarMotivo(String motivo) {
        if (this.resultadoValidado.getEnsayo().estadoHabilitadoParaMostrarInforme()) {
            this.observacionesFinales.setContenido(motivo);
            System.out.println(LocalDateTime.now() + ": " + getObservacionesFinales());
        }
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

    public Resultado getResultadoValidado() {
        return resultadoValidado;
    }

    public void setResultadoValidado(Resultado resultadoValidado) {
        this.resultadoValidado = resultadoValidado;
    }
	
	

}
