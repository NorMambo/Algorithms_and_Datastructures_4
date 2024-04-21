package Task4;

import Task1.*;

import java.util.Arrays;
import java.util.PriorityQueue;

// NOTE: Morgan said we are allowed to use Priority Queue from java.util
public class Dijkstra {
    Graph g;
    double[] dist_to;
    Edge[] edge_to;
    public PriorityQueue<Pair> pq;

    public Dijkstra(DirectedGraph g, int start) {
        this.g = g;
        this.edge_to = new Edge[g.vertices.length];
        this.dist_to = new double[g.vertices.length];

        Arrays.fill(edge_to, null);
        Arrays.fill(dist_to,Double.POSITIVE_INFINITY);

        dist_to[start] = 0.0;
        pq = new PriorityQueue<>();
        pq.offer(new Pair(0.0, start));

        while (!pq.isEmpty()) {
            Pair t = pq.poll();
            int v = t.vertexNr;
            for (Edge e : g.vertices[v]) {
                _relax(e);
            }
        }
    }

    public void _relax(Edge e) {
        if (dist_to[e.dst.nr] > dist_to[e.src.nr] + e.weight) {
            dist_to[e.dst.nr] = dist_to[e.src.nr] + e.weight;
            edge_to[e.dst.nr] = e;

            boolean found = false;
            Pair pair = null;
            for (Pair t : pq) {
                if (t.vertexNr == e.dst.nr) {
                    pair = t;
                    found = true;
                    break;
                }
            }

            if (found) {
                pq.remove(pair);
            }
            pq.add(new Pair(dist_to[e.dst.nr], e.dst.nr));
        }
    }


    public static void main(String[] args) throws Exception {

        DirectedGraph dg = new DirectedGraph(10);
        dg.fillGraph(10);
        dg.addE(0, 1, 1);
        dg.addE(1, 5, 1);
        dg.addE(5, 6, 1);
        dg.addE(6, 9, 1);
        dg.addE(0, 4, 7);
        dg.addE(4, 9, 20);
//
        int start = 0;
//
        Dijkstra dij = new Dijkstra(dg, start);
        System.out.println("DIJKSTRA");
        for (int i = 0; i < dij.dist_to.length; i++) {
            System.out.println(start + " - " + i + " = " + dij.dist_to[i]);
        }
    }

}
