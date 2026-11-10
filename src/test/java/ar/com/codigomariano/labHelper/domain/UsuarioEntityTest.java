package ar.com.codigomariano.labHelper.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.labHelper.utilities.EntityHelper;

public class UsuarioEntityTest extends BaseEntityTest<Usuario> {
	
	@Override
	protected Usuario crearEntidadValida() {
		return EntityHelper.crearUsuarioValido();
	}

	@Override
	protected void validarEntidadExitosa(Usuario usuario) {
		assertNotNull(usuario.getFechaCreacion());
		assertNotNull(usuario.getNombreUsuario());
		assertEquals(EntityHelper.EMAIL_VALIDO, usuario.getEmail());
	}
		
		
	
	@Test
	public void testCrearUsuarioEmailNulo() {
		IllegalArgumentException ex= assertThrows(IllegalArgumentException.class, ()-> new Usuario("Ariel",null,new Password("Ariel336549")));
		assertEquals(Usuario.EMAIL_OBLIGATORIO, ex.getMessage());
	}
	@Test
	public void testCrearUsuarioEmailVacio() {
		try {
			new Usuario(EntityHelper.NOMBRE_VALIDO,"",EntityHelper.crearPasswordValido());
		}catch(IllegalArgumentException ex) {
			assertEquals(Usuario.EMAIL_OBLIGATORIO, ex.getMessage());
		}
	}

	@Test
	public void testCrearUsuarioEmailEnBlanco() {
		IllegalArgumentException ex= assertThrows(IllegalArgumentException.class, ()-> new Usuario("Ariel","     ",new Password("Ariel336549")));
		assertEquals(Usuario.EMAIL_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearUsuarioEmailSinArroba() {
		IllegalArgumentException ex= assertThrows(IllegalArgumentException.class, ()-> new Usuario("Ariel","arielgolubgmail.com",new Password("Ariel336549")));
		assertEquals(Usuario.EMAIL_FORMATO_INVALID, ex.getMessage());
	}

	@Test
	public void testCrearUsuarioEmailMuyCorto() {
		IllegalArgumentException ex= assertThrows(IllegalArgumentException.class, ()-> new Usuario("Ariel","ari@gmail.com",new Password("Ariel336549")));
		assertEquals(String.format(Usuario.EMAIL_MIN_LONGITUD, Usuario.EMAIL_MIN), ex.getMessage());
	}

	@Test
	public void testCrearUsuarioEmailMuyLargo() {
		String emailLargo="a".repeat(70)+"@gmail.com";
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Usuario("Ariel",emailLargo,new Password("Ariel336549")));
		assertEquals(String.format(Usuario.EMAIL_MAX_LONGITUD,Usuario.EMAIL_MAX), ex.getMessage());
	}




}
