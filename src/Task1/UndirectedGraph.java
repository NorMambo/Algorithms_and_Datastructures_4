package Task1;


public class UndirectedGraph extends Graph{
    public UndirectedGraph(int maxNrVertices) {
        super(maxNrVertices);
    }

    public void fillGraph(int limit) throws Exception {
        if (limit <= maxNrVertices) {
            for (int i = 0; i < limit; i++) {
                Vertex v = new Vertex(i);
                v.degree = 0;
                vertices[i] = v;
                nrV++;
            }
        } else {
            throw new Exception("limit is high than max nr of vertices");
        }
    }

    public void addE(int v1, int v2, double weight) {
        _addE(v1, v2, weight);
    }

    public void addDefE(int v1, int v2) {
        _addE(v1,v2, 1.0);
    }

    public void _addE(int v1, int v2, double weight) {

        Edge e1 = new Edge(vertices[v1], vertices[v2], weight);
        Edge e2 = new Edge(vertices[v2], vertices[v1], weight);
        // ADD ADJACENCY 1 - 2
        boolean one = vertices[v1].addAdj(e1);

        // ADD ADJACENCY 2 - 1
        boolean two = vertices[v2].addAdj(e2);

        // INCREASE DEGREES IF ADDING BOTH ADJACENCEIS WAS SUCCESSFUL
        if (one && two) {
            vertices[v1].degree++;
            vertices[v2].degree++;
        }
    }

    public void remove_edge (int v1, int v2) {
        // REMOVE ADJACENCY 1 - 2
        Vertex ve2 = vertices[v2];
        boolean adjRemoved1 = vertices[v1].removeAdj(ve2);

        // REMOVE ADJACENCY 2 - 1
        Vertex ve1 = vertices[v1];
        boolean adjRemoved2 = vertices[v2].removeAdj(ve1);

        // DECREASE DEGREES IF REMOVING BOTH ADJACENCEIS WAS SUCCESSFUL
        if (adjRemoved1 && adjRemoved2) {
            ve1.degree--;
            ve2.degree--;
        }
    }

    public int nrE() {
        int count = 0;
        for (Vertex v : this)
            for (AdjacencyNode a : v.alist)
                count++;
        return count/2;
    }
}
