package com.sevillano.model.vehiculos;

import com.sevillano.model.Carga.Carga;
import com.sevillano.model.chofer.Chofer;
import com.sevillano.model.rutas.Ruta;

/**
 * Created by tsevillano on 11/13/16.
 */
public class Camiones extends Vehiculo {
    public Camiones(double costo, Chofer chofer)
    {
        super( costo, chofer, 6000);
    }

    @Override
    public double tiempo(Ruta ruta, Carga carga) {
        double tiempo = 0.25;
        tiempo = tiempo + ruta.kmBuenEstado / 100;
        if(carga.getPeso() <=  3000){
            tiempo = tiempo + ruta.kmMalEstado / 75;
        }else if( carga.getPeso() > 5000){
            tiempo = tiempo + ruta.kmMalEstado / 60;
        }else{
            tiempo = tiempo + ruta.kmMalEstado / 70;
        }
        return tiempo;
    }

    @Override
    public double consumoDeCombustible(Ruta ruta, Carga carga) {
        double combustible;
        combustible = 1 * ruta.kmBuenEstado * (carga.getPeso() / 1500);
        combustible = 1.5 * ruta.kmMalEstado * (carga.getPeso() / 1500);
        return combustible;
    }
}
