package com.sevillano.Menu;

import com.sevillano.Graph.IGraph;
import com.sevillano.model.BERUTransporte;
import com.sevillano.model.rutas.Ciudad;
import com.sevillano.model.rutas.MapaDeCiudad;

import java.util.ArrayList;

/**
 * Created by tsevillano on 11/28/16.
 */
public class MainMenu extends Menu {


    public MainMenu(){
        this.title = "Menu Principal";
        this.options = new ArrayList<String>(3);
        this.options.add("Empezar Control de Camiones");
        this.options.add("Salir.");
        listOptions();
    }

    @Override
    protected void menu(int opt) {
        switch (opt) {
            case 1: CargaDelMapa(); break;
            case 2: quit(); break;
            default: invalidOption(); break;
        }
    }

    private void CargaDelMapa() {
        clearScreen();
        BERUTransporte company =  BERUTransporte.getInstance();
        company.primeraCarga();
    }


}