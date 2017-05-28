package com.sevillano.model;

import com.sevillano.Graph.IGraph;
import com.sevillano.Menu.OperationsMenu;
import com.sevillano.model.Carga.Carga;
import com.sevillano.model.chofer.Chofer;
import com.sevillano.model.rutas.Ciudad;
import com.sevillano.model.rutas.MapaDeCiudad;
import com.sevillano.model.vehiculos.CamionConAcoplado;
import com.sevillano.model.vehiculos.Camiones;
import com.sevillano.model.vehiculos.Camionetas;
import com.sevillano.model.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Scanner;


public class BERUTransporte {
    public static BERUTransporte instance;
    IGraph<Ciudad> mapa;
    private Scanner scanner = new Scanner(System.in);

    private BERUTransporte() {
    }

    public static BERUTransporte getInstance(){
        if(instance == null){
            instance = new BERUTransporte();
        }
        return instance;
    }

    public  void primeraCarga(){
        System.out.println("Para empezar vamos a necesitar crear un par de ciudades!");
        mapa = MapaDeCiudad.getInstance();
        this.createRandomVehiclesInCity();
        new OperationsMenu();
    }

    public void addCity(){
        this.mapa.newCity();
        new OperationsMenu();
    }

    protected void createRandomVehiclesInCity(){
        Chofer choferRandom = new Chofer(323232);
        for (int i = 0; i < mapa.getSize(); i++){
            Vehiculo camioneta = new Camionetas(3000, choferRandom);
            Vehiculo camiones = new Camiones(3000, choferRandom);
            Vehiculo CamionConAcoplado = new CamionConAcoplado(3000, choferRandom);
            mapa.getVertex(i).addVehiculos(camioneta);
            mapa.getVertex(i).addVehiculos(camiones);
            mapa.getVertex(i).addVehiculos(CamionConAcoplado);
        }
    }

    public void addVehicle() {
        System.out.println("Primero necesitamos los datos de un chofer!");
        System.out.println("¿Cuales el el sueldo? (Numero entero)");
        int sueldo = scanner.nextInt();
        Chofer chofer = new Chofer(sueldo);
        System.out.println("¿Cuales el el costo? (Numero entero)");
        int costo = scanner.nextInt();
        System.out.println("Achora que tipo de Vehiculo queres?");
        ArrayList<String> options = new ArrayList<String>();
        options.add("Camion Con Acoplado");
        options.add("Camiones");
        options.add("Camioneta");
        Vehiculo newVehiculo = this.listOptions(options,costo, chofer);
        this.addVehicleToCity(newVehiculo);
        new OperationsMenu();

    }

    private void addVehicleToCity(Vehiculo v) {
        int numeroDeCiudad = getNumberOfCity("A que ciudad lo queres agregar?");
        if(mapa.getSize() > numeroDeCiudad){
            mapa.getVertex(numeroDeCiudad).addVehiculos(v);
        }
    }

    private int getNumberOfCity(String title) {
        System.out.println(title);
        for (int i = 0; i < mapa.getSize(); i++){
            System.out.println(i + ". " + mapa.getVertex(i).getName());
        }
        return scanner.nextInt();
    }

    protected Vehiculo listOptions(ArrayList<String> options, int costo, Chofer chofer){
        int optNumber = 1;
        Vehiculo newVeiculo = null;
        for (String option : options) {
            System.out.println(optNumber+". "+option);
            optNumber++;
        }

        switch (scanner.nextInt()) {
            case 1: newVeiculo = new CamionConAcoplado(costo,chofer); break;
            case 2: newVeiculo = new Camiones(costo, chofer); break;
            case 3: newVeiculo = new Camionetas(costo, chofer); break;

        }
        return newVeiculo;
    }

    public void addCarga() {
        System.out.println("Cual es el peso de las carga?");
        Double peso = scanner.nextDouble();

        System.out.println("Cual es el tiempo máximo de entrega?");
        Double tiempo = scanner.nextDouble();

        int numberOfCity = this.getNumberOfCity("De que ciudad lo queres despachar?");
        Ciudad ciduadDeparture = mapa.getVertex(numberOfCity);

        numberOfCity = this.getNumberOfCity("De que ciudad lo queres recibir?");
        Ciudad ciduadarrival = mapa.getVertex(numberOfCity);

        Carga carga = new Carga(ciduadDeparture,ciduadarrival,peso,tiempo);

        ciduadDeparture.takeThisCharger(carga);


    }

    public void cost(){
        Scanner scan = new Scanner(System.in);
        double consumo = 0;
        double sueldos = 0;
        double costoFijo = 0;

        for (Ciudad c:mapa.getVertices()) {
            for (Vehiculo v:c.getVehiculos()) {
                // Si la ciudad de Salida de la carga es igual a la ciudad que estoy analizando
                if (v.getCarga() != null) {
                    if (v.getCarga().getHojaDeRuta() != null) {
                        if (v.getCarga().getSalida().getName() == c.getName()) {
                            consumo += (v.consumoDeCombustible(v.getCarga().getHojaDeRuta(), v.getCarga()));
                            sueldos += (v.getChofer().getSueldo());
                            costoFijo += v.getCosto();

                        }
                    }
                }

            }

        }

        System.out.println("--------------Informe de Costos-------------------");
        System.out.format( "-Sueldos------           %1$016.2f ARS ---- %n",sueldos );
        System.out.format( "-Combustible--           %1$016.2f ARS ---- %n",consumo );
        System.out.format( "-Costos Fijos-           %1$016.2f ARS ---- %n",costoFijo );
        System.out.println("--------------------------------------------------");

        System.out.println("Precione Enter para volver al Menu principal ");

        scan.nextLine();
        new OperationsMenu();

    }
}
