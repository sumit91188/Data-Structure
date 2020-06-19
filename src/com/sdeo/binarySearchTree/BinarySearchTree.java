package com.sdeo.binarySearchTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author Sumit Deo
 */
public class BinarySearchTree<T extends Comparable<T>> {

    private int numNodes;
    private Node rootNode;

    private class Node {
        private T data;
        private Node leftNode, rightNode;

        public Node(Node leftNode, T data, Node rightNode) {
            this.data = data;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }
    }

    public BinarySearchTree() {
        numNodes = 0;
        rootNode = null;
    }

    public boolean isEmpty() {
        return numNodes == 0;
    }

    public int size() {
        return numNodes;
    }

    public boolean contains(T elm) {
        return contains(elm, rootNode);
    }

    private boolean contains(T elm, Node rootNode) {
        if (rootNode == null) {
            return false;
        }

        int compareResult = elm.compareTo(rootNode.data);
        if (compareResult < 0) {
            return contains(elm, rootNode.leftNode);
        }
        else if (compareResult > 0) {
            return contains(elm, rootNode.rightNode);
        }
        else {
            return true;
        }
    }

    public boolean add(T elm) {
        if (contains(elm)) {
            return false;
        }
        else {
            rootNode = add(rootNode, elm);
            numNodes++;
            return true;
        }
    }

    private Node add(Node rootNode, T elm) {
        if (rootNode == null) {
            rootNode = new Node(null, elm, null);
        }
        else {
            if (elm.compareTo(rootNode.data) < 0) {
                rootNode.leftNode = add(rootNode.leftNode, elm);
            }
            else {
                rootNode.rightNode = add(rootNode.rightNode, elm);
            }
        }
        return rootNode;
    }

    public boolean remove(T elm) {
        if (contains(elm)) {
            rootNode = remove(rootNode, elm);
            numNodes--;
            return true;
        }
        else {
            return false;
        }
    }

    private Node remove(Node rootNode, T elm) {
        if (rootNode == null) {
            return null;
        }

        int compareResult = elm.compareTo(rootNode.data);
        if (compareResult < 0) {
            rootNode.leftNode = remove(rootNode.leftNode, elm);
        }
        else if (compareResult > 0) {
            rootNode.rightNode = remove(rootNode.rightNode, elm);
        }
        else {
            if (rootNode.leftNode == null) {
                Node rightNode = rootNode.rightNode;

                rootNode.data = null;
                rootNode.rightNode = null;

                return rightNode;
            }
            else if (rootNode.rightNode == null) {
                Node leftNode = rootNode.leftNode;

                rootNode.data = null;
                rootNode.leftNode = null;

                return leftNode;
            }
            else {
                Node minNodeRtSubTree = getMinNodeFromRtSubTree(rootNode.rightNode);

                rootNode.data = minNodeRtSubTree.data;
                rootNode.rightNode = remove(rootNode.rightNode, minNodeRtSubTree.data);
            }
        }

        return rootNode;
    }

    private Node getMinNodeFromRtSubTree(Node rootNode) {
        while (rootNode.leftNode != null) {
            rootNode = rootNode.leftNode;
        }

        return rootNode;
    }

    public int height() {
        return height(rootNode);
    }

    private int height(Node rootNode) {
        if (rootNode == null) {
            return 0;
        }
        else {
            return Math.max(height(rootNode.leftNode), height(rootNode.rightNode)) + 1;
        }
    }

    public Iterator<T> traverse(BinaryTreeTraversalOrderEnum orderEnum) {

        switch (orderEnum) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    private Iterator<T> preOrderTraversal() {
        Stack<Node> stack = new Stack<>();
        stack.push(rootNode);
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return rootNode != null && ! stack.isEmpty();
            }

            @Override
            public T next() {
                Node node = stack.pop();

                if (node.rightNode != null) {
                    stack.push(node.rightNode);
                }

                if (node.leftNode != null) {
                    stack.push(node.leftNode);
                }

                return node.data;
            }
        };
    }

    private Iterator<T> inOrderTraversal() {
        Stack<Node> stack = new Stack<>();
        stack.push(rootNode);
        return new Iterator<T>() {

            Node node = rootNode;

            @Override
            public boolean hasNext() {
                return rootNode != null && ! stack.isEmpty();
            }

            @Override
            public T next() {

                while (node!= null && node.leftNode != null) {
                    stack.push(node.leftNode);
                    node = node.leftNode;
                }

                Node popNode = stack.pop();

                if (popNode.rightNode != null) {
                    stack.push(popNode.rightNode);
                    node = popNode.rightNode;
                }
                return popNode.data;
            }
        };
    }

    private Iterator<T> postOrderTraversal() {
        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();
        stack1.push(rootNode);

        while (! stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.leftNode != null) {
                    stack1.push(node.leftNode);
                }

                if (node.rightNode != null) {
                    stack1.push(node.rightNode);
                }
            }
        }

        return new Iterator<T>() {

            Node node = rootNode;

            @Override
            public boolean hasNext() {
                return rootNode!= null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                return stack2.pop().data;
            }
        };
    }

    private Iterator<T> levelOrderTraversal() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(rootNode);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                return rootNode != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                Node node = queue.poll();
                if (node.leftNode != null) {
                    queue.offer(node.leftNode);
                }

                if (node.rightNode != null) {
                    queue.offer(node.rightNode);
                }

                return node.data;
            }
        };
    }
}
