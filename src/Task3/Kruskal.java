package Task3;

import HelperClasses.MyPriorityQueue;
import HelperClasses.UnionFind;
import Task1.*;
import Task2.DFS;

import java.util.ArrayList;

public class Kruskal {
    ArrayList<ArrayList<Edge>> msts;
    UnionFind uf;
    Graph g;

    public Kruskal(UndirectedGraph g) {

        this.g = g;
        msts = new ArrayList<>();
        uf = new UnionFind(g.vertices.length);
    }

    public void printEdge(Edge e) {
        System.out.println("<" + e.src.nr + "," + e.dst.nr + "> - " + e.weight);
    }

    // Connected components:
    public ArrayList<ArrayList<Vertex>> FindCC() {
        ArrayList<ArrayList<Vertex>> listOfComponents = new ArrayList<>();
        DFS dfs = new DFS(g);
        for (Vertex v : g) {
            ArrayList<Vertex> comp = dfs.DFSFindAll(v, false);
            if (comp != null) {
                listOfComponents.add(comp);
            }
        }
        g.setAllUnvisited();
        return listOfComponents;
    }

    // Store edges in Priority queues
    public ArrayList<MyPriorityQueue<Edge>> storeEdges() {

        ArrayList<ArrayList<Vertex>> connComp = FindCC();

        ArrayList<MyPriorityQueue<Edge>> pqList = new ArrayList<>();

        for (ArrayList<Vertex> comp : connComp) {

            MyPriorityQueue<Edge> pq = new MyPriorityQueue<>(comp.size());

            if (comp.size() == 1) { // created a case for the trivial case of single Vertex tree
                Vertex single = comp.get(0);
                pq.insert(new Edge(single, single, -1.0));
                pqList.add(pq);
            } else {
                for (Vertex v : comp) {
                    for (AdjacencyNode n : v.alist) {
                        if (!n.v.visited) {
                            v.visited = true;
                            pq.insert(n.e);
                        }
                    }

                }
                pqList.add(pq);
            }
        }
        g.setAllUnvisited();
        return pqList;
    }

    // Find Minimum Spanning Trees
    public void kruskal() {

        ArrayList<MyPriorityQueue<Edge>> pqs = storeEdges();

        for (MyPriorityQueue<Edge> pq : pqs) {
            ArrayList<Edge> mst = new ArrayList<>();
            if (pq.size()==1) {
                Edge e = pq.delMin();
                mst.add(e);
                msts.add(mst);
            } else {
                while (!pq.isEmpty() && mst.size() < g.vertices.length - 1) {
                    Edge e = pq.delMin();
                    int src = e.src.nr;
                    int dst = e.dst.nr;
                    if (!uf.connected(src, dst)) {
                        uf.union(src, dst);
                        mst.add(e);
                    }
                }
                msts.add(mst);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        UndirectedGraph ug = new UndirectedGraph(20);
        ug.fillGraph(15);

        // TEST 1
        // 1st component
        ug.addE(0, 1, 1);
        ug.addE(1, 2, 9);
        ug.addE(2, 3, 10);
        ug.addE(3, 4, 3);
        ug.addE(4, 6, 6);
        ug.addE(6, 5, 2);
        ug.addE(5, 0, 11);
        ug.addE(5, 7, 7);
        ug.addE(7, 0, 8);
        ug.addE(7, 4, 3);

        // 2nd component
        ug.addE(9, 10, 2);
        ug.addE(10,11, 1);

        // 3rd component
        ug.addE(13, 14, 9);

        // TEST 2
//        //1st component
//        ug.addE(1, 2, 1);
//        ug.addE(2, 3, 9);
//        ug.addE(3, 4, 10);
//        ug.addE(4, 6, 3);
//        ug.addE(6, 2, 1);
//
//        // 2nd component
//        ug.addE(0, 5, 6);
//        ug.addE(5, 7, 2);
//        ug.addE(7, 0, 11);
//
//        // 3rd component
//        ug.addE(8, 9, 7);
//        ug.addE(9, 10, 8);
//        ug.addE(10, 11, 3);
//        ug.addE(11, 8, 1);
//        ug.addE(8, 10, 8);

        Kruskal k = new Kruskal(ug);
        k.kruskal();
        int count = 1;
        for (ArrayList<Edge> list : k.msts) {
            System.out.println("-----------\nComponent " + count + "\n-----------");
            for (Edge e : list)
                k.printEdge(e);
            count++;
            System.out.println();
        }
    }
}
