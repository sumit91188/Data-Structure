package com.sdeo.dynamicarray;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sumit Deo
 */
class DynamicArrayTest {

    DynamicArray<Integer> intArray;

    @BeforeEach
    void setup() {
        intArray = new DynamicArray<>();
    }

    @Test
    void testControllerNegativeCapacity() {
        try {
            intArray = new DynamicArray<>(-1);
            Assert.fail("Cant have -ve capacity!");
        }
        catch (IllegalArgumentException illegalArgumentException) {
            assertEquals("Illegal Capacity: -1", illegalArgumentException.getMessage());
        }
    }

    @Test
    void size() {
        assertEquals(0, intArray.size());
    }

    @Test
    void isEmpty() {
        assertTrue(intArray.isEmpty());
    }

    @Test
    void add() {
        intArray.add(10);
        assertEquals("[10]", intArray.toString());

        intArray = new DynamicArray<Integer>(0);
        intArray.add(10);
        assertEquals("[10]", intArray.toString());

        intArray = new DynamicArray<Integer>(1);
        intArray.add(10);
        intArray.add(20);
        assertEquals("[10, 20]", intArray.toString());
    }

    @Test
    void clear() {
        intArray.add(10);

        intArray.clear();
        assertEquals("[]", intArray.toString());
    }

    @Test
    void removeAt() {
        intArray.add(10);
        intArray.add(20);
        intArray.add(30);

        assertEquals(10, intArray.removeAt(0));
        assertEquals(20, intArray.removeAt(0));
        assertEquals(30, intArray.removeAt(0));

        assertThrows(IndexOutOfBoundsException.class, () -> intArray.removeAt(-1));
    }

    @Test
    void indexOf() {
        intArray.add(10);
        intArray.add(10);
        intArray.add(20);

        assertEquals(0, intArray.indexOf(10));
        assertEquals(2, intArray.indexOf(20));
    }

    @Test
    void remove() {
        intArray.add(10);
        intArray.add(10);
        intArray.add(20);

        assertTrue(intArray.remove(10));
        assertFalse(intArray.remove(-1));
        assertEquals("[10, 20]", intArray.toString());
    }

    @Test
    void contains() {
        intArray.add(10);
        intArray.add(10);
        intArray.add(20);

        assertTrue(intArray.contains(10));
        assertFalse(intArray.contains(-1));
    }

    @Test
    void iterator() {
        intArray.add(10);
        intArray.add(10);
        intArray.add(20);

        Iterator iterator = intArray.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            iterator.next();
            count++;
        }

        assertEquals(3, count);
    }
}