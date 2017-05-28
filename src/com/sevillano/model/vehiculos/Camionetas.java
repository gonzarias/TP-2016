package com.sevillano.model.vehiculos;

import com.sevillano.model.Carga.Carga;
import com.sevillano.model.chofer.Chofer;
import com.sevillano.model.rutas.Ruta;

/**
 * Created by tsevillano on 11/13/16.
 */
public class Camionetas extends Vehiculo {
    public Camionetas(double costo, Chofer chofer)
    {
        super(costo, chofer, 2500);
    }

    @Override
    public double tiempo(Ruta ruta, Carga carga) {
        if(carga.getPeso() <= 1800){
            return ruta.kmTotales() / 100;
        }else{
            return ruta.kmTotales() / 90;
        }
    }

    @Override
    public double consumoDeCombustible(Ruta ruta, Carga carga) {
        if(carga.getPeso() <= 1500){
            return 0.15 * ruta.kmTotales();
        }else{
            return 0.2 * ruta.kmTotales();
        }
    }
}
