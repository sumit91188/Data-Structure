package com.sdeo.dynamicarray;

import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class DynamicArray<T> implements Iterable<T> {

    private T[] array;
    private int arrayLength = 0;
    private int arrayCapacity = 0;

    public DynamicArray() {
        this(16);
    }

    public DynamicArray(int arrayCapacity) {
        if (arrayCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + arrayCapacity);
        }
        this.arrayCapacity = arrayCapacity;
        array = (T[]) new Object[arrayCapacity];
    }

    public int size() {
        return arrayLength;
    }

    public boolean isEmpty() {
        return arrayLength == 0;
    }

    public void clear() {
        for (T item : array) {
            item = null;
        }
        arrayLength = 0;
    }

    public void add(T value) {
        if (arrayLength + 1 > arrayCapacity) {
            if (arrayCapacity == 0) {
                arrayCapacity = 1;
            }
            else {
                arrayCapacity*=2;
            }
            T[] newArray = (T[]) new Object[arrayCapacity];
            for (int i = 0; i < arrayLength; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            array[arrayLength++] = value;
        }
        else {
            array[arrayLength++] = value;
        }
    }

    public T removeAt(int index) {
        if (index < 0 || index >= arrayLength) {
            throw new IndexOutOfBoundsException();
        }
        T value = array[index];
        for (int i = index; i < arrayLength - 1; i++) {
            array[i] = array[i + 1];
        }
        arrayLength--;
        return value;
    }

    public int indexOf(T elem) {
        int index = -1;
        for (int i = 0; i < arrayLength; i++) {
            if (array[i] == elem) {
                index = i;
                break;
            }
        }
        return index;
    }

    public boolean remove(T elem) {
        int index = indexOf(elem);
        if (index == -1) {
            return false;
        }
        else {
            removeAt(index);
            return true;
        }
    }

    public boolean contains(T elem) {
        return indexOf(elem) != -1;
    }

    @Override
    public String toString() {

        if (arrayLength == 0) {
            return "[]";
        }
        else {
            StringBuilder stringBuilder = new StringBuilder("[");
            for (int i = 0; i < arrayLength - 1; i++) {
                stringBuilder.append(array[i] + ", ");
            }
            return stringBuilder.append(array[arrayLength - 1] + "]").toString();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int index = 0;

            @Override
            public boolean hasNext() {
                return index < arrayLength;
            }

            @Override
            public T next() {
                return array[index++];
            }
        };
    }
}
