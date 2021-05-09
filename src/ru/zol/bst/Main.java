package ru.zol.bst;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(5);
        bst.add(2);
        bst.add(3);
        bst.add(4);
        bst.add(1);
        bst.add(6);

        bst.breathFirstSearch();
    }
}
