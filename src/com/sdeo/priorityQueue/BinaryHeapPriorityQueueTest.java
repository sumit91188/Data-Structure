package com.sdeo.priorityQueue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sumit Deo
 */
class BinaryHeapPriorityQueueTest {

    BinaryHeapPriorityQueue<Integer> heapQueue;

    @BeforeEach
    void setUp() {
        heapQueue = new BinaryHeapPriorityQueue();
    }

    @AfterEach
    void tearDown() {
        heapQueue = null;
    }

    private void addElements() {

        heapQueue.add(1);
        heapQueue.add(2);
        heapQueue.add(3);
        heapQueue.add(0);
        heapQueue.add(4);
    }

    @Test
    void testArrayConstructor() {
        Integer[] ints = {7,3,1,0,2,1,0,6,3,7};
        BinaryHeapPriorityQueue<Integer> heapQueueInts = new BinaryHeapPriorityQueue<Integer>(ints);
        assertEquals("[0, 0, 1, 3, 2, 1, 7, 6, 3, 7]", heapQueueInts.toString());

        String[] strings = {"a", "b", "z", "abcd", "jgd", "09nh"};
        BinaryHeapPriorityQueue<String> heapQueueStrings = new BinaryHeapPriorityQueue<String>(strings);
        assertEquals("[09nh, abcd, a, b, jgd, z]", heapQueueStrings.toString());
    }

    @Test
    void testCollectionConstructor() {
        List<Integer> list = new ArrayList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue();
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 10; i++) {
            list.add(i);
            priorityQueue.offer(i);
            stack.push(i);
        }

        BinaryHeapPriorityQueue<Integer> heapQueueInts = new BinaryHeapPriorityQueue<Integer>(list);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", heapQueueInts.toString());

        heapQueueInts = new BinaryHeapPriorityQueue<Integer>(priorityQueue);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", heapQueueInts.toString());

        heapQueueInts = new BinaryHeapPriorityQueue<Integer>(stack);
        assertEquals("[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]", heapQueueInts.toString());
    }

    @Test
    void size() {
        assertEquals(0, heapQueue.size());

        addElements();
        assertEquals(5, heapQueue.size());
    }

    @Test
    void clear() {
        addElements();
        assertEquals(5, heapQueue.size());

        heapQueue.clear();
        assertEquals(0, heapQueue.size());
    }

    @Test
    void isEmpty() {
        assertTrue(heapQueue.isEmpty());

        addElements();
        assertEquals(5, heapQueue.size());
    }

    @Test
    void peek() {
        assertNull(heapQueue.peek());

        addElements();
        assertEquals(0, heapQueue.peek());
    }

    @Test
    void poll() {
        assertNull(heapQueue.poll());

        addElements();
        for (int i = 0; i < 5; i++) {
            assertEquals(i, heapQueue.peek());
            assertEquals(i, heapQueue.poll());
        }

        assertTrue(heapQueue.isEmpty());
    }

    @Test
    void contains() {
        assertFalse(heapQueue.contains(1));

        addElements();
        assertTrue(heapQueue.contains(1));
    }

    @Test
    void add() {
        //tested indirectly
    }

    @Test
    void remove() {
        assertFalse(heapQueue.remove(0));

        addElements();
        for (int i = 4; i >= 0; i--) {
            assertEquals(0, heapQueue.peek());
            assertTrue(heapQueue.remove(i));
        }

        assertFalse(heapQueue.remove(4));
    }

    @Test
    void testToString() {
        addElements();
        assertEquals("[0, 1, 3, 2, 4]", heapQueue.toString());
    }
}