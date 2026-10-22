package ar.com.codigomariano.labHelper.helpers;


import java.util.Random;

public class Utils {
	
	private final static String DEFAULT_USERNAME_PREFIX="user";
	
	private final static Random r=new Random(System.currentTimeMillis());
	
	/**
	 * El username generado se compone del prefijo "user"
	 * número aleatorio entero.
	 * @return String user123
	 */
	public static String generarUsername() {
		return DEFAULT_USERNAME_PREFIX+r.nextInt();
	}
	

}
