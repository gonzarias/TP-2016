package com.sevillano.model.vehiculos;

import com.sevillano.model.Carga.Carga;
import com.sevillano.model.chofer.Chofer;
import com.sevillano.model.rutas.Ruta;

/**
 * Created by tsevillano on 11/13/16.
 */
public class CamionConAcoplado extends Vehiculo {
    public CamionConAcoplado(double costo, Chofer chofer)
    {
        super(costo, chofer,20000);
    }

    @Override
    public double tiempo(Ruta ruta, Carga carga) {
        double tiempo = 1;
        tiempo = tiempo + ruta.kmBuenEstado / 90;
        tiempo = tiempo + ruta.kmMalEstado / 70;
        if(carga.getPeso() >  10000) {
            tiempo = tiempo + (30 * tiempo / 100);
        }
        return tiempo;
    }

    @Override
    public double consumoDeCombustible(Ruta ruta, Carga carga) {
        double comboustible;
        comboustible = (1 * ruta.kmTotales() * (carga.getPeso() / 1000)) + (0.3 * ruta.kmTotales());
        return comboustible;
    }

}
