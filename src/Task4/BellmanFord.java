package Task4;

import Task1.*;

import java.util.*;

public class BellmanFord {

    Graph g;
    double[] dist_to;
    Edge[] edge_to;
    boolean[] onqueue;
    final double MAX_DIST = 100000;
    ArrayList<Integer> list;

    public BellmanFord(DirectedGraph g, int start) {
        this.g = g;
        this.dist_to = new double[g.vertices.length];
        this.edge_to = new Edge[g.vertices.length];
        this.onqueue = new boolean[g.vertices.length];
        list = new ArrayList<>();

        Arrays.fill(dist_to,MAX_DIST);
        Arrays.fill(edge_to, null);
        Arrays.fill(onqueue, false);

        dist_to[start] = 0.0;
        onqueue[start] = true;
        list.add(start);

        while (!list.isEmpty()) {
            int v = list.remove(list.size()-1);
            onqueue[v] = false;
            _relax(v);
        }
    }

    private void _relax(int v) {
        for (Edge e : g.vertices[v]) {
            int w = e.dst.nr;
            if (dist_to[w] > dist_to[v] + e.weight) {
                dist_to[w] = dist_to[v] + e.weight;
                edge_to[w] = e;
                if (!onqueue[w]) {
                    list.add(0, w);
                    onqueue[w] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        DirectedGraph g2 = new DirectedGraph(7);
        g2.fillGraph(7);

        g2.addE(0, 1, 1);
        g2.addE(0, 3, 2);
        g2.addE(3, 2, 3);
        g2.addE(1, 2, 1);
        g2.addE(2, 4, -7);
        g2.addE(2, 5, -8);
        g2.addE(5, 6, 8);
        g2.addE(4, 6, 8);

        int start = 0;

        BellmanFord bf = new BellmanFord(g2, start);

        for (int i = 0; i < bf.dist_to.length; i++) {
            System.out.println(start + " - " + i + " = " + bf.dist_to[i]);
        }
    }
}
