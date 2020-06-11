package com.sdeo.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class ArrayStack<T> implements Stack<T> {

    int maxElms;
    int index = 0;
    Object[] array;

    public ArrayStack() {
        this.maxElms = 16;
        array = new Object[maxElms];
    }

    @Override
    public int size() {
        if (isEmpty()) {
            return 0;
        }
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
    public void push(T elem) {
        if (index > maxElms) {
            increaseCapacity();
        }
        array[index++] = elem;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) array[index - 1];
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T elem = (T) array[index - 1];
        array[index - 1] = null;
        index--;
        return elem;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int iteratorIndex = 0;

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
