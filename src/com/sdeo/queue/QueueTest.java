package com.sdeo.queue;

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
public class QueueTest {

    private List<Queue<Integer>> queues;

    @BeforeEach
    public void setQueues() {
        queues = new ArrayList<>();
        queues.add(new LinkedListQueue<Integer>());
        queues.add(new ArrayQueue<Integer>());
        queues.add(new IntegerQueue(2));
    }

    @AfterEach
    public void destroyQueue() {
        queues = null;
    }

    @Test
    public void testIsEmptyForEmptyQueues() {
        for (Queue queue : queues) {
            assertTrue(queue.isEmpty());
        }
    }

    @Test
    public void testIsEmptyForNonEmptyQueues() {
        for (Queue queue : queues) {
            queue.offer(10);
            queue.offer(20);
            assertFalse(queue.isEmpty());
        }
    }

    @Test
    public void testSizeForEmptyQueues() {
        for (Queue queue : queues) {
            assertEquals(0, queue.size());
        }
    }

    @Test
    public void testSizeForNonEmptyQueues() {
        for (Queue queue : queues) {
            queue.offer(10);
            queue.offer(20);
            assertEquals(2, queue.size());
        }
    }

    @Test
    public void testPeekForEmptyQueues() {
        for (Queue queue : queues) {
            assertThrows(Exception.class, queue::peek);
        }
    }

    @Test
    public void testPeekForNonEmptyQueues() {
        for (Queue queue : queues) {
            queue.offer(10);
            queue.offer(20);
            assertEquals(10, queue.peek());
            assertEquals(10, queue.peek());
        }
    }

    @Test
    public void testPollForEmptyQueues() {
        for (Queue queue : queues) {
            assertThrows(Exception.class, queue::poll);
        }
    }

    @Test
    public void testPollForNonEmptyQueues() {
        for (Queue queue : queues) {
            queue.offer(10);
            queue.offer(20);
            assertEquals(10, queue.poll());
            assertEquals(20, queue.poll());
        }
    }

    @Test
    public void testIteratorForNonEmptyQueues() {
        for (Queue queue : queues) {
            queue.offer(10);
            queue.offer(20);
            Iterator iterator = queue.iterator();
            List ints = new ArrayList();
            while (iterator.hasNext()) {
                ints.add(iterator.next());
            }
            assertEquals("[10, 20]", ints.toString());
        }
    }
}
