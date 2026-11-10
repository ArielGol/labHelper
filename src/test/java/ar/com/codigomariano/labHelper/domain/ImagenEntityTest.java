package ar.com.codigomariano.labHelper.domain;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.io.InputStream;

import org.junit.jupiter.api.Test;

import ar.com.codigomariano.labHelper.helpers.ValidationUtils;
import ar.com.codigomariano.labHelper.utilities.EntityHelper;

public class ImagenEntityTest extends BaseEntityTest<Imagen> {

	@Override
	protected Imagen crearEntidadValida() {
		byte[] imagen =obtenerImagenDePerfil();
		return new Imagen(EntityHelper.IMAGEN_ARCHIVO,null,imagen);
	}

	@Override
	protected void validarEntidadExitosa(Imagen entidad) {
		assertNotNull(entidad.getNombre());
		assertNotNull(entidad.getContenido());
		assertTrue(ValidationUtils.textoTieneMenosCaracteres(entidad.getNombre(), Imagen.NOMBRE_MAX));
		
	}
	
	@Test
	public void testCrearImagenConArchivoRealGuardaElContenido() {
		byte[] bytes=obtenerImagenDePerfil();
		Imagen imagen=new Imagen(EntityHelper.IMAGEN_ARCHIVO, bytes);

		assertEquals(EntityHelper.IMAGEN_ARCHIVO, imagen.getNombre());
		assertArrayEquals(bytes, imagen.getContenido());
	}

	@Test
	public void testImagenDePerfilNoEstaVacia() {
		byte[] bytes=obtenerImagenDePerfil();

		assertTrue(bytes.length>0);
	}


	@Test
	public void testCrearImagenNombreNulo() {
		byte[] bytes=obtenerImagenDePerfil();
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen(null, bytes));
		assertEquals(Imagen.NOMBRE_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearImagenNombreVacio() {
		byte[] bytes=obtenerImagenDePerfil();
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen("", bytes));
		assertEquals(Imagen.NOMBRE_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearImagenNombreEnBlanco() {
		byte[] bytes=obtenerImagenDePerfil();
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen("     ", bytes));
		assertEquals(Imagen.NOMBRE_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearImagenNombreMuyLargo() {
		byte[] bytes=obtenerImagenDePerfil();
		String nombreLargo="a".repeat(Imagen.NOMBRE_MAX+1);
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen(nombreLargo, bytes));
		assertEquals(String.format(Imagen.NOMBRE_MAX_LONGITUD, Imagen.NOMBRE_MAX), ex.getMessage());
	}

	@Test
	public void testCrearImagenNombreEnElLimiteMaximo() {
		byte[] bytes=obtenerImagenDePerfil();
		String nombreLimite="a".repeat(Imagen.NOMBRE_MAX);
		Imagen imagen=new Imagen(nombreLimite, bytes);

		assertEquals(nombreLimite, imagen.getNombre());
	}


	@Test
	public void testCrearImagenContenidoNulo() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen("perfil.png", null));
		assertEquals(Imagen.CONTENIDO_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearImagenContenidoVacio() {
		IllegalArgumentException ex=assertThrows(IllegalArgumentException.class, ()-> new Imagen("perfil.png", new byte[0]));
		assertEquals(Imagen.CONTENIDO_OBLIGATORIO, ex.getMessage());
	}

	@Test
	public void testCrearImagenContenidoDeUnByte() {
		Imagen imagen=new Imagen(EntityHelper.IMAGEN_ARCHIVO, new byte[]{1});

		assertEquals(1, imagen.getContenido().length);
	}


	@Test
	public void testCrearImagenSinContentTypeUsaPng() {
		Imagen imagen=new Imagen(EntityHelper.IMAGEN_ARCHIVO, obtenerImagenDePerfil());

		assertEquals("image/png", imagen.getContentType());
	}

	@Test
	public void testCrearImagenConContentTypeExplicito() {
		Imagen imagen=new Imagen(EntityHelper.IMAGEN_ARCHIVO, "image/jpeg", obtenerImagenDePerfil());

		assertEquals("image/jpeg", imagen.getContentType());
	}

	@Test
	public void testCrearImagenConContentTypeNulo() {
		Imagen imagen=new Imagen(EntityHelper.IMAGEN_ARCHIVO, null, obtenerImagenDePerfil());

		assertNull(imagen.getContentType());
	}

	private byte[] obtenerImagenDePerfil() {
		return EntityHelper.readFileFromResource("imagenes/perfil.png");
	}

}
