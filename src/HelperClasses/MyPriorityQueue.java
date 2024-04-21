package HelperClasses;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MyPriorityQueue<T extends Comparable<T>> implements Iterable<T>{

    private int capacity;
    private T[] maxBinaryHeap;
    private int size;

    public MyPriorityQueue(int capacity) {
        this.capacity = capacity + 1; // to have the extra empty space in the beginning
        maxBinaryHeap = (T[]) new Comparable[this.capacity];
        size = 0;
    }

    public int size() {
        return size;
    }

    public void insert(T val) {
        if (size+1 > maxBinaryHeap.length-1) { // grow
            int newCapacity = this.capacity*2;
            maxBinaryHeap = Arrays.copyOf(maxBinaryHeap, newCapacity);
        }
        size++; // size is also the position of the value in the array
        maxBinaryHeap[size] = val;
        _swim(size); // if the child is larger than the parent
    }

    public void _swim(int size) {
        while (size > 1 && maxBinaryHeap[size / 2].compareTo(maxBinaryHeap[size]) > 0) { // while position is greater than 1 and parent is smaller than inserted element
            T tmp = maxBinaryHeap[size / 2]; // parent
            maxBinaryHeap[size / 2] = maxBinaryHeap[size]; // put child in parent position
            maxBinaryHeap[size] = tmp; // put parent in child position
            size = size/2; // go one level up
        }
    }

    public void _sink(int k) {

        while (2 * k <= size) {

            int j = 2 * k;
            if (j < size && maxBinaryHeap[j].compareTo(maxBinaryHeap[j + 1]) > 0)
                j+=1;
            if (maxBinaryHeap[k].compareTo(maxBinaryHeap[j]) <= 0)
                break;
            T tmp = maxBinaryHeap[k];
            maxBinaryHeap[k] = maxBinaryHeap[j];
            maxBinaryHeap[j] = tmp;

            k = j;
        }
    }

    public T delMin() {
        if (size > 0) {
            T max = maxBinaryHeap[1];
            T tmp = maxBinaryHeap[1];
            maxBinaryHeap[1] = maxBinaryHeap[size];
            maxBinaryHeap[size] = tmp;
            size--;
            _sink(1);
            maxBinaryHeap[size + 1] = null;
            return max;
        }
        return null;
    }

    public boolean isEmpty() {
        return size==0;
    }

    public void printQueue() {
        for (T i : maxBinaryHeap) {
            if (i != null)
                System.out.print(i + " ");
            else
                System.out.print("__ ");

        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator();
    }
    private class Iterator<T extends Comparable<T>> implements java.util.Iterator {

        int pos = 0;
        @Override
        public boolean hasNext() {
            return size > 0;
        }

        @Override
        public Object next() {
            if (!hasNext())
                throw new NoSuchElementException();
            Object tmp = maxBinaryHeap[pos];
            pos++;
            return tmp;
        }
    }
}
