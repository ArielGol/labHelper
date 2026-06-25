package ar.com.codigomariano.domain;

import ar.com.codigomariano.enums.TipoCliente;

public class Cliente {
    
    private Long codigo;
    private String nombre;
    private String email;
    private TipoCliente tipo;

    public Cliente(Long codigo, String nombre, String email, TipoCliente tipo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

}