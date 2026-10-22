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

import ar.com.codigomariano.labHelper.domain.Password;

@SpringBootTest
public class PasswordServiceImpEntityTest {
	@Autowired
	private PasswordService passwordService;


	@Test
	public void testCrearPasswordExitosamente() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertNotNull(password);
		assertNotNull(password.getValor());
	}

	@Test
	public void testCrearPasswordGuardaElValorEncriptado() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertNotEquals("Ariel336549", password.getValor());
	}

	@Test
	public void testCrearPasswordMismaClaveGeneraHashesDistintos() {
		Password password1=passwordService.crearPassword("Ariel336549");
		Password password2=passwordService.crearPassword("Ariel336549");

		assertNotEquals(password1.getValor(), password2.getValor());
	}


	@Test
	public void testCrearPasswordNula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword(null));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordVacia() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword(""));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnBlanco() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("     "));
		assertEquals(PasswordServiceImpl.PASSWORD_OBLIGATORIA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordMuyCorta() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("Abcde12"));
		assertEquals(String.format(PasswordServiceImpl.PASSWORD_MIN_LONGITUD, PasswordServiceImpl.PASSWORD_MIN), ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnElLimiteMinimo() {
		Password password=passwordService.crearPassword("Abcdef12");
		assertNotNull(password);
	}

	@Test
	public void testCrearPasswordMuyLarga() {
		String larga="Aa1"+"a".repeat(70);
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword(larga));
		assertEquals(String.format(PasswordServiceImpl.PASSWORD_MAX_LONGITUD, PasswordServiceImpl.PASSWORD_MAX), ex.getMessage());
	}

	@Test
	public void testCrearPasswordEnElLimiteMaximo() {
		String limite="Aa1"+"a".repeat(69);
		Password password=passwordService.crearPassword(limite);
		assertNotNull(password);
	}

	@Test
	public void testCrearPasswordConEspacios() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("Ariel 336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_CON_ESPACIOS, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinMayuscula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("ariel336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_MAYUSCULA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinMinuscula() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("ARIEL336549"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_MINUSCULA, ex.getMessage());
	}

	@Test
	public void testCrearPasswordSinNumero() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> passwordService.crearPassword("ArielGolub"));
		assertEquals(PasswordServiceImpl.PASSWORD_SIN_NUMERO, ex.getMessage());
	}


	@Test
	public void testCoincideConLaContraseniaCorrecta() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertTrue(passwordService.coincide("Ariel336549", password));
	}

	@Test
	public void testCoincideConLaContraseniaIncorrecta() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertFalse(passwordService.coincide("OtraClave123", password));
	}

	@Test
	public void testCoincideDiferenciaMayusculas() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertFalse(passwordService.coincide("ariel336549", password));
	}

	@Test
	public void testCoincideValorPlanoNulo() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertFalse(passwordService.coincide(null, password));
	}

	@Test
	public void testCoincideValorPlanoVacio() {
		Password password=passwordService.crearPassword("Ariel336549");

		assertFalse(passwordService.coincide("", password));
	}

	@Test
	public void testCoincidePasswordNulo() {
		assertFalse(passwordService.coincide("Ariel336549", null));
	}

}
