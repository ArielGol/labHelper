package ar.com.codigomariano.labHelper.helpers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.labHelper.utilities.EntityHelper;

public class ValidationUtilsTest {
	

	@Test
	public void testEmailValido() {
		assertTrue(ValidationUtils.esValidoEmail(EntityHelper.EMAIL_VALIDO));
	}

	@Test
	public void testEmailSinArroba() {
		assertFalse(ValidationUtils.esValidoEmail(EntityHelper.EMAIL_SINARROBA));
	}

	@Test
	public void testEmailNulo() {
		assertFalse(ValidationUtils.esValidoEmail(null));
	}

	@Test
	public void testEmailVacio() {
		assertFalse(ValidationUtils.esValidoEmail(""));
	}

	@Test
	public void testEmailEnBlanco() {
		assertFalse(ValidationUtils.esValidoEmail("     "));
	}

	@Test
	public void testEmailSoloArrobaEsValidoPorLaReglaActual() {
		assertTrue(ValidationUtils.esValidoEmail("@"));
	}


	@Test
	public void testTextoTieneMasCaracteresSuperaElLimite() {
		assertTrue(ValidationUtils.textoTieneMasCaracteres("abcdef", 5));
	}

	@Test
	public void testTextoTieneMasCaracteresEnElLimiteExacto() {
		assertFalse(ValidationUtils.textoTieneMasCaracteres("abcde", 5));
	}

	@Test
	public void testTextoTieneMasCaracteresPorDebajoDelLimite() {
		assertFalse(ValidationUtils.textoTieneMasCaracteres("abcd", 5));
	}

	@Test
	public void testTextoTieneMasCaracteresNulo() {
		assertFalse(ValidationUtils.textoTieneMasCaracteres(null, 5));
	}

	@Test
	public void testTextoTieneMasCaracteresVacio() {
		assertFalse(ValidationUtils.textoTieneMasCaracteres("", 5));
	}

	@Test
	public void testTextoTieneMasCaracteresEnBlancoDevuelveFalse() {
		assertFalse(ValidationUtils.textoTieneMasCaracteres("          ", 5));
	}


	@Test
	public void testTextoTieneMenosCaracteresPorDebajoDelLimite() {
		assertTrue(ValidationUtils.textoTieneMenosCaracteres("abcd", 5));
	}

	@Test
	public void testTextoTieneMenosCaracteresEnElLimiteExacto() {
		assertFalse(ValidationUtils.textoTieneMenosCaracteres("abcde", 5));
	}

	@Test
	public void testTextoTieneMenosCaracteresSuperaElLimite() {
		assertFalse(ValidationUtils.textoTieneMenosCaracteres("abcdef", 5));
	}

	@Test
	public void testTextoTieneMenosCaracteresNulo() {
		assertFalse(ValidationUtils.textoTieneMenosCaracteres(null, 5));
	}

	@Test
	public void testTextoTieneMenosCaracteresVacio() {
		assertFalse(ValidationUtils.textoTieneMenosCaracteres("", 5));
	}

	@Test
	public void testTextoTieneMenosCaracteresEnBlancoDevuelveFalse() {
		assertFalse(ValidationUtils.textoTieneMenosCaracteres("  ", 5));
	}



	@Test
	public void testIsEmplyNulo() {
		assertTrue(ValidationUtils.isEmply(null));
	}

	@Test
	public void testIsEmplyVacio() {
		assertTrue(ValidationUtils.isEmply(""));
	}

	@Test
	public void testIsEmplyEnBlanco() {
		assertTrue(ValidationUtils.isEmply("     "));
	}

	@Test
	public void testIsEmplyConTexto() {
		assertFalse(ValidationUtils.isEmply("hola"));
	}

	@Test
	public void testIsEmplyConTextoYEspacios() {
		assertFalse(ValidationUtils.isEmply("  hola  "));
	}



	@Test
	public void testTextoTieneEspaciosConEspacioEnElMedio() {
		assertTrue(ValidationUtils.textoTieneEspacios("hola mundo"));
	}

	@Test
	public void testTextoTieneEspaciosSoloEspacio() {
		assertTrue(ValidationUtils.textoTieneEspacios(" "));
	}

	@Test
	public void testTextoTieneEspaciosSinEspacios() {
		assertFalse(ValidationUtils.textoTieneEspacios("hola"));
	}

	@Test
	public void testTextoTieneEspaciosNulo() {
		assertFalse(ValidationUtils.textoTieneEspacios(null));
	}

	@Test
	public void testTextoTieneEspaciosVacio() {
		assertFalse(ValidationUtils.textoTieneEspacios(""));
	}

	// ---------- soloLetrasYEspacios ----------

	@Test
	public void testSoloLetrasYEspaciosConNombreCompleto() {
		assertTrue(ValidationUtils.soloLetrasYEspacios(EntityHelper.NOMBRE_VALIDO+" "+EntityHelper.NOMBRE_VALIDO));
	}

