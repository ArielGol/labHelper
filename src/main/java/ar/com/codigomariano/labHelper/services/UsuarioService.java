package ar.com.codigomariano.labHelper.services;

import ar.com.codigomariano.labHelper.domain.Usuario;

public interface UsuarioService {
	
	public void guardar(Usuario usuario);
	
	public Usuario obtener(Long id);
	
	public boolean existeUsuario(String email);

}
