package HelperClasses;

import java.util.ArrayList;

public class InfoList<T> {
    private final ArrayList<T> info1;
    private final ArrayList<T> info2;

    public InfoList(ArrayList<T> source, ArrayList<T> destination) {
        this.info1 = source;
        this.info2 = destination;
    }

    public ArrayList<T> get1stInfo() {
        return this.info1;
    }

    public ArrayList<T> get2ndInfo() {
        return this.info2;
    }
}
