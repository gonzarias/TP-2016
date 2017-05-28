package com.sevillano.model.rutas;

import com.sevillano.Menu.OperationsMenu;
import com.sevillano.model.Carga.Carga;
import com.sevillano.model.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tsevillano on 11/27/16.
 */
public class Ciudad {
    String name;
    ArrayList<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    ArrayList<Tramo> tramos = new ArrayList<>();

    public Ciudad(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addVehiculos(Vehiculo vehiculos) {
        this.vehiculos.add(vehiculos);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void addTramo(Tramo t) {
        this.tramos.add(t);
    }

    public Tramo searchtramo(Ciudad c) {
        Tramo rtn = null;
        for (Tramo t : tramos) {
            if (t.getLlegada().equals(c)) {
                rtn = t;
            }
        }
        return rtn;
    }

    public void takeThisCharger(Carga carga) {
        ArrayList<Vehiculo> vehiculosSele = new ArrayList<Vehiculo>();
        Scanner scanner = new Scanner(System.in);

        boolean can = false;
        MapaDeCiudad mapa = MapaDeCiudad.getInstance();
        int idCiudad = mapa.getIndex(this);
        int idCiudadDestino = mapa.getIndex(carga.getDestino());
        List<Ciudad> ciudadesQueVisita = mapa.determinarCaminoCorto(idCiudad, idCiudadDestino);
        Ruta hojaDeRuta = new Ruta();
        for (int i = 0; i < ciudadesQueVisita.size(); i++) {
            int e = i + 1;
            try {
                if (e < ciudadesQueVisita.size()) {
                    Tramo t = ciudadesQueVisita.get(i).searchtramo(ciudadesQueVisita.get(e));
                    hojaDeRuta.addTramo(t);
                }
            } catch (IndexOutOfBoundsException err) {
                System.out.println("Error: Array fuera de rango");
            }
            ;
        }
        /* I - Sysout para Debug */
       /* System.out.println("Verificacion: imprimo tramos");

        for (Tramo t: hojaDeRuta.rutas) {
            System.out.println( " Salida:    " + t.getSalida().getName() +
                                " Llegada:   " + t.getLlegada().getName() +
                                " KM Buenos: " + t.getKmBuenEstado() +
                                " KM Malos:  " + t.getKmMalEstado());

        }*/

        System.out.println("-Itinerario: KM Buenos " + hojaDeRuta.getKmBuenEstado() +
                " KM Malos: " + hojaDeRuta.getKmMalEstado() +
                " KM Totales: " + hojaDeRuta.kmTotales());

        /* F - Sysout para Debug */

        // Realizo una preseleccion de los Vehiculos que cumplen con los requisitos de Peso y Tiempo
        for (Vehiculo v : this.vehiculos) {

            if (v.getCapacidadMaxima() >= carga.getPeso()) {

             /*   System.out.println(" Vehiculo Capacidad: " + v.getCapacidadMaxima());
                System.out.println(" Tiempo vehiculo: " + v.tiempo(hojaDeRuta, carga) +
                        " Tiempo Carga: " + carga.getTiempoMaximo() +
                        " Consumo: " + v.consumoDeCombustible(hojaDeRuta, carga));*/
                if (v.tiempo(hojaDeRuta, carga) <= carga.getTiempoMaximo()) {
                    /*System.out.println( "-Vehiculo Preseleccionado-");*/
                    vehiculosSele.add(v);

                }

            }
        }

        Double consumoAux = 99999999.;
        Vehiculo vaux = null;

        for (Vehiculo vBest : vehiculosSele) {
            if (vBest.consumoDeCombustible(hojaDeRuta, carga) <= consumoAux) {
                // Se queda con el que mejor consumo tenga
                if (vBest.consumoDeCombustible(hojaDeRuta, carga) < consumoAux) {
                    vaux = vBest;
                    consumoAux = vBest.consumoDeCombustible(hojaDeRuta, carga);

                } else { // Ante igual consumo priorizo el tiempo
                    if (vBest.tiempo(hojaDeRuta, carga) < vaux.tiempo(hojaDeRuta, carga)) {
                        vaux = vBest;
                    }
                }

            }

        }

        System.out.println("Consumo: " + vaux.consumoDeCombustible(hojaDeRuta, carga)+" litros" +
                " Tiempo de Trayecto: " + vaux.tiempo(hojaDeRuta, carga) +" horas" +
                " Sueldo Chofer: " + vaux.getChofer().getSueldo() + " ARS"+
                " Capacidad Maxima: " + vaux.getCapacidadMaxima() +" kg.");

        // Setea la carga en el Vehiculo y la despacha
        carga.setEstado("Despachado");
        carga.setHojaDeRuta(hojaDeRuta);
        vaux.setCarga(carga);

        System.out.println("La carga fue despachada con exito. Precione Enter para volver al Menu principal ");

        scanner.nextLine();
        new OperationsMenu();


    }
}





        


