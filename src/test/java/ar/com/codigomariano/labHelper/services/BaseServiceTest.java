package ar.com.codigomariano.labHelper.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.codigomariano.labHelper.domain.Persistible;

@SpringBootTest
@ActiveProfiles(value= "test")
public abstract class BaseServiceTest<T extends Persistible, S extends CRUDService<T>> {
	
	@Autowired
	protected S servicio;
	
	@Test
	public void testGuardarEntidadExitosamente() {
		T entidad= crearEntidadValida();
		this.servicio.guardar(entidad);
	}
	
	protected abstract T crearEntidadValida();
	

}
