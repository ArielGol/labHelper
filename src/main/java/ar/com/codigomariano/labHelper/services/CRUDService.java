package ar.com.codigomariano.labHelper.services;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.domain.Persistible;

public interface CRUDService <T extends Persistible> {
	
	public void guardar(T entidad);

}
