package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="USUARIOS")
public class Usuario extends Persistible {
	
	@Column(name ="EMAIL")
	private String email;
	
	@OneToOne(cascade =CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name= "PASSWORD_ID")
    private Password contrasenia;
	
	@ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinTable(
	    name = "ROLES_USUARIOS",
	    joinColumns = @JoinColumn(name = "USUARIO_ID", referencedColumnName = "ID"),
	    inverseJoinColumns = @JoinColumn(name = "ROL_ID", referencedColumnName = "ID")
	)
    private Set<Rol> roles;
    
    @Column(name= "NOMBRE_COMPLETO")
    private String nombreCompleto;
    
    @Column(name= "NOMBRE_USUARIO")
    private String nombreUsuario;
    
    @Column(name="FECHA_CREACION")
    private LocalDateTime fechaCreacion;
    
    //Sólo para Hibernate
    Usuario(){
    	
    }

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
