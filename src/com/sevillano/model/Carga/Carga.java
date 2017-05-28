package com.sevillano.model.Carga;

import com.sevillano.model.rutas.Ciudad;
import com.sevillano.model.rutas.Ruta;

/**
 * Created by tsevillano on 11/27/16.
 */
public class Carga {
    Ruta hojaDeRuta;
    Ciudad salida;
    Ciudad destino;
    Double peso;
    String estado;

    public Double getTiempoMaximo() {
        return tiempoMaximo;
    }

    Double tiempoMaximo;

    public Carga(Ciudad salida, Ciudad destino, Double peso, Double tiempoMaximo) {
        this.salida = salida;
        this.destino = destino;
        this.peso = peso;
        this.tiempoMaximo = tiempoMaximo;
    }

    public Ciudad getSalida() {
        return salida;
    }

    public Ciudad getDestino() {
        return destino;
    }

    public Double getPeso() {
        return peso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Ruta getHojaDeRuta() {
        return hojaDeRuta;
    }

    public void setHojaDeRuta(Ruta hojaDeRuta) {
        this.hojaDeRuta = hojaDeRuta;
    }

}
