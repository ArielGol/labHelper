package ar.com.codigomariano.labHelper.services;

import org.springframework.security.crypto.password.PasswordEncoder;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.repositories.PasswordRepository;

public class PasswordServiceImpl implements PasswordService {

	private final PasswordRepository passwordRepository;
    private final PasswordEncoder passwordEncoder;

    public PasswordServiceImpl(PasswordRepository passwordRepository, PasswordEncoder passwordEncoder) {
        this.passwordRepository = passwordRepository;
        this.passwordEncoder = passwordEncoder;
    }

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
