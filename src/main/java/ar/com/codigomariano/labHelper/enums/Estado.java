package ar.com.codigomariano.labHelper.enums;

public enum Estado {
    RECIBIDO (0.0),
    EN_ANALISIS(30.0),
    COMPLETADO(100.0),
    POR_VALIDAR(75.0),
    APROBADO(100.0),
    RECHAZADO(0.0);
    
    private double progreso;
	
	private Estado(double progreso) {
		this.setProgreso(progreso);
	}

	public double getProgreso() {
		return progreso;
	}

	public void setProgreso(double progreso) {
		this.progreso = progreso;
	}
}