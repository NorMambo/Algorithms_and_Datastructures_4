package Task1;

public class Test {
    public static void main(String[] args) throws Exception {

        System.out.println("\nDIRECTED:\n");
        DirectedGraph g1 = new DirectedGraph(100);

        g1.fillGraph(15);

        g1.addDefE(1, 2);
        g1.addDefE(1, 6);
        g1.addDefE(1, 4);
        g1.addDefE(3, 2);
        g1.addDefE(4, 2);
        g1.addDefE(4, 6);
        g1.addDefE(5, 2);
        g1.addDefE(5, 6);
        g1.addDefE(6, 2);

        g1.addDefE(7, 8);
        g1.addDefE(8, 9);
        g1.addDefE(9, 7);

        g1.printVerticesWithAdjacency();
        System.out.println("Nr. edges: " + g1.nrE());
        System.out.println("Nr. vertices: " + g1.nrV() + "\n");
        g1.printVerticesDegrees();
        g1.printAllEdges();

        System.out.println("REMOVE (6 -> 2), (3 -> 2), (5 -> 2) ");
        g1.remove_edge(6,2);
        g1.remove_edge(3,2);
        g1.remove_edge(5,2);

        g1.printVerticesWithAdjacency();
        System.out.println("Nr. edges: " + g1.nrE());
        System.out.println("Nr. vertices: " + g1.nrV() + "\n"); // <- fine
        g1.printVerticesDegrees();
        g1.printAllEdges();

        // -------------------- UNDIRECTED --------------------------

        System.out.println("UNDIRECTED:\n");
        UndirectedGraph g2 = new UndirectedGraph(100);

        g2.fillGraph(11);

        g2.addDefE(1, 2);
        g2.addDefE(1, 6);
        g2.addDefE(1, 4);
        g2.addDefE(3, 2);
        g2.addDefE(4, 2);
        g2.addDefE(4, 6);
        g2.addDefE(5, 2);
        g2.addDefE(5, 6);
        g2.addDefE(6, 2);

        g2.addDefE(7, 8);
        g2.addDefE(8, 9);
        g2.addDefE(9, 7);

        g2.printVerticesWithAdjacency();
        System.out.println("Nr. edges: " + g2.nrE());
        System.out.println("Nr. vertices: " + g2.nrV() + "\n");
        g2.printVerticesDegrees();
        g2.printAllEdges();

        System.out.println("REMOVE (6 -> 2), (3 -> 2), (5 -> 2) ");
        g2.remove_edge(6,2);
        g2.remove_edge(3,2);
        g2.remove_edge(5,2);

        g2.printVerticesWithAdjacency();
        System.out.println("Nr. edges: " + g2.nrE());
        System.out.println("Nr. vertices: " + g2.nrV() + "\n"); // <- fine
        g2.printVerticesDegrees();
        g2.printAllEdges();

    }
}
