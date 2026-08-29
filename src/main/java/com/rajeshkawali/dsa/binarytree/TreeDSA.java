package com.rajeshkawali.dsa.binarytree;

import java.util.*;

/**
 * Complete Binary Tree + BST implementation with core methods.
 *
 * Includes:
 * - Traversals: inorder, preorder, postorder, level order
 * - BST operations: insert, search, delete
 * - Advanced: height, diameter, lowest common ancestor (LCA), balanced check
 *
 * Time Complexity:
 * - Traversals: O(n)
 * - Insert/Search/Delete (BST): O(h) [O(log n) if balanced, O(n) worst case]
 * - Height/Diameter/LCA: O(n)
 * - Balanced check: O(n)
 *
 * Space Complexity:
 * - Traversals: O(h) recursion stack, O(n) for level order queue
 * - Other methods: O(h) recursion stack
 */

public class TreeDSA {

    // Node class
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    Node root;

    // ---------------- Traversals ----------------

    /** Inorder Traversal: Left → Root → Right */
    public void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.data + " ");
        inorder(node.right);
    }

    /** Preorder Traversal: Root → Left → Right */
    public void preorder(Node node) {
        if (node == null) return;
        System.out.print(node.data + " ");
        preorder(node.left);
        preorder(node.right);
    }

    /** Postorder Traversal: Left → Right → Root */
    public void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.data + " ");
    }

    /** Level Order Traversal (Breadth First Search) */
    public void levelOrder(Node node) {
        if (node == null) return;
        Queue<Node> q = new LinkedList<>();
        q.add(node);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            System.out.print(cur.data + " ");
            if (cur.left != null) q.add(cur.left);
            if (cur.right != null) q.add(cur.right);
        }
    }

    // ---------------- BST Operations ----------------

    /** Insert node in BST */
    public Node insert(Node node, int key) {
        if (node == null) return new Node(key);
        if (key < node.data) node.left = insert(node.left, key);
        else if (key > node.data) node.right = insert(node.right, key);
        return node;
    }

    /** Search node in BST */
    public boolean search(Node node, int key) {
        if (node == null) return false;
        if (node.data == key) return true;
        if (key < node.data) return search(node.left, key);
        return search(node.right, key);
    }

    /** Delete node in BST */
    public Node delete(Node node, int key) {
        if (node == null) return null;
        if (key < node.data) node.left = delete(node.left, key);
        else if (key > node.data) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
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

    // ---------------- Advanced Methods ----------------

    /** Height of tree */
    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /** Diameter of tree (longest path between two nodes) */
    public int diameter(Node node) {
        if (node == null) return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        int ld = diameter(node.left);
        int rd = diameter(node.right);
        return Math.max(lh + rh + 1, Math.max(ld, rd));
    }

    /** Lowest Common Ancestor (LCA) */
    public Node lca(Node node, int n1, int n2) {
        if (node == null) return null;
        if (node.data == n1 || node.data == n2) return node;
        Node left = lca(node.left, n1, n2);
        Node right = lca(node.right, n1, n2);
        if (left != null && right != null) return node;
        return (left != null) ? left : right;
    }

    /** Check if tree is balanced */
    public boolean isBalanced(Node node) {
        return checkBalance(node) != -1;
    }

    private int checkBalance(Node node) {
        if (node == null) return 0;
        int lh = checkBalance(node.left);
        if (lh == -1) return -1;
        int rh = checkBalance(node.right);
        if (rh == -1) return -1;
        if (Math.abs(lh - rh) > 1) return -1;
        return 1 + Math.max(lh, rh);
    }

    // ---------------- Demo ----------------

    public static void main(String[] args) {
        TreeDSA tree = new TreeDSA();

        // Build BST
        tree.root = tree.insert(tree.root, 50);
        tree.insert(tree.root, 30);
        tree.insert(tree.root, 70);
        tree.insert(tree.root, 20);
        tree.insert(tree.root, 40);
        tree.insert(tree.root, 60);
        tree.insert(tree.root, 80);

        System.out.print("Inorder (sorted): ");
        tree.inorder(tree.root); // 20 30 40 50 60 70 80
        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder(tree.root); // 50 30 20 40 70 60 80
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(tree.root); // 20 40 30 60 80 70 50
        System.out.println();

        System.out.print("Level Order: ");
        tree.levelOrder(tree.root); // 50 30 70 20 40 60 80
        System.out.println();

        System.out.println("Search 40: " + tree.search(tree.root, 40)); // true
        System.out.println("Search 90: " + tree.search(tree.root, 90)); // false

        tree.root = tree.delete(tree.root, 50);
        System.out.print("Inorder after deleting 50: ");
        tree.inorder(tree.root); // 20 30 40 60 70 80
        System.out.println();

        System.out.println("Height: " + tree.height(tree.root)); // 3
        System.out.println("Diameter: " + tree.diameter(tree.root)); // 5
        System.out.println("Is Balanced? " + tree.isBalanced(tree.root)); // true

        Node lcaNode = tree.lca(tree.root, 20, 40);
        System.out.println("LCA of 20 and 40: " + (lcaNode != null ? lcaNode.data : "null"));
    }
}
