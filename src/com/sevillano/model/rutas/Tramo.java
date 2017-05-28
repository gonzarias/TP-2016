package com.sevillano.model.rutas;

/**
 * Created by tsevillano on 11/13/16.
 */
public class Tramo {
    Ciudad salida;
    Ciudad llegada;
    double KmBuenEstado;
    double KmMalEstado;


    public Tramo(Ciudad salida, Ciudad llegada, Double kmBuenEstado, Double kmMalEstado) {
        this.salida = salida;
        this.llegada = llegada;
        KmBuenEstado = kmBuenEstado;
        KmMalEstado = kmMalEstado;
    }

    public Ciudad getSalida() {
        return salida;
    }

    public Ciudad getLlegada() {
        return llegada;
    }

    public double getKmBuenEstado() {
        return KmBuenEstado;
    }

    public void setKmBuenEstado(Double kmBuenEstado) {
        KmBuenEstado = kmBuenEstado;
    }

    public double getKmMalEstado() {
        return KmMalEstado;
    }

    public void setKmMalEstado(Double kmMalEstado) {
        KmMalEstado = kmMalEstado;
    }

    public double getKmTotales() {
        return getKmBuenEstado() + getKmMalEstado();
    }
}
