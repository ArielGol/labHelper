package ar.com.codigomariano.labHelper.services;

import ar.com.codigomariano.labHelper.domain.Password;

public interface PasswordService extends CRUDService<Password> {
	
	Password crearPassword(String valorPlano);
	
	boolean coincide(String valorPlano, Password password);

}
