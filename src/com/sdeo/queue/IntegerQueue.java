package com.sdeo.queue;

import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class IntegerQueue implements Queue<Integer> {

    int index = 0;
    int maxElms;
    int[] integers;

    public IntegerQueue(int maxElms) {
        this.maxElms = maxElms;
        integers = new int[maxElms];
    }

    @Override
    public Integer poll() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        int elem = integers[0];
        for (int i = 1; i < index; i++) {
            integers[i - 1] = integers[i];
        }
        index--;
        return elem;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        return integers[0];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    @Override
    public void offer(Integer elem) {
        if (index > maxElms) {
            throw new RuntimeException("Queue overflow.");
        }
        integers[index++] = elem;
    }


    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < index;
            }

            @Override
            public Integer next() {
                return integers[iteratorIndex++];
            }
        };
    }
}
