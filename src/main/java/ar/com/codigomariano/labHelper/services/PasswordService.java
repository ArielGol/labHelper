package ar.com.codigomariano.labHelper.services;

import ar.com.codigomariano.labHelper.domain.Password;

public interface PasswordService {
	
	Password crearPassword(String valorPlano);
	
	boolean coincide(String valorPlano, Password password);

}
