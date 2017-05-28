package com.sevillano.model.vehiculos;

import com.sevillano.model.Carga.Carga;
import com.sevillano.model.chofer.Chofer;
import com.sevillano.model.rutas.Ruta;

/**
 * Created by tsevillano on 11/13/16.
 */
public abstract class Vehiculo {
    public static int CAMIONETA = 1;
    public static int MIONCA = 2;
    public static int MIONCA_CON_ACOPLADO = 3;

    protected Vehiculo nextVehiculo;

    Carga carga;

    int capacidadMaxima;
    double tiempo;
    double consumo;

    double costo;



    Chofer chofer;

    public Vehiculo( double costo, Chofer chofer, int capacidadMaxima) {
        this.costo = costo;
        this.chofer = chofer;
        this.capacidadMaxima = capacidadMaxima;
    }
    public abstract double consumoDeCombustible(Ruta ruta, Carga carga);

    public abstract double tiempo(Ruta rutas, Carga carga);

    public void setCarga(Carga carga) {
        this.carga = carga;
    }

    public void setNextVehiculo(Vehiculo nextVehiculo){
        this.nextVehiculo = nextVehiculo;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public Chofer getChofer() {
        return chofer;
    }

    public Carga getCarga() {
        return carga;
    }

    public double getCosto() {
        return costo;
    }




}
