package com.sdeo.stack;

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
public class StackTest {

    private List<Stack<Integer>> stacks;

    @BeforeEach
    public void setStacks() {
        stacks = new ArrayList<>();
        stacks.add(new LinkedListStack<Integer>());
        stacks.add(new ArrayStack<Integer>());
        stacks.add(new IntegerStack<>(2));
    }

    @AfterEach
    public void destroyStack() {
        stacks = null;
    }

    @Test
    public void testEmptyStack() {
        for (Stack stack : stacks) {
            assertTrue(stack.isEmpty());
        }
    }

    @Test
    public void testNonEmptyStack() {
        for (Stack stack : stacks) {
            stack.push(10);
            assertFalse(stack.isEmpty());
        }
    }

    @Test
    public void testSizeOfEmptyStack() {
        for (Stack stack : stacks) {
            assertEquals(0, stack.size());
        }
    }

    @Test
    public void testSizeOfNonEmptyStack() {
        for (Stack stack : stacks) {
            stack.push(10);
            stack.push(20);
            assertEquals(2, stack.size());
        }
    }

    @Test
    public void testPeekEmptyStack() {
        for (Stack stack : stacks) {
            assertThrows(Exception.class, stack::peek);
        }
    }

    @Test
    public void testPeekStack() {
        for (Stack stack : stacks) {
            stack.push(10);
            stack.push(20);
            assertEquals(20, stack.peek());
        }
    }

    @Test
    public void testPopEmptyStack() {
        for (Stack stack : stacks) {
            assertThrows(Exception.class, stack::pop);
        }
    }

    @Test
    public void testPopStack() {
        for (Stack stack : stacks) {
            stack.push(10);
            stack.push(20);
            assertEquals(20, stack.pop());
            assertEquals(10, stack.pop());
            assertEquals(0, stack.size());
            assertTrue(stack.isEmpty());
        }
    }

    @Test
    public void testIteratorForNonEmptyQueues() {
        for (Stack stack : stacks) {
            stack.push(10);
            stack.push(20);
            Iterator iterator = stack.iterator();
            List ints = new ArrayList();
            while (iterator.hasNext()) {
                ints.add(iterator.next());
            }
            assertEquals("[10, 20]", ints.toString());
        }
    }
}
