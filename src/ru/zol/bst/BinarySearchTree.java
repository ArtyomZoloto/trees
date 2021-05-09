package ru.zol.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {
    private Node root;

    public void add(int value) {
        root = insert(root, value);
    }

    public boolean contains(int value) {
        return containsRecursive(root, value);
    }

    public void delete(int value) {
        root = deleteRecursive(root, value);
    }

    /**
     * Traverse:
     * 1. Left sub-tree
     * 2. Root node
     * 3. Right sub-tree
     */
    public void traverseInOrderFromRoot() {
        System.out.println("root value: " + root.getValue());
        traverseInOrder(root);
    }

    private void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.getLeft());
            System.out.println(" " + node.getValue());
            traverseInOrder(node.getRight());
        }
    }

    /**
     * Traversing:
     * 1. Root node
     * 2. Left sub-tree
     * 3. Right sub-tree
     */
    public void traversePreOrderFromRoot() {
        traversePreOrder(root);
    }

    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.println(" " + node.getValue());
            traversePreOrder(node.getLeft());
            traversePreOrder(node.getRight());
        }
    }

    /**
     * Traverse:
     * 1. left sub-tree
     * 2. right sub-tree
     * 3. Root node
     */
    public void traversePostOrderFromRoot() {
        traversePostOrder(root);
    }

    private void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.getLeft());
            traversePostOrder(node.getRight());
            System.out.println(" " + node.getValue());
        }
    }

    private Node insert(Node current, Integer data) {
        if (current == null) {
            return new Node(data);
        } else if (data < current.getValue()) {
            current.setLeft(insert(current.getLeft(), data));
        } else {
            current.setRight(insert(current.getRight(), data));
        }
        return current;
    }

    private boolean containsRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (current.getValue() == value) {
            return true;
        }
        if (current.getValue() < value) {
            return containsRecursive(current.getRight(), value);
        } else {
            return containsRecursive(current.getLeft(), value);
        }
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.getValue()) { //delete code here
            //no childs. This node is a leaf node.
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            //1 child. In this case swap node with child.
            if (current.getLeft() != null && current.getRight() == null) {
                return current.getLeft();
            } else if (current.getLeft() == null && current.getRight() != null) {
                return current.getRight();
            }

            //2 children.
            int smallestValue = findSmallestValueRecursive(current.getRight());
            current.setValue(smallestValue);
            current.setRight(deleteRecursive(current.getRight(), smallestValue));

        }
        if (value < current.getValue()) {
            current.setLeft(deleteRecursive(current.getLeft(), value));
            return current;
        }
        current.setRight(deleteRecursive(current.getRight(), value));
        return current;
    }

    private int findSmallestValueRecursive(Node root) {
        return root.getLeft() == null ? root.getValue() : findSmallestValueRecursive(root.getLeft());
    }

    /**
     * start from the root, go to the levels from left to right
     *
     * @return
     */
    public void breathFirstSearch() {
        traverseLevelOrder();
    }

    private void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        int level = 0;
        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.println("level " + level + ": " + node.getValue());

            if (node.getLeft() != null) {
                nodes.add(node.getLeft());
            }
            if (node.getRight() != null) {
                nodes.add(node.getRight());
            }

        }

    }
}
