package ar.com.codigomariano.labHelper.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codigomariano.labHelper.domain.Usuario;
import ar.com.codigomariano.labHelper.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	private UsuarioRepository repositorio;

	@Override
	public void guardar(Usuario usuario) {
		this.repositorio.save(usuario);
		
	}

	@Override
	public Usuario obtener(Long id) {
		Optional<Usuario> user= this.repositorio.findById(id);
		return user.get();
	}

	@Override
	public boolean existeUsuario(String email) {
		boolean existe=false;
		if(email !=null) {
			List<Usuario> usuarios=this.repositorio.findByEmail(email);
			existe=! usuarios.isEmpty();
		}
		return existe;
	}

}
