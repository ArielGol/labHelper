package ar.com.codigomariano.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.codigomariano.domain.Cliente;
import ar.com.codigomariano.domain.Resultado;
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.enums.EstadoMuestra;



public class Muestra {

    private String codigo;
    private String tipo;
    private Cliente cliente;
    private LocalDate fechaIngreso;
    private LocalDate fechaSalida;
    private String observaciones;
    private EstadoMuestra estado;
    private Usuario responsable;
    private List<Resultado> resultados;

    public Muestra(String codigo, String tipo, Cliente cliente, LocalDate fechaIngreso, LocalDate fechaSalida, String observaciones, EstadoMuestra estado, Usuario responsable) {
        this.codigo = codigo;
        this.tipo = tipo;
        this.cliente = cliente;
        this.fechaIngreso = LocalDate.now();
        this.fechaSalida = fechaSalida;
        this.observaciones = observaciones;
        this.estado = EstadoMuestra.RECIBIDA;
        this.responsable = responsable;
        this.resultados = new ArrayList<>();
    }

     public String getCodigo() {
        return codigo;
    }

    public EstadoMuestra getEstado() {
        return estado;
    }

    public void setEstado(EstadoMuestra estado) {
        this.estado = estado;
    }

    public List<Resultado> getResultados() {
        return resultados;
    }

    @Override
    public String toString() {

        return "Codigo: " + codigo
                + " | Cliente: " + cliente.getNombre()
                + " | Tipo: " + tipo
                + " | Estado: " + estado;
    }
}


}