package com.sevillano.Menu;

import com.sevillano.model.BERUTransporte;

import java.util.ArrayList;

/**
 * Created by tsevillano on 11/28/16.
 */
public class OperationsMenu extends Menu {
    BERUTransporte berut = BERUTransporte.getInstance();;

    public OperationsMenu() {
        this.title = "Que desea hacer?";
        this.options = new ArrayList<String>(5);
        this.options.add("Agregar Una carga.");
        this.options.add("Agregar un vehiculo carga.");
        this.options.add("Calcular Costos.");
        this.options.add("add city");
        this.options.add("Salir.");
        listOptions();
    }

    @Override
    protected void menu(int opt) {
        switch (opt) {
            case 1: addCarga(); break;
            case 2: addVehiculo(); break;
            case 3: cost(); break;
            case 4: addCity(); break;
            default: invalidOption(); break;
        }
    }

    private void addCity() {
        berut.addCity();
    }

    private void cost() { berut.cost();     
    }

    private void addVehiculo() {
        berut.addVehicle();
    }

    private void addCarga() {
        berut.addCarga();
    }
    
}
