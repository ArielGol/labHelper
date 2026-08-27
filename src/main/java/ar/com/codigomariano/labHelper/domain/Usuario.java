package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


public class Usuario extends Persistible {
    private String email;
    private Password contrasenia;
    private Set<Rol> roles;
    private String nombreCompleto;
    private String nombreUsuario;
    private LocalDateTime fechaCreacion;

    public Usuario(String userName,String nombre,String email,Password password){
    	this.nombreUsuario=userName;
    	this.nombreCompleto=nombre;
    	this.email=email;
    	this.contrasenia=password;
    	this.roles=new HashSet<>();
    	this.fechaCreacion=LocalDateTime.now();
    }
    
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void agregarRol(Rol rol) {
    	this.roles.add(rol);
    }
    public void eliminarRol(Rol rol) {
    	this.roles.remove(rol);
    }
    public Set<Rol> getRoles(){
    	return roles;
    }
    
    public boolean autenticarUser(Password password) {
    	return this.contrasenia.equals(password);
    }
    
    public boolean tieneRol(Rol rol) {
    	return this.roles.contains(rol);
    }

	public String getEmail() {
		return email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}
    
    

}
