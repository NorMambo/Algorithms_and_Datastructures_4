package Task1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Vertex implements Iterable<Edge>, Comparable<Vertex> {

    public AdjacencyList alist;

    public int nr;
    public int topNum;
    public int degree = -1;
    public int inDegree = -1;
    public int outDegree = -1;
    public String data;

    public boolean visited;

    public Vertex(int nr) {
        this.nr = nr;
        this.topNum = nr;
        visited = false;
        alist = new AdjacencyList();
    }

    public Vertex(int nr, String data) {
        this.nr = nr;
        this.data = data;
        this.visited = false;
        this.alist = new AdjacencyList();
    }

    public void setInitialDegDir() {
        inDegree = outDegree = 0;
    }

    public boolean addAdj(Edge e) {
        return alist.add(e);
    }

    public boolean removeAdj(Vertex v) {
        return alist.remove(v);
    }

    public String getData() {
        return data;
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.topNum, o.topNum);
    }

    // ITERATOR FOR EDGES
    @Override
    public Iterator<Edge> iterator() {
        return new EdgeIterator();
    }
    private class EdgeIterator implements Iterator<Edge> {

        AdjacencyNode current = alist.head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Edge next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Edge e = current.e;
            current = current.next;
            return e;
        }
    }
}
