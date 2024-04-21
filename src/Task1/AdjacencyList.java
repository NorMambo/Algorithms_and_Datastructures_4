package Task1;

import java.util.NoSuchElementException;

public class AdjacencyList implements Iterable<AdjacencyNode> {
    public AdjacencyNode head;

    public int size;
    public AdjacencyList() {}

    public boolean add(Edge e) {
        if (head == null) {
            head = new AdjacencyNode(e.dst);
            head.addE(e);
            size++;
            return true;
        }
        AdjacencyNode current = head;
        return _add(e, current);
    }

    private boolean _add(Edge e, AdjacencyNode current) {
        if (current.next == null) {
            current.next = new AdjacencyNode(e.dst);
            current.next.addE(e);
            size++;
            return true;
        }
        if (current.next.v == e.dst)
            return false;
        return _add(e, current.next);
    }

    public boolean remove(Vertex v) {
        if (head.v == v) {
            head = head.next;
            size--;
            return true;
        }
        AdjacencyNode current = head;
        return _remove(v, current);
    }

    private boolean _remove(Vertex v, AdjacencyNode current) {
        if (current.next == null) {
            return false;
        }
        if (current.next.v == v) {
            AdjacencyNode tmp = current.next;
            current.next = current.next.next;
            tmp.next = null;
            size--;
            return true;
        }
        return _remove(v, current.next);
    }

    // ITERATOR FOR ADJACENCY LIST
    @Override
    public Iterator iterator() {
        return new Iterator();
    }

    private class Iterator implements java.util.Iterator<AdjacencyNode> {

        AdjacencyNode current = head;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public AdjacencyNode next() {
            if (!hasNext())
                throw new NoSuchElementException();
            AdjacencyNode n = current;
            current = current.next;
            return n;
        }
    }

}
