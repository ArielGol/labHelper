package ar.com.codigomariano.labHelper.domain;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import ar.com.codigomariano.labHelper.helpers.Utils;
import ar.com.codigomariano.labHelper.helpers.ValidationUtils;
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
	
	public final static String EMAIL_OBLIGATORIO="El email es obligatorio.";
	public final static String EMAIL_MAX_LONGITUD="El email no debe superar los %d caracteres.";
	public final static String EMAIL_MIN_LONGITUD="El email debe superar los %d caracteres.";
	public final static int EMAIL_MAX=75;
	public final static int EMAIL_MIN=15;
	public final static String EMAIL_FORMATO_INVALID="El email no tiene formato válido.";
	
	public final static String NOMBRE_OBLIGATORIO="El nombre completo es obligatorio.";
	public final static String NOMBRE_MAX_LONGITUD="El nombre completo no debe superar los %d caracteres.";
	public final static String NOMBRE_MIN_LONGITUD="El nombre completo debe tener al menos %d caracteres.";
	public final static int NOMBRE_MAX=100;
	public final static int NOMBRE_MIN=3; 
	
	public final static String USERNAME_OBLIGATORIO="El nombre de usuario es obligatorio.";
	public final static String USERNAME_MAX_LONGITUD="El nombre de usuario no debe superar los %d caracteres.";
	public final static String USERNAME_MIN_LONGITUD="El nombre de usuario debe tener al menos %d caracteres.";
	public final static String USERNAME_CON_ESPACIOS="El nombre de usuario no debe contener espacios.";
	public final static int USERNAME_MAX=30;
	public final static int USERNAME_MIN=4;
	
	public final static String NOMBRE_SOLO_LETRAS="El nombre completo solo puede contener letras y espacios.";

	public final static String ROL_OBLIGATORIO="El rol no puede ser nulo.";
	
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
    public Usuario(String nombre,String email,Password password) {
    	this(Utils.generarUsername(),nombre,email,password);
    }

    public Usuario(String userName,String nombre,String email,Password password){
    	setNombreUsuario(userName);
    	setNombreCompleto(nombre);
    	setEmail(email);
    	this.contrasenia=password;
    	this.roles=new HashSet<>();
    	this.fechaCreacion=LocalDateTime.now();
    }
    
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getEmail() {
		return email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}
    public Set<Rol> getRoles(){
    	return roles;
    }

	public Password getContrasenia() {
		return contrasenia;
	}

	public LocalDateTime getFechaCreacion() {
		return fechaCreacion;
	}
	public void setEmail(String email) {
		if(ValidationUtils.isEmply(email)) {
			throw new IllegalArgumentException(EMAIL_OBLIGATORIO);
		} else if(ValidationUtils.textoTieneMasCaracteres(email, EMAIL_MAX)) {
			throw new IllegalArgumentException(String.format(EMAIL_MAX_LONGITUD,EMAIL_MAX));
		} else if(ValidationUtils.textoTieneMenosCaracteres(email, EMAIL_MIN)) {
			throw new IllegalArgumentException(String.format(EMAIL_MIN_LONGITUD,EMAIL_MIN));
		} else if(!ValidationUtils.esValidoEmail(email)) {
			throw new IllegalArgumentException(EMAIL_FORMATO_INVALID);
		}
		this.email = email;
	}
	public void setNombreCompleto(String nombreCompleto) {
		if(ValidationUtils.isEmply(nombreCompleto)) {
			throw new IllegalArgumentException(NOMBRE_OBLIGATORIO);
		} else if(ValidationUtils.textoTieneMasCaracteres(nombreCompleto, NOMBRE_MAX)) {
			throw new IllegalArgumentException(String.format(NOMBRE_MAX_LONGITUD,NOMBRE_MAX));
		} else if(ValidationUtils.textoTieneMenosCaracteres(nombreCompleto, NOMBRE_MIN)) {
			throw new IllegalArgumentException(String.format(NOMBRE_MIN_LONGITUD,NOMBRE_MIN));
		} else if(!ValidationUtils.soloLetrasYEspacios(nombreCompleto)) {
			throw new IllegalArgumentException(NOMBRE_SOLO_LETRAS);
		}
		this.nombreCompleto = nombreCompleto;
	}
	public void setNombreUsuario(String nombreUsuario) {
		if(ValidationUtils.isEmply(nombreUsuario)) {
			throw new IllegalArgumentException(USERNAME_OBLIGATORIO);
		} else if(ValidationUtils.textoTieneMasCaracteres(nombreUsuario, USERNAME_MAX)) {
			throw new IllegalArgumentException(String.format(USERNAME_MAX_LONGITUD,USERNAME_MAX));
		} else if(ValidationUtils.textoTieneMenosCaracteres(nombreUsuario, USERNAME_MIN)) {
			throw new IllegalArgumentException(String.format(USERNAME_MIN_LONGITUD,USERNAME_MIN));
		} else if(nombreUsuario.contains(" ")) {
			throw new IllegalArgumentException(USERNAME_CON_ESPACIOS);
		} else if(ValidationUtils.textoTieneEspacios(nombreUsuario)) {
			throw new IllegalArgumentException(USERNAME_CON_ESPACIOS);
		}
		this.nombreUsuario = nombreUsuario;
	}
	public void agregarRol(Rol rol) {
		if(rol == null) {
			throw new IllegalArgumentException(ROL_OBLIGATORIO);
		}
    	this.roles.add(rol);
    }
    public void eliminarRol(Rol rol) {
    	this.roles.remove(rol);
    }

    
    public boolean tieneRol(Rol rol) {
    	return this.roles.contains(rol);
    }


    

}
