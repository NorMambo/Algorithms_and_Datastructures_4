package Task2;

import Task1.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class DFS {
    private Graph g;
    public DFS(Graph g) {
        this.g = g;
    }

    // ----------------- CONNECTED ------------------
    public boolean DFSConnected(Vertex src, Vertex dst) { // sets all to unvisited when done
        Stack<Vertex> stack = new Stack<>();
        stack.push(src);
        while (stack.size() > 0) {
            Vertex curr = stack.pop();
            if(!curr.visited) {
                curr.visited = true;
                if (curr == dst) {
                    g.setAllUnvisited();
                    return true;
                }
                for (AdjacencyNode n : curr.alist) {
                    if (!n.v.visited) {
                        stack.push(n.v);
                    }
                }
            }
        }
        g.setAllUnvisited();
        return false;
    }

    // ----------------- FIND ALL ------------------
    private void _DFSFindAll(Vertex curr, ArrayList<Vertex> comp) {
        if (!curr.visited) {
            comp.add(curr);
        }
        curr.visited = true;
        for (AdjacencyNode n : curr.alist) {
            if (!n.v.visited) {
                _DFSFindAll(n.v, comp);
            }
        }
    }
    public ArrayList<Vertex> DFSFindAll(Vertex src, boolean setUnvisited) { // used in KRUSKAL to find connected components
        ArrayList<Vertex> comp = new ArrayList<>();
        _DFSFindAll(src, comp);
        if (comp.size() > 0) {
            if (setUnvisited)
                g.setAllUnvisited();
            return comp;
        }
        if (setUnvisited)
            g.setAllUnvisited();
        return null;
    }

    // ----------------- FROM TO ------------------
    public ArrayList<Integer> DFSFromTo(Vertex src, Vertex dst) {
        if (DFSConnected(src, dst)) {
            int start = src.nr;
            boolean[] marked = new boolean[g.vertices.length];
            int[] edge_to = new int[g.vertices.length];
            Arrays.fill(marked, false);
            Arrays.fill(edge_to, 0);
            _dfs(start, marked, edge_to);
            ArrayList<Integer> path = new ArrayList<>();
            int x = dst.nr;
            while (x != src.nr) {
                path.add(0, x);
                x = edge_to[x];
            }
            path.add(0, src.nr);
            return path;
        } else {
            return null;
        }
    }
    private void _dfs(int v, boolean[] marked, int[] edge_to) {
        marked[v] = true;
        for (AdjacencyNode n : g.vertices[v].alist) {
            if (!marked[n.v.nr]) {
                _dfs(n.v.nr,  marked, edge_to);
                edge_to[n.v.nr] = v;
            }
        }
    }

    // ----------------- MAIN ------------------

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
        System.out.println("\nDFS FIND ALL: COMPONENT 1 (not required for task)");
        ArrayList<Vertex> comp1 = uGraph.DFSFindAllInComponent(uGraph.vertices[1]); // 1 is in 1 component
        for (Vertex v : comp1)
            System.out.println(v.nr);

        System.out.println("\nDFS FIND ALL: COMPONENT 2 (not required for task)");
        ArrayList<Vertex> comp2 = uGraph.DFSFindAllInComponent(uGraph.vertices[8]); // 8 is in the other component
        for (Vertex v : comp2)
            System.out.println(v.nr);

        int t1 = 1;
        int t2 = 7;
        System.out.println();
        System.out.println(t1 + " CONNECTED " + t2);
        System.out.println(uGraph.DFSConnected(uGraph.vertices[t1], uGraph.vertices[t2]));

        int t3 = 8;
        int t4 = 11;
        System.out.println();
        System.out.println(t3 + " CONNECTED " + t4);
        System.out.println(uGraph.DFSConnected(uGraph.vertices[t3], uGraph.vertices[t4]));

        int t5 = 1;
        int t6 = 11;
        System.out.println();
        System.out.println(t5 + " CONNECTED " + t6);
        System.out.println(uGraph.DFSConnected(uGraph.vertices[t5], uGraph.vertices[t6]));

        System.out.println();
        System.out.println("FROM " + t1 + " TO " + t2);
        uGraph.DFSFromTo(uGraph.vertices[t1], uGraph.vertices[t2]);

        System.out.println();
        System.out.println("FROM " + t3 + " TO " + t4);
        uGraph.DFSFromTo(uGraph.vertices[t3], uGraph.vertices[t4]);

        System.out.println();
        System.out.println("FROM " + t5 + " TO " + t6);
        uGraph.DFSFromTo(uGraph.vertices[t5], uGraph.vertices[t6]);

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
        System.out.println("\nDFS FIND ALL: COMPONENT 1 (not required for task)");
        ArrayList<Vertex> comp3 = dirGraph.DFSFindAllInComponent(dirGraph.vertices[1]); // 1 is in 1 component
        for (Vertex v : comp3)
            System.out.println(v.nr);

        System.out.println("\nDFS FIND ALL: COMPONENT 2 (not required for task)");
        ArrayList<Vertex> comp4 = dirGraph.DFSFindAllInComponent(dirGraph.vertices[8]); // 8 is in the other component
        for (Vertex v : comp4)
            System.out.println(v.nr);

        int tt1 = 1;
        int tt2 = 7;
        System.out.println();
        System.out.println(tt1 + " CONNECTED " + tt2);
        System.out.println(dirGraph.DFSConnected(dirGraph.vertices[tt1], dirGraph.vertices[tt2]));

        int tt3 = 8;
        int tt4 = 11;
        System.out.println();
        System.out.println(tt3 + " CONNECTED " + tt4);
        System.out.println(dirGraph.DFSConnected(dirGraph.vertices[tt3], dirGraph.vertices[tt4]));

        int tt5 = 1;
        int tt6 = 11;
        System.out.println();
        System.out.println(tt5 + " CONNECTED " + tt6);
        System.out.println(dirGraph.DFSConnected(dirGraph.vertices[tt5], dirGraph.vertices[tt6]));

        System.out.println();
        System.out.println("FROM " + tt1 + " TO " + tt2);
        dirGraph.DFSFromTo(dirGraph.vertices[tt1], dirGraph.vertices[tt2]);

        System.out.println();
        System.out.println("FROM " + tt3 + " TO " + tt4);
        dirGraph.DFSFromTo(dirGraph.vertices[tt3], dirGraph.vertices[tt4]);

        System.out.println();
        System.out.println("FROM " + tt5 + " TO " + tt6);
        dirGraph.DFSFromTo(dirGraph.vertices[tt5], dirGraph.vertices[tt6]);

    }
}
