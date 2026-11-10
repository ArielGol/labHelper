package ar.com.codigomariano.labHelper.helpers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class UtilsTest {
	
	@Test
	public void generarUsernameAleatorio() {
		String username=Utils.generarUsername();
		assertNotNull(username);
		assertTrue(username.startsWith(Utils.DEFAULT_USERNAME_PREFIX));
	}
}
