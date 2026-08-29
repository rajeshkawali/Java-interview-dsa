package com.rajeshkawali.dsa.binarytree;

/**
 * Binary Search Tree implementation with insertion, search, and deletion.
 *
 * Logic Explanation:
 * ------------------
 * - BST property: Left child < Root < Right child.
 * - Insert: Recursively place node in correct position.
 * - Search: Traverse left/right depending on value.
 * - Delete:
 *   1. Node with no child → remove directly.
 *   2. Node with one child → replace with child.
 *   3. Node with two children → replace with inorder successor (smallest in right subtree).
 *
 * Time Complexity:
 * - Insert/Search/Delete: O(h) (h = height of tree)
 * - Balanced BST: O(log n)
 * - Worst case (skewed tree): O(n)
 */

public class BinarySearchTree {

    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    Node root;

    /** Insert node */
    public Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.data) node.left = insert(node.left, key);
        else if (key > node.data) node.right = insert(node.right, key);
        return node;
    }

    /** Search node */
    public boolean search(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        if (key < node.data) return search(node.left, key);
        return search(node.right, key);
    }

    /** Delete node */
    public Node delete(Node node, int key) {
        if (node == null) return null;

        if (key < node.data) node.left = delete(node.left, key);
        else if (key > node.data) node.right = delete(node.right, key);
        else {
            // Case 1: No child
            if (node.left == null && node.right == null) return null;
            // Case 2: One child
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            // Case 3: Two children
            Node successor = minValue(node.right);
            node.data = successor.data;
            node.right = delete(node.right, successor.data);
        }
        return node;
    }

    /** Find minimum value node */
    private Node minValue(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /** Inorder traversal (sorted order for BST) */
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    // Demo
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.root = bst.insert(bst.root, 50);
        bst.insert(bst.root, 30);
        bst.insert(bst.root, 70);
        bst.insert(bst.root, 20);
        bst.insert(bst.root, 40);
        bst.insert(bst.root, 60);
        bst.insert(bst.root, 80);

        System.out.print("Inorder (sorted): ");
        bst.inorder(bst.root); // 20 30 40 50 60 70 80
        System.out.println();

        System.out.println("Search 40: " + bst.search(bst.root, 40)); // true
        System.out.println("Search 90: " + bst.search(bst.root, 90)); // false

        bst.root = bst.delete(bst.root, 50);
        System.out.print("Inorder after deleting 50: ");
        bst.inorder(bst.root); // 20 30 40 60 70 80
    }
}
