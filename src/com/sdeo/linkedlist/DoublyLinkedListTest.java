package com.sdeo.linkedlist;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sumit Deo
 */
class DoublyLinkedListTest {

    DoublyLinkedList<Integer> linkedList;

    @BeforeEach
    void setUp() {
        linkedList = new DoublyLinkedList<>();
    }

    @AfterEach
    void tearDown() {
        linkedList = null;
    }

    @Test
    void clear() {
        linkedList.addFirst(10);
        linkedList.addLast(20);
        linkedList.clear();
        assertEquals("[]", linkedList.toString());
    }

    @Test
    void size() {
        assertEquals(0, linkedList.size());
    }

    @Test
    void isEmpty() {
        assertTrue(linkedList.isEmpty());
    }

    @Test
    void addFirst() {
        linkedList.addFirst(10);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(10));
        assertEquals(1, linkedList.size());
        assertEquals(0, linkedList.indexOf(10));
        assertEquals(10, linkedList.peekFirst());
        assertEquals("[10]", linkedList.toString());
    }

    @Test
    void addLast() {
        linkedList.addFirst(10);
        linkedList.addLast(20);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(20));
        assertEquals(2, linkedList.size());
        assertEquals(1, linkedList.indexOf(20));
        assertEquals(20, linkedList.peekLast());
        assertEquals("[10, 20]", linkedList.toString());
    }

    @Test
    void add() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(20));
        assertEquals(3, linkedList.size());
        assertEquals(1, linkedList.indexOf(20));
        assertEquals("[10, 20, 30]", linkedList.toString());
    }

    @Test
    void addAt() {
        linkedList.addFirst(10);
        linkedList.add(30);
        linkedList.addLast(40);
        linkedList.addAt(1, 20);
        assertFalse(linkedList.isEmpty());
        assertTrue(linkedList.contains(20));
        assertEquals(4, linkedList.size());
        assertEquals(1, linkedList.indexOf(20));
        assertEquals("[10, 20, 30, 40]", linkedList.toString());
    }

    @Test
    void addAt_exception1() {
        assertThrows(IllegalArgumentException.class, () -> linkedList.addAt(-1, 10));
    }

    @Test
    void addAt_exception2() {
        assertThrows(IllegalArgumentException.class, () -> linkedList.addAt(1, 10));
    }

    @Test
    void addAt_addAt0() {
        linkedList.addAt(0, 10);
        assertEquals("[10]", linkedList.toString());
    }

    @Test
    void addAt_addAtLastIndex() {
        linkedList.addFirst(10);
        linkedList.addLast(20);
        linkedList.addAt(2, 30);
        assertEquals("[10, 20, 30]", linkedList.toString());
    }

    @Test
    void peekFirst() {
        linkedList.addFirst(10);
        assertEquals(10, linkedList.peekFirst());
    }

    @Test
    void peekFirst_exception() {
        assertThrows(RuntimeException.class, () -> linkedList.peekFirst());
    }

    @Test
    void peekLast() {
        linkedList.addLast(10);
        assertEquals(10, linkedList.peekLast());
    }

    @Test
    void peekLast_exception() {
        assertThrows(RuntimeException.class, () -> linkedList.peekLast());
    }

    @Test
    void removeFirst() {
        linkedList.addFirst(10);
        assertEquals(10, linkedList.removeFirst());
    }

    @Test
    void removeFirst_exception() {
        assertThrows(RuntimeException.class, () -> linkedList.removeFirst());
    }

    @Test
    void removeLast() {
        linkedList.addLast(10);
        assertEquals(10, linkedList.removeLast());
    }

    @Test
    void removeLast_exception() {
        assertThrows(RuntimeException.class, () -> linkedList.removeLast());
    }

    @Test
    void removeAt() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertEquals(20, linkedList.removeAt(1));
    }

    @Test
    void removeAt_exception1() {
        assertThrows(IllegalArgumentException.class, () -> linkedList.removeAt(-1));
    }

    @Test
    void removeAt_exception2() {
        assertThrows(IllegalArgumentException.class, () -> linkedList.removeAt(1));
    }

    @Test
    void testRemove() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertTrue(linkedList.remove(20));
    }

    @Test
    void testRemove_returnsFalse() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertFalse(linkedList.remove(40));
    }

    @Test
    void indexOf() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertEquals(1, linkedList.indexOf(20));
    }

    @Test
    void indexOf_null() {
        linkedList.addFirst(10);
        linkedList.add(null);
        linkedList.addLast(30);
        assertEquals(1, linkedList.indexOf(null));
    }

    @Test
    void indexOf_notFound() {
        linkedList.addFirst(10);
        linkedList.add(20);
        linkedList.addLast(30);
        assertEquals(-1, linkedList.indexOf(40));
    }

    @Test
    void contains() {
        linkedList.addFirst(10);
        assertTrue(linkedList.contains(10));
        assertFalse(linkedList.contains(20));
    }

    @Test
    void iterator() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        List<Integer> integers = new ArrayList<>();
        Iterator iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            integers.add((Integer) iterator.next());
        }
        assertEquals("[10, 20, 30]", integers.toString());
    }

    @Test
    void testToString() {
        assertEquals("[]", linkedList.toString());
    }
}