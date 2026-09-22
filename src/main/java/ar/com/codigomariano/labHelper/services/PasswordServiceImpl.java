package ar.com.codigomariano.labHelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.repositories.PasswordRepository;

@Service
public class PasswordServiceImpl implements PasswordService {
	
	@Autowired
	private PasswordRepository passwordRepository;
	
	@Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Password crearPassword(String valorPlano) {
        String valorEncriptado = passwordEncoder.encode(valorPlano);
        Password password = new Password(valorEncriptado);
        return passwordRepository.save(password);
    }

    @Override
    public boolean coincide(String valorPlano, Password password) {
        return passwordEncoder.matches(valorPlano, password.getValor());
    }
}
