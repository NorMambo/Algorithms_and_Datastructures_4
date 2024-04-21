package Task5;

import HelperClasses.CSVReaderWriter;
import HelperClasses.InfoList;
import Task1.DirectedGraph;
import Task1.Vertex;

import java.io.IOException;
import java.util.ArrayList;

public class GraphBuilder {

    DirectedGraph g;
    public Vertex[] vertices;

    CSVReaderWriter readerWriter = new CSVReaderWriter();

    public GraphBuilder(int maxNrVertices) throws Exception {
        g = new DirectedGraph(maxNrVertices);
        vertices = new Vertex[maxNrVertices];
    }

    public void createGraphFromCSV(String path) throws IOException {

        readerWriter.assignCSVFile(path);
        InfoList il = readerWriter.getInfoArrays();
        ArrayList<String> src = il.get2ndInfo(); // sources
        ArrayList<String> dst = il.get1stInfo(); // destinations

        Vertex newSrc = new Vertex(0, src.get(0));
        newSrc.setInitialDegDir();
        Vertex newDst = new Vertex(1, dst.get(0));
        newDst.setInitialDegDir();

        int count = 2;

        g.vertices[newSrc.nr] = newSrc;
        g.vertices[newDst.nr] = newDst;
        g.addDefE(newSrc.nr, newDst.nr);

        Vertex prevSrc; // Vertex that already exists in the graph
        Vertex prevDst; // Vertex that already exists in the graph


        for (int i = 1; i < src.size(); i++) {

            int tmp = count;
            newSrc = new Vertex(tmp, src.get(i));
            newSrc.setInitialDegDir();
            tmp++;
            newDst = new Vertex(tmp, dst.get(i));
            newDst.setInitialDegDir();

            prevSrc = g.contains(newSrc); // Vertex that already exists in the graph
            prevDst = g.contains(newDst); // Vertex that already exists in the graph

            if (prevSrc == null && prevDst == null) {
                g.vertices[newSrc.nr] = newSrc;
                g.vertices[newDst.nr] = newDst;
                g.addDefE(newSrc.nr, newDst.nr);
                count+=2;
            }
            else if (prevSrc != null && prevDst != null) {
                g.addDefE(prevSrc.nr, prevDst.nr);
            }
            else if (prevSrc != null){
                newDst = new Vertex(count, dst.get(i));
                newDst.setInitialDegDir();
                g.vertices[prevSrc.nr] = prevSrc;
                g.vertices[newDst.nr] = newDst;
                g.addDefE(prevSrc.nr, newDst.nr);
                count++;
            }
            else {
                g.vertices[newSrc.nr] = newSrc;
                g.vertices[prevDst.nr] = prevDst;
                g.addDefE(newSrc.nr, prevDst.nr);
                count++;
            }
        }
    }
}
