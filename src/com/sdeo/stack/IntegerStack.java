package com.sdeo.stack;

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class IntegerStack<T> implements Stack<Integer> {

    private Integer[] ints;
    private int index = 0;

    public IntegerStack(int maxElems) {
        ints = new Integer[maxElems];
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

    @Override
    public void push(Integer elem) {
        if (index > ints.length) {
            throw new RuntimeException("Stack Overflow.");
        }
        ints[index++] = elem;
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return ints[index - 1];
    }

    @Override
    public Integer pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        int elem = ints[index - 1];
        ints[index - 1] = null;
        index--;
        return elem;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int iteratorIndex = 0;

            @Override
            public boolean hasNext() {
                return iteratorIndex < index;
            }

            @Override
            public Integer next() {
                return ints[iteratorIndex++];
            }
        };
    }
}
