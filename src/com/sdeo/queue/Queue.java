package com.sdeo.queue;

import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public interface Queue<T> extends Iterable<T> {

    public T poll();

    public T peek();

    public int size();

    public boolean isEmpty();

    public void offer(T elem);

    @Override
    Iterator<T> iterator();
}
