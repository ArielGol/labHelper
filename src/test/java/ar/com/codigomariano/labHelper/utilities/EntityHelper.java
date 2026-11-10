package ar.com.codigomariano.labHelper.utilities;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import ar.com.codigomariano.labHelper.domain.Password;
import ar.com.codigomariano.labHelper.domain.Usuario;

public class EntityHelper {
	
	public static final String EMAIL_VALIDO="arielgolub@gmail.com";
	public static final String EMAIL_SINARROBA="arielfolgmail.com";
	public static final String PASSWORD_VALIDO="Ariel336549";
	public static final String NOMBRE_VALIDO="Ariel";
	
	public static final String IMAGEN_ARCHIVO="perfil.png";
	
	private static Random random =new Random(System.currentTimeMillis());
	public static final String EMAIL_SUFIJO="@dummy.com.ar";
	public static final String EMAIL_PREFIJO="mail_";
	
	public static Password crearPasswordValido() {
		return new Password(PASSWORD_VALIDO);
	}
	
	public static Usuario crearUsuarioValido() {
		return new Usuario(NOMBRE_VALIDO,EMAIL_VALIDO,EntityHelper.crearPasswordValido());
	}
	
	public static String generarEmailAleatorio() {
		return EMAIL_PREFIJO+random.nextInt()+EMAIL_SUFIJO;
	}
	
	public static byte[] readFileFromResource(String file) {
		InputStream stream=EntityHelper.class.getClassLoader().getResourceAsStream(file);
		assertNotNull(stream, "No se encontró imagenes/perfil.png en los recursos de test");
		byte[] contenido=null;
		try {
			contenido=stream.readAllBytes();
		} catch (IOException e) {
			fail("El archivo solicitado ["+file+"] no se pudo acceder.");
		}
		return contenido;
	}
	
	
}
