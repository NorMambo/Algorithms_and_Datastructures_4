package Task1;

import HelperClasses.GraphPrints;
import Task2.BFS;
import Task2.DFS;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Graph implements Iterable<Vertex>, Cloneable{

    public Vertex[] vertices;
    public int maxNrVertices;
    public int nrV;

    public Graph (int maxNrVertices) {
        this.vertices = new Vertex[maxNrVertices];
        this.maxNrVertices = maxNrVertices;
        this.nrV = 0;
    }


    // ---------------------- GENERAL ----------------------
    public void setAllUnvisited() {
        for (Vertex v : this) {
            v.visited = false;
        }
    }
    public Vertex contains(Vertex v) {
        for (Vertex x : this) {
            if (x.getData()==null)
                return null;
            if (x.getData().equals(v.getData()))
                return x;
        }
        return null;
    }
    public int nrV() {
        return nrV;
    }



    // --------------------- PRINTOUTS ---------------------
    public void printVerticesDegrees() {
        new GraphPrints(this).printVerticesDegrees();
    }
    public void printVerticesWithAdjacency() {
        new GraphPrints(this).printVerticesWithAdjacency();
    }
    public void printAllEdges() {
        new GraphPrints(this).printAllEdges();
    }

    public void printVerticesWithAdjacencyDATA() {
        new GraphPrints(this).printVerticesWithAdjacencyDATA();
    }



    // ---------------- TASK 2 (DFS & BFS) -----------------
    public ArrayList<Vertex> DFSFindAllInComponent(Vertex src) {
        return new DFS(this).DFSFindAll(src, true);
    }
    public boolean DFSConnected(Vertex src, Vertex dst) {
        return new DFS(this).DFSConnected(src,dst);
    }
    public void DFSFromTo(Vertex src, Vertex dst) {
        ArrayList<Integer> path = new DFS(this).DFSFromTo(src, dst);
        if (path != null) {
            for (int v : path) {
                System.out.println(v);
            }
        } else {
            System.out.println("They are not connected");
        }
    }
    public boolean BFSConnected(Vertex src, Vertex dst) {
        return new BFS(this).BFSConnected(src, dst);
    }
    public void BFSFromTo(Vertex src, Vertex dst){
        new BFS(this).BFSFromTo(src, dst);
    }


    // --------------- ITERATOR FOR VERTICES ---------------
    @Override
    public Iterator iterator() {
        return new Iterator();
    }
    private class Iterator implements java.util.Iterator<Vertex>{
        int pos = 0;

        @Override
        public boolean hasNext() {
            return pos < vertices.length && vertices[pos] != null;
        }

        @Override
        public Vertex next() {
            Vertex v = vertices[pos];
            pos++;
            return v;
        }
    }

}
