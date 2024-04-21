package Task6;

import Task1.Edge;
import Task1.UndirectedGraph;
import Task1.Vertex;

import java.util.ArrayList;

public class BridgeFinder {
    public UndirectedGraph g;

    public BridgeFinder(UndirectedGraph g) {
        this.g = g;
    }

    public void bridgeEdges() {
        ArrayList<Edge> bridges = _bridges();
        if (bridges != null) {
            System.out.println("Bridge edges are: ");
            for (Edge e : bridges) {
                e.printEdge();
            }
        } else {
            System.out.println("There are no bridges.");
        }

    }

    private ArrayList<Edge> _bridges() {
        ArrayList<Edge> bridges = new ArrayList<>();
        ArrayList<Vertex> comp = g.DFSFindAllInComponent(g.vertices[0]);
        int originalComp = comp.size();
        int newComp;
        for (Vertex v : g) {
            int count = 0;
            for (Edge e : v) {
                if (count == v.alist.size)
                    break;
                g.remove_edge(e.src.nr, e.dst.nr);
                ArrayList<Vertex> list = g.DFSFindAllInComponent(g.vertices[0]);
                g.addDefE(e.src.nr, e.dst.nr);
                newComp = list.size();
                if (newComp != originalComp) {
                    bridges.add(e);
                }
                count++;
            }
        }
        if (bridges.size() > 0) {
            return bridges;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        UndirectedGraph ug = new UndirectedGraph(10);
        ug.fillGraph(10);

        ug.addDefE(0, 1);
        ug.addDefE(1, 2);
        ug.addDefE(2, 3);
        ug.addDefE(3, 0);
        ug.addDefE(3, 4);
        ug.addDefE(4, 5);
        ug.addDefE(4, 6);
        ug.addDefE(5, 6);

        ug.printVerticesWithAdjacency();

        BridgeFinder bf = new BridgeFinder(ug);
        bf.bridgeEdges();
    }
}
