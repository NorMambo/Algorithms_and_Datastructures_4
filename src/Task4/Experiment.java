package Task4;

import HelperClasses.CSVReaderWriter;
import HelperClasses.Functions;
import HelperClasses.Timer;
import Task1.DirectedGraph;

import java.util.ArrayList;

public class Experiment {
    public static void main(String[] args) throws Exception {

        Timer t = new Timer();
        Functions fs = new Functions();
        CSVReaderWriter readerWriter = new CSVReaderWriter();
        ArrayList<Double> dijkstraTimes = new ArrayList<>();
        ArrayList<Double> bellmanTimes = new ArrayList<>();

        ArrayList<Integer> sizes = new ArrayList<>();
        int randDst = -1;
        int randWeight = -1;

        for (int i = 1; i <= 100; i++) {
            DirectedGraph dg1 = new DirectedGraph(101);
            dg1.fillGraph(101);

            for (int j = 0; j < 101; j++) {
                boolean found = false;
                while (!found) {
                    randDst = (int) Math.floor(Math.random() * (j+5) + 1);
                    randWeight = (int) Math.floor(Math.random() * 31);
                    if (randDst != j && randDst <= 100) {
                        found = true;
                    }
                }

                dg1.addE(j,randDst,randWeight);
            }
            double time1 = t.timeFunction(()->{
                for (int j = 0; j < 101; j++) {
                    BellmanFord bf = new BellmanFord(dg1, j);
                }
                return null;
            });
            double time2 = t.timeFunction(()->{
                for (int j = 0; j < 101; j++) {
                    Dijkstra di = new Dijkstra(dg1, j);
                }
                    return null;
            });

            dijkstraTimes.add(time1);
            bellmanTimes.add(time2);
            sizes.add(i);
        }

        System.out.println("Bellman avg: " + fs.findAvg(bellmanTimes));
        System.out.println("Dijkstra avg: " + fs.findAvg(dijkstraTimes));

        readerWriter.writeToFile(dijkstraTimes,sizes,"src/Task4/Dijkstra.csv");
        readerWriter.writeToFile(bellmanTimes,sizes,"src/Task4/Bellman.csv");


    }
}
