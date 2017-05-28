package com.sevillano.model.rutas;

import com.sevillano.Graph.WeightedGraph;

import java.util.List;
import java.util.Scanner;

/**
 * Created by tsevillano on 11/28/16.
 */
public class MapaDeCiudad extends WeightedGraph<Ciudad> {
    private static MapaDeCiudad instance;
    private Scanner scanner = new Scanner(System.in);


    public static MapaDeCiudad getInstance(){
        if(instance == null){
            instance = new MapaDeCiudad();
            instance.addNewsCity();

        }
        return instance;
    }

    private void  addNewsCity(){
        int firstPosition = instance.getSize();
        while (true) {
            System.out.println("Ingrese el nombre de las ciudades: (quite for exit)");
            String name = scanner.nextLine();
            if(name.equals("quite")){
                break;
            }
            instance.addVertex(new Ciudad(name));
        }
        instance.addRelacionCiduades();
    }

    public void newCity(){
        System.out.println("Ingrese el nombre de la nueva ciudad: ");
        String name2 = scanner.nextLine();
        Ciudad newCity = new Ciudad(name2);
        instance.addVertex(newCity);
        instance.addRelationOneCity(instance.getIndex(newCity));
    }

    protected void addRelationOneCity(int index) {
        for(int i = 0; i < index ; i++){
            this.addRelation(index, i);
        }
    }


    protected void addRelacionCiduades(){
        for (int i = 0; i < instance.getSize(); i++) {
            for(int e = i+1; e < instance.getSize(); e++){
                addRelation(i, e);
            }
        }
    }

    private void addRelation(int i, int e) {

        Scanner in=new Scanner(System.in);

        String yesOrNo = "";
        System.out.println(instance.getVertex(i).getName() + " Esta relacionada con "
                + instance.getVertex(e).getName() + " (yes/no)");

        yesOrNo = in.nextLine();

        // Bug doble ciudad enviada a consolta
        do {

            try {

                if (yesOrNo.startsWith("Y") || yesOrNo.startsWith("y")) {
                    Tramo tramo = this.generateNewTramo(i, e);

                    double kmTotales = tramo.getKmTotales();
                    instance.addEdge(i, e, kmTotales);
                }
            } catch (java.util.InputMismatchException err) //error con otros caracteres
            {
                System.out.println("Error: valor no válido. ");
                yesOrNo = "";
            }
        }while(yesOrNo == "" );

        in.reset();
    }

    private Tramo generateNewTramo(int i, int e) {
        System.out.println("Km en buen estado: ");
        double kmb = scanner.nextDouble();
        System.out.println("Km en mal estado: ");
        double kmm = scanner.nextDouble();
        Tramo t =new Tramo(instance.getVertex(e),instance.getVertex(i),kmb, kmm);
        instance.getVertex(e).addTramo(t);
        t = new Tramo(instance.getVertex(i),instance.getVertex(e),kmb, kmm);
        instance.getVertex(i).addTramo(t);
        return t;
    }

    public List<Ciudad> determinarCaminoCorto(int v, int i){
       return instance.getShortestPath(v).getPath(i);
    }
}
