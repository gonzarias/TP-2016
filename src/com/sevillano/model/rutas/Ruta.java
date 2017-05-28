package com.sevillano.model.rutas;

import java.util.ArrayList;

/**
 * Created by tsevillano on 11/13/16.
 */
public class Ruta  {
    public ArrayList<Tramo>  rutas = new ArrayList<>();
    public double kmBuenEstado;
    public double kmMalEstado;

    public Ruta(ArrayList<Tramo> rutas) {
        for(Tramo tramo : rutas){
            this.kmMalEstado = this.kmMalEstado + tramo.getKmMalEstado();
            this.kmBuenEstado = this.kmBuenEstado + tramo.getKmBuenEstado();
        }
        this.rutas = rutas;
    }

    public Ruta(){

    }

    public double getKmBuenEstado() {
        return kmBuenEstado;
    }

    public double getKmMalEstado() {
        return kmMalEstado;
    }

    public double kmTotales(){
        return this.getKmMalEstado() + this.getKmBuenEstado();
    }

    public void addTramo(Tramo tramo){
        this.rutas.add(tramo);
        this.kmBuenEstado = this.kmBuenEstado + tramo.getKmBuenEstado();
        this.kmMalEstado = this.kmMalEstado + tramo.getKmMalEstado();
    }
}
