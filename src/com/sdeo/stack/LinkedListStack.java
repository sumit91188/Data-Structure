package com.sdeo.stack;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author Sumit Deo
 */
public class LinkedListStack<T> implements Stack<T> {

    LinkedList<T> list;

    public LinkedListStack() {
        list = new LinkedList<T>();
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
    public void push(T elem) {
        list.addLast(elem);
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.peekLast();
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return list.removeLast();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
}
