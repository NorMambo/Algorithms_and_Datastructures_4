package Task5;

import HelperClasses.MyPriorityQueue;
import Task1.AdjacencyNode;
import Task1.Graph;
import Task1.Vertex;

import java.util.ArrayDeque;
import java.util.Queue;

public class TopSort {
    Graph g;
    private int V_NUM;

    public TopSort(Graph g) {
        this.g = g;
        for (Vertex v : g)
            V_NUM++;
    }

    public void topSort() {

        Queue<Vertex> q = new ArrayDeque<>();
        int counter = 0;

        for (Vertex v : g)
            if (v.inDegree == 0)
                q.add(v);

        while (!q.isEmpty()) {
            Vertex v = q.remove();
            v.topNum = ++counter;  // Assign next number
            for (AdjacencyNode n : v.alist) {
                if (--n.v.inDegree == 0) {
                    q.add(n.v);
                }
            }
        }
    }

    public int V_NUM() {
        return V_NUM;
    }

    public static void main(String[] args) throws Exception {

        GraphBuilder gb = new GraphBuilder(28);
        gb.createGraphFromCSV("src/data.txt");

        TopSort ts = new TopSort(gb.g);
        ts.topSort();

        MyPriorityQueue<Vertex> pq = new MyPriorityQueue<>(ts.V_NUM());
        for (Vertex v : ts.g) {
            pq.insert(v);
        }
        System.out.println("PRINT COURSES IN SORTED ORDER:\n");
        while (!pq.isEmpty()) {
            Vertex v = pq.delMin();
            System.out.println(v.topNum + " - " + v.data);
        }
    }
}
