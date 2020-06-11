package com.sdeo.queue;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class ArrayQueue<T> implements Queue<T> {

    int index = 0;
    Object[] array;
    int maxElms = 16;

    public ArrayQueue() {
        array = new Object[maxElms];
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException();
        }

        Object elem = array[0];
        for (int i = 1; i < index; i++) {
            array[i - 1] = array[i];
        }
        array[index - 1] = null;
        index--;
        return (T) elem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException();
        }
        return (T) array[0];
    }

    @Override
    public int size() {
        return index;
    }

    @Override
    public boolean isEmpty() {
        return index == 0;
    }

    private void increaseCapacity() {
        maxElms *= 2;
        array = Arrays.copyOf(array, maxElms);
    }

    @Override
    public void offer(T elem) {
        if (index > maxElms) {
            increaseCapacity();
        }
        array[index++] = elem;
    }

    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int iteratorIndex;

            @Override
            public boolean hasNext() {
                return iteratorIndex < index;
            }

            @Override
            public T next() {
                return (T) array[iteratorIndex++];
            }
        };
    }
}
