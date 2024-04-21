package Task2;

import Task1.*;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class BFS {
    Graph g;

    public BFS(Graph g) {
        this.g = g;
    }

    public boolean BFSConnected(Vertex src, Vertex dst) {
        Queue<Vertex> queue = new ArrayDeque();
        queue.offer(src);
        while (queue.size() > 0) {
            Vertex v = queue.poll();
            if(!v.visited) {
                v.visited = true;
                if (v == dst) {
                    g.setAllUnvisited();
                    return true;
                }
                for (AdjacencyNode n : v.alist) {
                    if (!n.v.visited) {
                        queue.offer(n.v);
                    }
                }
            }
        }
        g.setAllUnvisited();
        return false;
    }

    public void BFSFromTo(Vertex src, Vertex dst){

        Vertex[] prev = _bfs(src);

        ArrayList<Integer> path = _reconstructPath(src, dst, prev);
        if (path != null) {
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.println(path.get(i));
            }
            g.setAllUnvisited();
        }
        else {
            System.out.println("They are not connected");
            g.setAllUnvisited();
        }
    }

    private Vertex[] _bfs(Vertex src) {
        Queue<Vertex> queue = new ArrayDeque<>();
        queue.offer(src);
        src.visited = true;
        Vertex[] prev = new Vertex[g.vertices.length];
        while (queue.size() > 0) {
            Vertex v = queue.poll(); // 0
            for (AdjacencyNode next : v.alist) {
                if (!next.v.visited) {
                    queue.offer(next.v);
                    next.v.visited = true;
                    prev[next.v.nr] = v;
                }
            }
        }
        return prev;
    }

    private ArrayList<Integer> _reconstructPath(Vertex src, Vertex dst, Vertex[] prev) {
        ArrayList<Integer> path = new ArrayList<>(prev.length);

        for (Vertex i = dst; i != null; i = prev[i.nr])
            path.add(i.nr);

        if (path.get(path.size()-1) == src.nr)
            return path;
        else return null;
    }

    public static void main(String[] args) throws Exception {
        UndirectedGraph uGraph = new UndirectedGraph(20);
        uGraph.fillGraph(12);
        uGraph.addE(0, 1, 1);
        uGraph.addE(1, 2, 9);
        uGraph.addE(2, 3, 10);
        uGraph.addE(3, 4, 3);
        uGraph.addE(4, 6, 6);
        uGraph.addE(6, 5, 2);
        uGraph.addE(5, 0, 11);
        uGraph.addE(5, 7, 7);
        uGraph.addE(7, 0, 8);
        uGraph.addE(7, 4, 3);

        // 2nd component
        uGraph.addE(8, 9,  1);
        uGraph.addE(8, 10, 2);
        uGraph.addE(8, 11, 3);
        uGraph.addE(9, 10, 2);
        uGraph.addE(10,11, 1);

        System.out.println("----------\nUNDIRECTED:\n----------");

        int t1 = 1;
        int t2 = 7;
        System.out.println();
        System.out.println(t1 + " CONNECTED " + t2);
        System.out.println(uGraph.BFSConnected(uGraph.vertices[t1], uGraph.vertices[t2]));

        int t3 = 8;
        int t4 = 11;
        System.out.println();
        System.out.println(t3 + " CONNECTED " + t4);
        System.out.println(uGraph.BFSConnected(uGraph.vertices[t3], uGraph.vertices[t4]));

        int t5 = 1;
        int t6 = 11;
        System.out.println();
        System.out.println(t5 + " CONNECTED " + t6);
        System.out.println(uGraph.BFSConnected(uGraph.vertices[t5], uGraph.vertices[t6]));

        System.out.println();
        System.out.println("FROM " + t1 + " TO " + t2);
        uGraph.BFSFromTo(uGraph.vertices[t1], uGraph.vertices[t2]);

        System.out.println();
        System.out.println("FROM " + t3 + " TO " + t4);
        uGraph.BFSFromTo(uGraph.vertices[t3], uGraph.vertices[t4]);

        System.out.println();
        System.out.println("FROM " + t5 + " TO " + t6);
        uGraph.BFSFromTo(uGraph.vertices[t5], uGraph.vertices[t6]);

        DirectedGraph dirGraph = new DirectedGraph(20);
        dirGraph.fillGraph(12);
        dirGraph.addE(0, 1, 1);
        dirGraph.addE(1, 2, 9);
        dirGraph.addE(2, 3, 10);
        dirGraph.addE(3, 4, 3);
        dirGraph.addE(4, 6, 6);
        dirGraph.addE(6, 5, 2);
        dirGraph.addE(5, 0, 11);
        dirGraph.addE(5, 7, 7);
        dirGraph.addE(7, 0, 8);
        dirGraph.addE(7, 4, 3);

        // 2nd component
        dirGraph.addE(8, 9,  1);
        dirGraph.addE(8, 10, 2);
        dirGraph.addE(8, 11, 3);
        dirGraph.addE(9, 10, 2);
        dirGraph.addE(10,11, 1);

        System.out.println("\n\n----------\nDIRECTED:\n----------");
//        BFS bfs2 = new BFS(dirGraph);

        int tt1 = 1;
        int tt2 = 7;
        System.out.println();
        System.out.println(tt1 + " CONNECTED " + tt2);
        System.out.println(dirGraph.BFSConnected(dirGraph.vertices[tt1], dirGraph.vertices[tt2]));

        int tt3 = 8;
        int tt4 = 11;
        System.out.println();
        System.out.println(tt3 + " CONNECTED " + tt4);
        System.out.println(dirGraph.BFSConnected(dirGraph.vertices[tt3], dirGraph.vertices[tt4]));

        int tt5 = 1;
        int tt6 = 11;
        System.out.println();
        System.out.println(tt5 + " CONNECTED " + tt6);
        System.out.println(dirGraph.BFSConnected(dirGraph.vertices[tt5], dirGraph.vertices[tt6]));

        System.out.println();
        System.out.println("FROM " + tt1 + " TO " + tt2);
        dirGraph.BFSFromTo(dirGraph.vertices[tt1], dirGraph.vertices[tt2]);

        System.out.println();
        System.out.println("FROM " + tt3 + " TO " + tt4);
        dirGraph.BFSFromTo(dirGraph.vertices[tt3], dirGraph.vertices[tt4]);

        System.out.println();
        System.out.println("FROM " + tt5 + " TO " + tt6);
        dirGraph.BFSFromTo(dirGraph.vertices[tt5], dirGraph.vertices[tt6]);
    }
}
