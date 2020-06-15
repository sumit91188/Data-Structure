package com.sdeo.priorityQueue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sumit Deo
 */
public class BinaryHeapQueue<T extends Comparable<T>> {

    private List<T> heap;

    public BinaryHeapQueue() {
        this(1);
    }

    public BinaryHeapQueue(int maxElms) {
        heap = new ArrayList<>(maxElms);
    }

    public BinaryHeapQueue(T[] elms) {
        heap = new ArrayList<>(elms.length);

        for (int i = 0; i < elms.length; i++) {
            heap.add(elms[i]);
        }

        for (int i = Math.max(0, (elms.length / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public BinaryHeapQueue(Collection<T> elms) {
        heap = new ArrayList<>(elms.size());

        heap.addAll(elms);

        for (int i = Math.max(0, (elms.size() / 2) - 1); i >= 0; i--) {
            sink(i);
        }
    }

    private void sink(int i) {
        int heapSize = size();

        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int smallest = left;

            if (right < heapSize && less(right, left)) {
                smallest = right;
            }

            if (left >= heapSize || less(i, smallest)) {
                break;
            }

            swap(smallest, i);
            i = smallest;
        }
    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);
    }

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    public int size() {
        return heap.size();
    }

    public void clear() {
        heap.clear();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {
        // Linear scan to check containment
        for (int i = 0; i < size(); i++) {
            if (heap.get(i).equals(elem)) {
                return true;
            }
        }
        return false;
    }

    public void add(T elem) {

        if (elem == null) throw new IllegalArgumentException();

        heap.add(elem);

        int indexOfLastElem = size() - 1;
        swim(indexOfLastElem);
    }

    private void swim(int i) {
        int parent = (i - 1) / 2;

        // Keep swimming while we have not reached the
        // root and while we're less than our parent.
        while (i > 0 && less(i, parent)) {
            // Exchange k with the parent
            swap(parent, i);
            i = parent;

            // Grab the index of the next parent node WRT to k
            parent = (i - 1) / 2;
        }
    }

    private T removeAt(int i) {
        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

        // Obliterate the value
        heap.remove(indexOfLastElem);

        // Check if the last element was removed
        if (i == indexOfLastElem) return removed_data;
        T elem = heap.get(i);

        // Try sinking element
        sink(i);

        // If sinking did not work try swimming
        if (heap.get(i).equals(elem)) swim(i);
        return removed_data;
    }

    public boolean remove(T element) {
        if (element == null) return false;
        // Linear removal via search, O(n)
        for (int i = 0; i < size(); i++) {
            if (element.equals(heap.get(i))) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
