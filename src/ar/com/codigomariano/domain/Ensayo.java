package ar.com.codigomariano.domain;

import ar.com.codigomariano.enums.TipoEnsayo;

public class Ensayo {

    private Integer id;
    private String nombre;
    private String unidad;
    private String descripcion;
    private TipoEnsayo tipo;

    public Ensayo(Integer id, String nombre, String unidad, String descripcion, TipoEnsayo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.unidad = unidad;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

     public String getNombre() {
        return nombre;
    }

    public String getUnidad() {
        return unidad;
    }

}