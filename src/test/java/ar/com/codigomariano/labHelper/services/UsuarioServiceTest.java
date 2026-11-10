package ar.com.codigomariano.labHelper.services;



import ar.com.codigomariano.labHelper.domain.Usuario;
import ar.com.codigomariano.labHelper.utilities.EntityHelper;


public class UsuarioServiceTest extends BaseServiceTest<Usuario, UsuarioService> {

	@Override
	protected Usuario crearEntidadValida() {
		return EntityHelper.crearUsuarioValido();
	}
	
	
}
