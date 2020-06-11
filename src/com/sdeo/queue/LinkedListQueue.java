package com.sdeo.queue;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Sumit Deo
 */
public class LinkedListQueue<T> implements Queue<T> {

    LinkedList<T> list = new LinkedList<T>();

    public LinkedListQueue() {
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        return list.removeFirst();
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Empty queue.");
        }
        return list.peekFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void offer(T elem) {
        list.addLast(elem);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
