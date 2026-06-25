package ar.com.codigomariano.domain;

import java.util.Set;
import ar.com.codigomariano.enums.Rol;


public class Usuario {
	private Integer id;
    private String nombreUsuario;
    private String nombreCompleto;
    private String email;
    private String password;
    private int[] roles;

    Usuario(Integer id, String user,String nombre, String email,String password){
        this.id=id;
        this.nombreUsuario=user;
        this.nombreCompleto=nombre;
        this.email=email;
        this.password=password;
        roles=new int[Rol.values().length];
    }

}
