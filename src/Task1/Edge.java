package Task1;

public class Edge implements Comparable<Edge>{
    public Vertex src;
    public Vertex dst;
    public double weight;

    public Edge(Vertex src, Vertex dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public void printEdge() {
        System.out.println("<" + this.src.nr + "," + this.dst.nr + ">");
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight < o.weight)
            return -1;
        else if (this.weight > o.weight)
            return 1;
        else
            return 0;

    }
}
