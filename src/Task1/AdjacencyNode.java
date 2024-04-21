package Task1;

public class AdjacencyNode {

    public AdjacencyNode next;
    public Vertex v;
    public Edge e;
    public AdjacencyNode(Vertex v) {
        this.v = v;
    }

    public void addE(Edge e) {
        this.e = e;
    }

}
