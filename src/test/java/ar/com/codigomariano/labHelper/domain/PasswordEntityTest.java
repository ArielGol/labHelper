package ar.com.codigomariano.labHelper.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.labHelper.utilities.EntityHelper;

public class PasswordEntityTest extends BaseEntityTest<Password>{
	
	@Override
	protected Password crearEntidadValida() {
		return EntityHelper.crearPasswordValido();
	}

	@Override
	protected void validarEntidadExitosa(Password password) {
		assertEquals(EntityHelper.PASSWORD_VALIDO, password.getValor());
		
	}
	
	@Test
	public void testCrearPasswordValorNulo() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Password(null));
		assertEquals(Password.VALOR_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearPasswordValorVacio() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Password(""));
		assertEquals(Password.VALOR_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearPasswordValorEnBlanco() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Password("     "));
		assertEquals(Password.VALOR_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testSetValorExitosamente() {
		Password password=EntityHelper.crearPasswordValido();
		password.setValor("OtraClave123");

		assertEquals("OtraClave123", password.getValor());
	}

	@Test
	public void testSetValorNuloLanzaExcepcion() {
		Password password=EntityHelper.crearPasswordValido();

		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> password.setValor(null));
		assertEquals(Password.VALOR_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testSetValorInvalidoNoCambiaElValor() {
		Password password=EntityHelper.crearPasswordValido();

		assertThrows(IllegalArgumentException.class, ()-> password.setValor(""));
		assertEquals(EntityHelper.PASSWORD_VALIDO, password.getValor());
	}



}
