package ar.com.codigomariano.labHelper.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.utilities.EntityHelper;


public class PasswordServiceImpEntityTest extends BaseServiceTest<Password, PasswordService> {



	@Test
	public void testCrearPasswordExitosamente() {
		Password password=servicio.crearPassword(EntityHelper.PASSWORD_VALIDO);

		assertNotNull(password);
		assertNotNull(password.getValor());
	}

	@Test
	public void testCrearPasswordGuardaElValorEncriptado() {
		Password password=servicio.crearPassword(EntityHelper.PASSWORD_VALIDO);

		assertNotEquals(EntityHelper.PASSWORD_VALIDO, password.getValor());
	}

	@Test
	public void testCrearPasswordMismaClaveGeneraHashesDistintos() {
		Password password1=servicio.crearPassword(EntityHelper.PASSWORD_VALIDO);
		Password password2=servicio.crearPassword(EntityHelper.PASSWORD_VALIDO);

		assertNotEquals(password1.getValor(), password2.getValor());
	}


	@Test
	public void testCrearPasswordNula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword(null));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordVacia() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword(""));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnBlanco() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("     "));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordMuyCorta() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("Abcde12"));
		assertEquals(String.format(PasswordServiceImpl.PASSWORD_MIN_LONGITUD, PasswordServiceImpl.PASSWORD_MIN), ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnElLimiteMinimo() {
		Password password=servicio.crearPassword("Abcdef12");
		assertNotNull(password);
	}

	@Test
	public void testCrearPasswordMuyLarga() {
		String larga="Aa1"+"a".repeat(70);
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword(larga));
		assertEquals(String.format(PasswordServiceImpl.PASSWORD_MAX_LONGITUD, PasswordServiceImpl.PASSWORD_MAX), ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnElLimiteMaximo() {
		String limite="Aa1"+"a".repeat(69);
		Password password=servicio.crearPassword(limite);
		assertNotNull(password);
	}

	@Test
	public void testCrearPasswordConEspacios() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("Ariel 336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_CON_ESPACIOS, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinMayuscula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("ariel336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_MAYUSCULA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinMinuscula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("ARIEL336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_MINUSCULA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinNumero() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> servicio.crearPassword("ArielGolub"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_NUMERO, ex.getMessage());
	}


	@Test
	public void testCoincideConLaContraseniaCorrecta() {
		Password password=servicio.crearPassword("Ariel336549");

		assertTrue(servicio.coincide("Ariel336549", password));
	}

	@Test
	public void testCoincideConLaContraseniaIncorrecta() {
		Password password=servicio.crearPassword("Ariel336549");

		assertFalse(servicio.coincide("OtraClave123", password));
	}

	@Test
	public void testCoincideDiferenciaMayusculas() {
		Password password=servicio.crearPassword("Ariel336549");

		assertFalse(servicio.coincide("ariel336549", password));
	}

	@Test
	public void testCoincideValorPlanoNulo() {
		Password password=servicio.crearPassword("Ariel336549");

		assertFalse(servicio.coincide(null, password));
	}

	@Test
	public void testCoincideValorPlanoVacio() {
		Password password=servicio.crearPassword("Ariel336549");

		assertFalse(servicio.coincide("", password));
	}

	@Test
	public void testCoincidePasswordNulo() {
		assertFalse(servicio.coincide("Ariel336549", null));
	}

	@Override
	protected Password crearEntidadValida() {
		return EntityHelper.crearPasswordValido();
	}

}
