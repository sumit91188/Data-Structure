package com.sdeo.binarySearchTree;

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
class BinarySearchTreeTest {

    BinarySearchTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new BinarySearchTree<Integer>();
    }

    @AfterEach
    void tearDown() {
        tree = null;
    }

    private void addElements() {
        tree.add(60);
        tree.add(10);
        tree.add(90);
        tree.add(30);
        tree.add(20);
        tree.add(50);
        tree.add(80);
        tree.add(40);
        tree.add(70);
    }

    @Test
    void isEmpty() {
        assertTrue(tree.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, tree.size());
    }

    @Test
    void add() {
        addElements();

        assertFalse(tree.isEmpty());
        assertEquals(9, tree.size());
    }

    @Test
    void remove() {
        assertFalse(tree.remove(10));

        addElements();
        assertTrue(tree.remove(10));
    }

    @Test
    void height() {
        assertEquals(0, tree.height());

        addElements();
        assertEquals(5, tree.height());
    }

    @Test
    void contains() {
        assertFalse(tree.contains(10));

        addElements();
        assertTrue(tree.contains(10));
    }

    @Test
    void traverse_preOrderTraversal() {
        addElements();

        Iterator iterator = tree.traverse(BinaryTreeTraversalOrderEnum.PRE_ORDER);
        List<Integer> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add((Integer) iterator.next());
        }
        assertEquals("[60, 10, 30, 20, 50, 40, 90, 80, 70]",list.toString()); //1st root element
    }

    @Test
    void traverse_inOrderTraversal() {
        addElements();

        Iterator iterator = tree.traverse(BinaryTreeTraversalOrderEnum.IN_ORDER);
        List<Integer> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add((Integer) iterator.next());
        }
        assertEquals("[10, 20, 30, 40, 50, 60, 70, 80, 90]",list.toString()); //in sorted order
    }

    @Test
    void traverse_postOrderTraversal() {
        addElements();

        Iterator iterator = tree.traverse(BinaryTreeTraversalOrderEnum.POST_ORDER);
        List<Integer> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add((Integer) iterator.next());
        }
        assertEquals("[20, 40, 50, 30, 10, 70, 80, 90, 60]",list.toString());  //last element root
    }

    @Test
    void traverse_levelOrderTraversal() {
        addElements();

        Iterator iterator = tree.traverse(BinaryTreeTraversalOrderEnum.LEVEL_ORDER);
        List<Integer> list = new ArrayList<>();

        while (iterator.hasNext()) {
            list.add((Integer) iterator.next());
        }
        assertEquals("[60, 10, 90, 30, 80, 20, 50, 70, 40]",list.toString());
    }
}