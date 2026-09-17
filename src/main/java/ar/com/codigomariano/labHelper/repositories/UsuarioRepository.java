package ar.com.codigomariano.labHelper.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codigomariano.labHelper.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
 
	public List<Usuario> findByEmail(String email);
}
