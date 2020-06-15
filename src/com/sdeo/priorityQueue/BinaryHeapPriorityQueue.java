package com.sdeo.priorityQueue;

import java.util.*;

/**
 * @author Sumit Deo
 */
public class BinaryHeapPriorityQueue<T extends Comparable<T>> {

    private List<T> heap;
    private HashMap<T, TreeSet<Integer>> map;

    public BinaryHeapPriorityQueue() {
        this(1);
    }

    public BinaryHeapPriorityQueue(int maxElms) {
        heap = new ArrayList<T>(maxElms);
        map = new HashMap<>();
    }

    public BinaryHeapPriorityQueue(T[] elms) {

        int heapSize = elms.length;
        heap = new ArrayList<>(heapSize);
        map = new HashMap<>();

        for (int i = 0; i < heapSize; i++) {
            heap.add(elms[i]);
            putToMap(elms[i], i);
        }

        for (int i = Math.max(0, heapSize/2 - 1); i >= 0; i--) {
            sink(i);
        }
    }

    public BinaryHeapPriorityQueue(Collection<T> elems) {
        this(elems.size());
        for (T elem : elems) add(elem);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    // Clears everything inside the heap, O(n)
    public void clear() {
        heap.clear();
        map.clear();
    }

    public T peek() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    public T poll() {
        return removeAt(0);
    }

    public boolean contains(T elem) {

        if (elem == null) return false;
        return map.containsKey(elem);
    }

    public int size() {
        return heap.size();
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

    private boolean less(int i, int j) {
        T node1 = heap.get(i);
        T node2 = heap.get(j);
        return node1.compareTo(node2) <= 0;
    }

    private void swap(int i, int j) {
        T elem_i = heap.get(i);
        T elem_j = heap.get(j);

        heap.set(i, elem_j);
        heap.set(j, elem_i);

        mapSwap(elem_i, elem_j, i, j);
    }

    private void putToMap(T elm, int i) {
        if (map.containsKey(elm)) {
            map.get(elm).add(i);
        }
        else {
            TreeSet set = new TreeSet();
            set.add(i);
            map.put(elm, set);
        }
    }

    public void add(T elem) {

        if (elem == null) throw new IllegalArgumentException();

        heap.add(elem);
        int indexOfLastElem = size() - 1;
        putToMap(elem, indexOfLastElem);

        swim(indexOfLastElem);
    }

    private void swim(int k) {

        int parent = (k - 1) / 2;
        while (k > 0 && less(k, parent)) {

            swap(parent, k);
            k = parent;

            parent = (k - 1) / 2;
        }
    }

    public boolean remove(T element) {

        if (element == null) return false;
        Integer index = mapGet(element);
        if (index != null) removeAt(index);
        return index != null;
    }

    private T removeAt(int i) {

        if (isEmpty()) return null;

        int indexOfLastElem = size() - 1;
        T removed_data = heap.get(i);
        swap(i, indexOfLastElem);

        heap.remove(indexOfLastElem);
        mapRemove(removed_data, indexOfLastElem);

        if (i == indexOfLastElem) return removed_data;

        T elem = heap.get(i);

        sink(i);

        if (heap.get(i).equals(elem)) swim(i);

        return removed_data;
    }

    private void mapRemove(T value, int index) {
        TreeSet<Integer> set = map.get(value);
        set.remove(index); // TreeSets take O(log(n)) removal time
        if (set.size() == 0) map.remove(value);
    }

    // Extract an index position for the given value
    // NOTE: If a value exists multiple times in the heap the highest
    // index is returned (this has arbitrarily been chosen)
    private Integer mapGet(T value) {
        TreeSet<Integer> set = map.get(value);
        if (set != null) return set.last();
        return null;
    }

    private void mapSwap(T val1, T val2, int val1Index, int val2Index) {

        Set<Integer> set1 = map.get(val1);
        Set<Integer> set2 = map.get(val2);

        set1.remove(val1Index);
        set2.remove(val2Index);

        set1.add(val2Index);
        set2.add(val1Index);
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
