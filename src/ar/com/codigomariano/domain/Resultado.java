package ar.com.codigomariano.domain;

import java.time.LocalDate;

import ar.com.codigomariano.domain.Muestra;
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.domain.Ensayo;

public class Resultado {

    private Integer id;
    private Muestra muestra;
    private Ensayo ensayo;
    private Usuario analista;
    private String valorObtenido;
    private String unidad;
    private LocalDate fecha;
    private Boolean aprobado;
    private String observaciones;

    public Resultado(Integer id, Muestra muestra, Ensayo ensayo, Usuario analista, String valorObtenido, String unidad, LocalDate fecha, Boolean aprobado, String observaciones) {
        this.id = id;
        this.muestra = muestra;
        this.ensayo = ensayo;
        this.analista = analista;
        this.valorObtenido = valorObtenido;
        this.unidad = unidad;
        this.fecha = LocalDate.now();
        this.aprobado = aprobado;
        this.observaciones = observaciones;
    }
    
     @Override
    public String toString() {
        return ensayo.getNombre()
                + " = "
                + valorObtenido
                + " "
                + ensayo.getUnidad();
    }


}