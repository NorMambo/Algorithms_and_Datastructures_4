package HelperClasses;

import java.util.Arrays;

public class UnionFind {
    private final int[] parents;
    private final int[] sizeOfComponents;
    private int elements;

    public UnionFind(int elements) {
        int[] parents = new int[elements];
        int[] sizeOfComponents = new int[elements];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        Arrays.fill(sizeOfComponents,1);
        this.parents = parents;
        this.sizeOfComponents = sizeOfComponents;
        this.elements = elements;
    }

    public boolean connected(int a, int b) {
        return root(a) == root(b);
    }

    public int root(int a) {
        while (a != parents[a]) {
            parents[a] = parents[parents[a]];
            a = parents[a];
        }
        return a;
    }

    public void union(int a, int b) {
        int root_a = root(a);
        int root_b = root(b);
        if (sizeOfComponents[root_a] < sizeOfComponents[root_b]) {
            parents[root_a] = root_b;
            sizeOfComponents[root_b] += sizeOfComponents[root_a];
        } else {
            parents[root_b] = root_a;
            sizeOfComponents[root_a] += sizeOfComponents[root_b];
        }
    }
}
