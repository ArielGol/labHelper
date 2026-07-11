package ar.com.codigomariano.labHelper.domain;

public abstract class Persistible {
	
	private Long id;
	
	public Persistible(Long id) {
		setId(id);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
