package com.sdeo.hashTable;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Sumit Deo
 */
class HashTableSeparateChainingTest {

    HashTableSeparateChaining<String, Integer> table;

    @BeforeEach
    void setUp() {
        table = new HashTableSeparateChaining<>();
    }

    @AfterEach
    void tearDown() {
        table = null;
    }

    private void addData() {
        table.put("Sumit", 32);
        table.put("Sneha", 32);
        table.put("Ujju", 31);
        table.put("Apoo", 31);
        table.put("Sampada", 33);
        table.put("Shreyas", 35);
        table.put("Shruti", 29);
    }

    @Test
    void size() {
        assertEquals(0, table.size());

        addData();
        assertEquals(7, table.size());
    }

    @Test
    void isEmpty() {
        assertTrue(table.isEmpty());

        addData();
        assertFalse(table.isEmpty());
    }

    @Test
    void clear() {
        addData();
        assertFalse(table.isEmpty());

        table.clear();
        assertTrue(table.isEmpty());
    }

    @Test
    void containsKey() {
        addData();

        assertTrue(table.containsKey("Sumit"));
        assertFalse(table.containsKey("Inder"));
    }

    @Test
    void put() {
        addData();

        assertEquals(32, table.get("Sumit"));

        table.put("Sumit", 28);
        assertEquals(28, table.get("Sumit"));
    }

    @Test
    void get() {
        addData();

        assertEquals(32, table.get("Sneha"));
    }

    @Test
    void remove() {
        addData();
        assertEquals(32, table.get("Sumit"));

        table.remove("Sumit");
        assertNull(table.get("Sumit"));
    }

    @Test
    void keys() {
        addData();
        List<String> keys = table.keys();

        assertEquals("[Ujju, Sampada, Shruti, Sumit, Sneha, Apoo, Shreyas]", keys.toString());
    }

    @Test
    void values() {
        addData();
        List<Integer> values = table.values();

        assertEquals("[31, 33, 29, 32, 32, 31, 35]", values.toString());
    }

    @Test
    void iterator() {
        addData();
        Iterator iterator = table.iterator();

        List<String> keys = new LinkedList<>();
        while (iterator.hasNext()) {
            keys.add((String) iterator.next());
        }
        assertEquals("[Ujju, Sampada, Shruti, Sumit, Sneha, Apoo, Shreyas]", keys.toString());
    }

    @Test
    void testToString() {
        addData();

        assertEquals("{Entry{key=Ujju, value=31}, Entry{key=Sampada, value=33}, Entry{key=Shruti, value=29}, " +
                        "Entry{key=Sumit, value=32}, Entry{key=Sneha, value=32}, Entry{key=Apoo, value=31}, " +
                        "Entry{key=Shreyas, value=35}, }",
                table.toString());
    }
}