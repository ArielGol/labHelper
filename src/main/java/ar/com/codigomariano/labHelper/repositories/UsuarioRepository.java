package ar.com.codigomariano.labHelper.repositories;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.codigomariano.labHelper.domain.Usuario;

@Repository
public interface UsuarioRepository extends BaseRepository<Usuario> {
 
	public List<Usuario> findByEmail(String email);
	
	List<Usuario> findByEmailAndIdNot(String email, Long id);
}
