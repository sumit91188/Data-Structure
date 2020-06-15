package com.sdeo.unionFind;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sumit Deo
 */
class UnionFindTest {

    UnionFind set;

    @BeforeEach
    void setUp() {
        set = new UnionFind(10);
    }

    @AfterEach
    void tearDown() {
        set = null;
    }

    @Test
    void size() {
        assertEquals(10, set.size());
    }

    @Test
    void getNumComponents() {
        assertEquals(10, set.getNumComponents());
    }

    @Test
    void find() {
        for (int i = 0; i < 10; i++) {
            assertEquals(i, set.find(i));
        }
    }

    @Test
    void isConnected() {
        assertTrue(set.isConnected(0,0));
        assertTrue(set.isConnected(9,9));

        assertFalse(set.isConnected(0,9));
        assertFalse(set.isConnected(8,1));
    }

    @Test
    void getComponentSize() {
        for (int i = 0; i < 10; i++) {
            assertEquals(1, set.getComponentSize(i));
        }
    }

    @Test
    void unify() {
        set.unify(0,9);
        set.unify(1,8);
        set.unify(2,7);
        set.unify(3,6);
        set.unify(4,5);

        assertEquals(0, set.find(9));
        assertEquals(1, set.find(8));
        assertEquals(2, set.find(7));
        assertEquals(3, set.find(6));
        assertEquals(4, set.find(5));

        assertEquals(5, set.getNumComponents());

        assertTrue(set.isConnected(0, 9));
        assertTrue(set.isConnected(1, 8));
        assertTrue(set.isConnected(2, 7));
        assertTrue(set.isConnected(3, 6));
        assertTrue(set.isConnected(4, 5));

        assertFalse(set.isConnected(4, 9));
        assertFalse(set.isConnected(2, 8));
        assertFalse(set.isConnected(3, 7));
        assertFalse(set.isConnected(1, 6));
        assertFalse(set.isConnected(0, 5));

        set.unify(0,1);
        set.unify(3,4);

        assertEquals(0, set.find(9));
        assertEquals(0, set.find(8));
        assertEquals(2, set.find(7));
        assertEquals(3, set.find(6));
        assertEquals(3, set.find(5));

        assertEquals(3, set.getNumComponents());

        assertTrue(set.isConnected(0, 9));
        assertTrue(set.isConnected(1, 8));
        assertTrue(set.isConnected(2, 7));
        assertTrue(set.isConnected(3, 6));
        assertTrue(set.isConnected(4, 5));

        assertTrue(set.isConnected(0, 8));
        assertTrue(set.isConnected(9, 1));
        assertTrue(set.isConnected(3, 5));
        assertTrue(set.isConnected(4, 6));

        assertFalse(set.isConnected(4, 9));
        assertFalse(set.isConnected(2, 8));
        assertFalse(set.isConnected(3, 7));
        assertFalse(set.isConnected(1, 6));
        assertFalse(set.isConnected(0, 5));

        set.unify(0,2);
        set.unify(2,4);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertTrue(set.isConnected(i, j));
            }
        }

        assertEquals(1, set.getNumComponents());

        for (int i = 0; i < 10; i++) {
            assertEquals(10, set.getComponentSize(i));
            assertEquals(0, set.find(i));
        }
    }
}