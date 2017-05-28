package com.sevillano.model.rutas;

import com.sevillano.Graph.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tsevillano on 11/27/16.
 */
public class Grafos {
    public static void main(String[] args) {
        // Prueba carga
        Ciudad bsas = new Ciudad("Buenos Aires");
        Ciudad mdq = new Ciudad("mdq");
        Ciudad laplata = new Ciudad("la plata");
        Ciudad rosario = new Ciudad("rosario");
        Ciudad bariloche = new Ciudad("bariloche");
        Ciudad sanrario = new Ciudad("Santa rosa");
        Ciudad sanRafael = new Ciudad("San rrafael");
        Ciudad salta = new Ciudad("salta");
        Ciudad jujuy = new Ciudad("jujuy");
        Ciudad chaco = new Ciudad("chaco");
        Ciudad chubut = new Ciudad("chubut");
        Ciudad tdf = new Ciudad("tierra del feuglo");

        /*Ciudad[] vertices = {bsas, mdq, laplata,
               rosario, bariloche, sanrario, sanRafael,
                salta,jujuy, chaco, chubut,
                tdf};*/

        ArrayList<Ciudad> vertices = new ArrayList<Ciudad>();
        vertices.add(bsas);
        vertices.add(mdq);
        vertices.add(laplata);
        vertices.add(rosario);

        //System.out.println(vertices[0]);


       /* int[][] edges = {
                {0, 1}, {0, 3}, {0, 5},
                {1, 0}, {1, 2}, {1, 3},
                {2, 1}, {2, 3}, {2, 4}, {2, 10},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 2}, {4, 3}, {4, 5}, {4, 7}, {4, 8}, {4, 10},
                {5, 0}, {5, 3}, {5, 4}, {5, 6}, {5, 7},
                {6, 5}, {6, 7},
                {7, 4}, {7, 5}, {7, 6}, {7, 8},
                {8, 4}, {8, 7}, {8, 9}, {8, 10}, {8, 11},
                {9, 8}, {9, 11},
                {10, 2}, {10, 4}, {10, 8}, {10, 11},
                {11, 8}, {11, 9}, {11, 10}
        };*/

       // Agregar las relaciones entre los nodos

        ArrayList<WeightedEdge> listEdge = new ArrayList<WeightedEdge>();

        listEdge.add(new WeightedEdge(0,1,20.00)); // u,v,peso
        listEdge.add(new WeightedEdge(0,2,10.00)); // u,v,peso
        listEdge.add(new WeightedEdge(1,3,25.00)); // u,v,peso
        listEdge.add(new WeightedEdge(2,3,10.00)); // u,v,peso

        // Se crea un grafo pesado
        //IGraph<Ciudad> graph = new UnweightedGraph<Ciudad>(edges, vertices);
        WeightedGraph<Ciudad> grafoPesado = new WeightedGraph<Ciudad>(listEdge,vertices);

        // Se calcula el costo desde el nodo 0 hasta el nodo 3
        System.out.println("Costo: " + grafoPesado.getShortestPath(0).getCost(3));

        // Se imprimen las ciudades del camino calculado 
        for (Ciudad c :grafoPesado.getShortestPath(0).getPath(3)){
                    System.out.println("Ciudad: " + c.getName() + "/n");
              }

    }
}
