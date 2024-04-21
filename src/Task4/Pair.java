package Task4;

public class Pair implements Comparable<Pair>{
    public double weight;
    public int vertexNr;
    public Pair(double weight, int vertexNr) {
        this.weight = weight;
        this.vertexNr = vertexNr;
    }

    @Override
    public int compareTo(Pair o) {
        if (this.weight < o.weight)
            return -1;
        if (this.weight > o.weight)
            return 1;
        else
            return 0;
    }
}
