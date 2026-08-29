package com.rajeshkawali.dsa.binarytree;

import java.util.*;

/**
 * Binary Tree implementation with basic traversals.
 *
 * Logic Explanation:
 * ------------------
 * - Each node has data, left child, and right child.
 * - Traversals:
 *   1. Inorder (Left → Root → Right)
 *   2. Preorder (Root → Left → Right)
 *   3. Postorder (Left → Right → Root)
 *   4. Level Order (Breadth First using Queue)
 *
 * Time Complexity:
 * - All traversals: O(n) (visit each node once)
 * Space Complexity:
 * - O(h) for recursion stack (h = height of tree)
 * - O(n) for level order (queue)
 */

public class BinaryTreeBasics {

    // Node class
    static class Node {
        int data;
        Node left, right;
        Node(int data) { this.data = data; }
    }

    Node root;

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

    // Demo
    public static void main(String[] args) {
        BinaryTreeBasics tree = new BinaryTreeBasics();
        tree.root = new Node(1);
        tree.root.left = new Node(2);
        tree.root.right = new Node(3);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(5);

        System.out.print("Inorder: ");
        tree.inorder(tree.root); // 4 2 5 1 3
        System.out.println();

        System.out.print("Preorder: ");
        tree.preorder(tree.root); // 1 2 4 5 3
        System.out.println();

        System.out.print("Postorder: ");
        tree.postorder(tree.root); // 4 5 2 3 1
        System.out.println();

        System.out.print("Level Order: ");
        tree.levelOrder(tree.root); // 1 2 3 4 5
    }
}
