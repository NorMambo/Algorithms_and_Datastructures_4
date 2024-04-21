package HelperClasses;

import Task1.Edge;
import Task1.Graph;
import Task1.Vertex;

public class GraphPrints {
    Graph g;

    public GraphPrints(Graph g) {
       this.g = g;
    }

    public void printVertices() {
        System.out.println("--------------------");
        System.out.println("VERTICES");
        System.out.println("--------------------");
        for (Vertex v : g) {
            System.out.println(v.nr);
        }
    }

    public void printVerticesDegrees() {
        System.out.println("--------------------");
        System.out.println("VERTICES DEGREE");
        System.out.println("--------------------");
        for (Vertex v : g) {
            if (v.inDegree == -1 && v.outDegree == -1)
                System.out.println(v.nr + " - degree: " + ": " + v.degree);
            else {
                System.out.println(v.nr + " - In deg: " + v.inDegree + " | Out deg: " + v.outDegree);
            }
        }
        System.out.println();
    }

    public void printVerticesWithAdjacency() {
        System.out.println("--------------------");
        System.out.println("ADJACENCY LIST");
        System.out.println("--------------------");
        for (Vertex V : g) { // for Vertex in Graph
            if (V != null) {
                System.out.print(V.nr);
            for (Edge adjE : V) // for every edge adjacent to Vertex
                System.out.print(" -> " + adjE.dst.nr); // destination edge
            System.out.println();
            }
        }
        System.out.println();
    }

    public void printVerticesWithAdjacencyDATA() {
        System.out.println("--------------------");
        System.out.println("ADJACENCY LIST DATA");
        System.out.println("--------------------");
        for (Vertex V : g) { // for Vertex in Graph
            if (V != null) {
                System.out.print(V.getData());
                for (Edge adjE : V) // for every edge adjacent to Vertex
                    System.out.print(" -> " + adjE.dst.getData()); // destination edge
                System.out.println();
            }
        }
        System.out.println();
    }

    public void printAllEdges() {
        System.out.println("--------------------");
        System.out.println("EDGES BETWEEN");
        System.out.println("--------------------");
        for (Vertex V : g) {
            for (Edge adjE : V)
                System.out.println("<" +adjE.src.nr + "," + adjE.dst.nr+ ">");
        }
        System.out.println();
    }
}
