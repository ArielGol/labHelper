package ar.com.codigomariano.labHelper.helpers;

public class ValidationUtils {
	
	
	/**
	 * Valida si el email del usuario contiene un "@".
	 * @param email
	 * @return true o false
	 */
	public static boolean esValidoEmail(String email) {
		boolean resultado=false;
		if(email!=null && !email.isBlank()) {
			resultado=email.contains("@");
		}
		return resultado;
	}
	/**
	 * Valida si el texto contiene más de cierta cantidad de caracteres.
	 * @param texto
	 * @param cantidad
	 * @return
	 */
	public static boolean textoTieneMasCaracteres(String texto, int cantidad) {
		boolean resultado=false;
		if(texto!=null && !texto.isBlank()) {
			resultado=texto.length()>cantidad;
		}
		return resultado;
	}
	/**
	 * Valida si el texto contiene menor de cierta cantidad de caracteres
	 * @param texto
	 * @param cantidad
	 * @return
	 */
	public static boolean textoTieneMenosCaracteres(String texto,int cantidad) {
		boolean resultado=false;
		if(texto!=null&& !texto.isBlank()) {
			resultado=texto.length()<cantidad;
		}
		return resultado;
	}
	
	public static boolean isEmply(String texto) {
		return (texto==null||texto.isBlank());
	}
	
	/**
	 * Valida si el texto contiene espacios.
	 * @param texto
	 * @return true si tiene al menos un espacio
	 */
	public static boolean textoTieneEspacios(String texto) {
		boolean resultado=false;
		if(texto!=null) {
			resultado=texto.contains(" ");
		}
		return resultado;
	}

	/**
	 * Valida si el texto contiene solo letras y espacios.
	 * @param texto
	 * @return true si todos los caracteres son letras o espacios
	 */
	public static boolean soloLetrasYEspacios(String texto) {
		boolean resultado=false;
		if(texto!=null && !texto.isBlank()) {
			resultado=true;
			for(int i=0; i<texto.length(); i++) {
				char c=texto.charAt(i);
				if(!Character.isLetter(c) && c!=' ') {
					resultado=false;
				}
			}
		}
		return resultado;
	}
	/**
	 * Valida si el texto contiene al menos una letra mayúscula.
	 * @param texto
	 * @return true si tiene alguna mayúscula
	 */
	public static boolean textoTieneMayuscula(String texto) {
		boolean resultado=false;
		if(texto!=null) {
			for(int i=0; i<texto.length(); i++) {
				if(Character.isUpperCase(texto.charAt(i))) {
					resultado=true;
				}
			}
		}
		return resultado;
	}

	/**
	 * Valida si el texto contiene al menos una letra minúscula.
	 * @param texto
	 * @return true si tiene alguna minúscula
	 */
	public static boolean textoTieneMinuscula(String texto) {
		boolean resultado=false;
		if(texto!=null) {
			for(int i=0; i<texto.length(); i++) {
				if(Character.isLowerCase(texto.charAt(i))) {
					resultado=true;
				}
			}
		}
		return resultado;
	}

	/**
	 * Valida si el texto contiene al menos un número.
	 * @param texto
	 * @return true si tiene algún dígito
	 */
	public static boolean textoTieneNumero(String texto) {
		boolean resultado=false;
		if(texto!=null) {
			for(int i=0; i<texto.length(); i++) {
				if(Character.isDigit(texto.charAt(i))) {
					resultado=true;
				}
			}
		}
		return resultado;
	}
	
	
}
