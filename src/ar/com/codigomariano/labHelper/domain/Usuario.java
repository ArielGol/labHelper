package ar.com.codigomariano.labHelper.domain;

import java.util.HashSet;
import java.util.Set;

import ar.com.codigomariano.labHelper.enums.Rol;


public class Usuario extends Persistible {
    private String nombreUsuario;
    private String email;
    private String password;
    private Set<Rol> roles;
    private String nombreCompleto;

    public Usuario(Long id,String userName,String nombre,String email,String password){
    	super(id);
    	this.nombreUsuario=userName;
    	this.nombreCompleto=nombre;
    	this.email=email;
    	this.password=password;
    	this.roles=new HashSet<>();
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
    
    public boolean autenticarUser(String password) {
    	return this.password.equals(password);
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
