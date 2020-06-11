package com.sdeo.linkedlist;

import java.util.Iterator;

/**
 * @author Sumit Deo
 */
public class DoublyLinkedList<T> implements Iterable<T> {

    private int size;
    private Node<T> head;
    private Node<T> tail;

    private static class Node<T> {
        private T data;
        private Node<T> previousNode, nextNode;

        public Node(T data, Node<T> previousNode, Node<T> nextNode) {
            this.data = data;
            this.previousNode = previousNode;
            this.nextNode = nextNode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

    public void clear() {
        Node<T> node = head;
        while (node != null) {
            Node<T> next = node.nextNode;
            node.previousNode = null;
            node.nextNode = null;
            node.data = null;
            node = next;
        }
        head = null;
        tail = null;
        node = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T elem) {
        Node<T> node;
        if (isEmpty()) {
            node = new Node<>(elem, null, null);
            head = node;
            tail = node;
        }
        else {
            node = new Node<>(elem, null, head);
            head.previousNode = node;
            head = node;
        }
        size++;
        node = null; //for garbage collection
    }

    public void addLast(T elem) {
        Node<T> node;
        if (isEmpty()) {
            node = new Node<>(elem, null, null);
            head = node;
            tail = node;
        }
        else {
            node = new Node<>(elem, tail, null);
            tail.nextNode = node;
            tail = node;
        }
        size++;
        node = null; //gor garbage collection
    }

    public void add(T elem) {
        addLast(elem);
    }

    public void addAt(int index, T elem) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Illegal Argument: " + index);
        }

        if (index == 0) {
            addFirst(elem);
            return;
        }

        if (index == size) {
            addLast(elem);
            return;
        }

        Node<T> previousToIndexNode = head;
        Node<T> node;
        for (int i = 0; i < index - 1; i++) {
            previousToIndexNode = previousToIndexNode.nextNode;
        }

        node = new Node<>(elem, previousToIndexNode, previousToIndexNode.nextNode);
        previousToIndexNode.nextNode = node;
        previousToIndexNode.nextNode.previousNode = node;
        size++;
        node = previousToIndexNode = null; //for garbage collection
    }

    public T peekFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return head.data;
    }

    public T peekLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        return tail.data;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        T data = head.data;
        head = head.nextNode;

        --size;

        if (isEmpty()) {
            tail = null;
        }
        else {
            head.previousNode = null;
        }

        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            throw new RuntimeException("Empty List");
        }
        T data = tail.data;
        tail = tail.previousNode;

        size--;

        if (isEmpty()) {
            head = null;
        }

        else {
            tail.nextNode = null;
        }

        return data;
    }

    public T remove(Node<T> node) {
        if (node.previousNode == null) {
            return removeFirst();
        }

        if (node.nextNode == null) {
            return removeLast();
        }

        node.nextNode.previousNode = node.previousNode;
        node.previousNode.nextNode = node.nextNode;

        T data = node.data;
        node.nextNode = null;
        node.previousNode = null;
        node.data = null;

        return data;
    }

    public T removeAt(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException();
        }

        Node<T> node = head;

        for (int i = 0; i < index; i++) {
            node = node.nextNode;
        }

        return remove(node);
    }

    public boolean remove(Object o) {
        Node<T> node = head;

        if (o == null) {
            for (; node != null; node = node.nextNode) {
                if (node.data == null) {
                    remove(node);
                    return true;
                }
            }
        }
        else {
            for (; node != null; node = node.nextNode) {
                if (node.data.equals(o)) {
                    remove(node);
                    return true;
                }
            }
        }
        return false;
    }

    public int indexOf(Object o) {
        Node<T> node = head;
        int index = 0;
        if (o == null) {
            for (; node != null; node = node.nextNode) {
                if (node.data == null) {
                    return index;
                }
                index++;
            }
        }
        else {
            for (; node != null; node = node.nextNode) {
                if (node.data.equals(o)) {
                    return index;
                }
                index++;
            }
        }

        return -1;
    }

    public boolean contains(Object obj) {
        return indexOf(obj) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                T data = node.data;
                node = node.nextNode;
                return data;
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (isEmpty()) {
            builder.append("]");
        }
        else if (head == tail) {
            builder.append(head.data + "]");
        }
        else {
            Node<T> node = head;
            while (true) {
                builder.append(node.data);
                node = node.nextNode;
                if (node != tail) {
                    builder.append(", ");
                }
                else {
                    builder.append(", " + node.data + "]");
                    break;
                }
            }
        }

        return builder.toString();
    }
}