	@Test
	public void testSoloLetrasYEspaciosSoloLetras() {
		assertTrue(ValidationUtils.soloLetrasYEspacios(EntityHelper.NOMBRE_VALIDO));
	}


	@Test
	public void testSoloLetrasYEspaciosConNumero() {
		assertFalse(ValidationUtils.soloLetrasYEspacios(Utils.generarUsername()));
	}

	@Test
	public void testSoloLetrasYEspaciosConGuionBajo() {
		assertFalse(ValidationUtils.soloLetrasYEspacios(Utils.generarUsername()));
	}

	@Test
	public void testSoloLetrasYEspaciosConSimbolo() {
		assertFalse(ValidationUtils.soloLetrasYEspacios(Utils.generarUsername()));
	}

	@Test
	public void testSoloLetrasYEspaciosSoloNumeros() {
		assertFalse(ValidationUtils.soloLetrasYEspacios("12345"));
	}

	@Test
	public void testSoloLetrasYEspaciosNulo() {
		assertFalse(ValidationUtils.soloLetrasYEspacios(null));
	}

	@Test
	public void testSoloLetrasYEspaciosVacio() {
		assertFalse(ValidationUtils.soloLetrasYEspacios(""));
	}

	@Test
	public void testSoloLetrasYEspaciosEnBlanco() {
		assertFalse(ValidationUtils.soloLetrasYEspacios("     "));
	}


	@Test
	public void testTextoTieneMayusculaAlPrincipio() {
		assertTrue(ValidationUtils.textoTieneMayuscula("Hola"));
	}

	@Test
	public void testTextoTieneMayusculaAlFinal() {
		assertTrue(ValidationUtils.textoTieneMayuscula("holA"));
	}

	@Test
	public void testTextoTieneMayusculaTodoMayusculas() {
		assertTrue(ValidationUtils.textoTieneMayuscula("HOLA"));
	}

	@Test
	public void testTextoTieneMayusculaConEnie() {
		assertTrue(ValidationUtils.textoTieneMayuscula("Ñandu"));
	}

	@Test
	public void testTextoTieneMayusculaSoloMinusculas() {
		assertFalse(ValidationUtils.textoTieneMayuscula("hola"));
	}

	@Test
	public void testTextoTieneMayusculaSoloNumeros() {
		assertFalse(ValidationUtils.textoTieneMayuscula("12345"));
	}

	@Test
	public void testTextoTieneMayusculaNulo() {
		assertFalse(ValidationUtils.textoTieneMayuscula(null));
	}

	@Test
	public void testTextoTieneMayusculaVacio() {
		assertFalse(ValidationUtils.textoTieneMayuscula(""));
	}


	@Test
	public void testTextoTieneMinusculaAlPrincipio() {
		assertTrue(ValidationUtils.textoTieneMinuscula("hOLA"));
	}

	@Test
	public void testTextoTieneMinusculaAlFinal() {
		assertTrue(ValidationUtils.textoTieneMinuscula("HOLa"));
	}

	@Test
	public void testTextoTieneMinusculaTodoMinusculas() {
		assertTrue(ValidationUtils.textoTieneMinuscula("hola"));
	}

	@Test
	public void testTextoTieneMinusculaConTilde() {
		assertTrue(ValidationUtils.textoTieneMinuscula("JOSé"));
	}

	@Test
	public void testTextoTieneMinusculaSoloMayusculas() {
		assertFalse(ValidationUtils.textoTieneMinuscula("HOLA"));
	}

	@Test
	public void testTextoTieneMinusculaSoloNumeros() {
		assertFalse(ValidationUtils.textoTieneMinuscula("12345"));
	}

	@Test
	public void testTextoTieneMinusculaNulo() {
		assertFalse(ValidationUtils.textoTieneMinuscula(null));
	}

	@Test
	public void testTextoTieneMinusculaVacio() {
		assertFalse(ValidationUtils.textoTieneMinuscula(""));
	}


	@Test
	public void testTextoTieneNumeroAlFinal() {
		assertTrue(ValidationUtils.textoTieneNumero("abc1"));
	}

	@Test
	public void testTextoTieneNumeroAlPrincipio() {
		assertTrue(ValidationUtils.textoTieneNumero("1abc"));
	}

	@Test
	public void testTextoTieneNumeroSoloNumeros() {
		assertTrue(ValidationUtils.textoTieneNumero("12345"));
	}

	@Test
	public void testTextoTieneNumeroSinNumeros() {
		assertFalse(ValidationUtils.textoTieneNumero("abc"));
	}

	@Test
	public void testTextoTieneNumeroConSimbolos() {
		assertFalse(ValidationUtils.textoTieneNumero("abc!@#"));
	}

	@Test
	public void testTextoTieneNumeroNulo() {
		assertFalse(ValidationUtils.textoTieneNumero(null));
	}

	@Test
	public void testTextoTieneNumeroVacio() {
		assertFalse(ValidationUtils.textoTieneNumero(""));
	}

}
