package Task1;

public class DirectedGraph extends Graph {
    public DirectedGraph(int maxNrVertices) {
        super(maxNrVertices);
    }

    public void fillGraph(int limit) throws Exception {
        if (limit <= maxNrVertices) {
            for (int i = 0; i < limit; i++) {
                Vertex v = new Vertex(i);
                v.inDegree = v.outDegree = 0;
                vertices[i] = v;
                nrV++;
            }
        } else {
            throw new Exception("limit is high than max nr of vertices");
        }
    }

    public void addV (Vertex v) {
        v.inDegree = v.outDegree = 0;
        vertices[v.nr] = v;
        nrV++;
    }

    public void addDefE(int src, int dst) {
        _addE(src, dst, 1.0);
    }

    public void addE(int src, int dst, double weight) {
        _addE(src,dst,weight);
    }

    private void _addE(int src, int dst, double weight) {
        Edge e = new Edge(vertices[src], vertices[dst], weight);

        // INCREASE DEGREES IF ADDING ADJACENCY IS SUCCESSFUL
        if (vertices[src].addAdj(e)) {
            vertices[src].outDegree++;
            vertices[dst].inDegree++;
        }
    }

    public void remove_edge(int src, int dst) {
        Vertex dstV = vertices[dst];
        // DECREASE DEGREES IF ADDING ADJACENCY IS SUCCESSFUL
        if (vertices[src].removeAdj(dstV)) {
            Vertex srcV = vertices[src];
            srcV.outDegree--;
            dstV.inDegree--;
        }
    }

    public int nrE() {
        int count = 0;
        for (Vertex v : this) {
            for (AdjacencyNode a : v.alist)
                count++;
        }
        return count;
    }
}
