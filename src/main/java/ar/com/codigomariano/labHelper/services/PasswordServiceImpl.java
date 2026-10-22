package ar.com.codigomariano.labHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.helpers.ValidationUtils;
import ar.com.codigomariano.labHelper.repositories.PasswordRepository;

@Service
public class PasswordServiceImpl implements PasswordService {
	
	public final static String PASSWORD_OBLIGATORIA="La contraseña es obligatoria.";
	public final static String PASSWORD_MAX_LONGITUD="La contraseña no debe superar los %d caracteres.";
	public final static String PASSWORD_MIN_LONGITUD="La contraseña debe tener al menos %d caracteres.";
	public final static String PASSWORD_CON_ESPACIOS="La contraseña no debe contener espacios.";
	public final static String PASSWORD_SIN_MAYUSCULA="La contraseña debe tener al menos una mayúscula.";
	public final static String PASSWORD_SIN_MINUSCULA="La contraseña debe tener al menos una minúscula.";
	public final static String PASSWORD_SIN_NUMERO="La contraseña debe tener al menos un número.";
	public final static int PASSWORD_MAX=72;
	public final static int PASSWORD_MIN=8;
	
	@Autowired
	private PasswordRepository passwordRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Password crearPassword(String valorPlano) {
    	validarValorPlano(valorPlano);
        String valorEncriptado = passwordEncoder.encode(valorPlano);
        Password password = new Password(valorEncriptado);
        return passwordRepository.save(password);
    }
    
    private void validarValorPlano(String valorPlano) {
    	if(ValidationUtils.isEmply(valorPlano)) {
    		throw new IllegalArgumentException(PASSWORD_OBLIGATORIA);
    	} else if(ValidationUtils.textoTieneMasCaracteres(valorPlano, PASSWORD_MAX)) {
    		throw new IllegalArgumentException(String.format(PASSWORD_MAX_LONGITUD,PASSWORD_MAX));
    	} else if(ValidationUtils.textoTieneMenosCaracteres(valorPlano, PASSWORD_MIN)) {
    		throw new IllegalArgumentException(String.format(PASSWORD_MIN_LONGITUD,PASSWORD_MIN));
    	} else if(ValidationUtils.textoTieneEspacios(valorPlano)) {
    		throw new IllegalArgumentException(PASSWORD_CON_ESPACIOS);
    	} else if(!ValidationUtils.textoTieneMayuscula(valorPlano)) {
    		throw new IllegalArgumentException(PASSWORD_SIN_MAYUSCULA);
    	} else if(!ValidationUtils.textoTieneMinuscula(valorPlano)) {
    		throw new IllegalArgumentException(PASSWORD_SIN_MINUSCULA);
    	} else if(!ValidationUtils.textoTieneNumero(valorPlano)) {
    		throw new IllegalArgumentException(PASSWORD_SIN_NUMERO);
    	}
    }

    @Override
    public boolean coincide(String valorPlano, Password password) {
    	boolean resultado=false;
    	if(!ValidationUtils.isEmply(valorPlano) && password!=null) {
    		resultado=passwordEncoder.matches(valorPlano, password.getValor());
    	}
    	return resultado;
    }
}
