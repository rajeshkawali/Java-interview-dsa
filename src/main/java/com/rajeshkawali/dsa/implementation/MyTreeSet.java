package com.rajeshkawali.dsa.implementation;

import java.util.NoSuchElementException;

public class MyTreeSet<T extends Comparable<T>> {

    // Node class for BST
    private static class Node<T> {
        T value;
        Node<T> left, right;
        Node(T value) { this.value = value; }
    }

    private Node<T> root;
    private int size;

    public MyTreeSet() {
        root = null;
        size = 0;
    }

    /* add
     * Purpose: Insert element if not already present.
     * Time: O(log n) average (balanced tree), O(n) worst-case (unbalanced)
     * Space: O(1)
     */
    public boolean add(T value) {
        if (root == null) {
            root = new Node<>(value);
            size++;
            return true;
        }
        Node<T> cur = root, parent = null;
        while (cur != null) {
            int cmp = value.compareTo(cur.value);
            if (cmp == 0) return false; // duplicate
            parent = cur;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        if (value.compareTo(parent.value) < 0) parent.left = new Node<>(value);
        else parent.right = new Node<>(value);
        size++;
        return true;
    }

    /* contains
     * Purpose: Check if element exists.
     * Time: O(log n) average, O(n) worst-case
     */
    public boolean contains(T value) {
        Node<T> cur = root;
        while (cur != null) {
            int cmp = value.compareTo(cur.value);
            if (cmp == 0) return true;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return false;
    }

    /* remove
     * Purpose: Remove element if present.
     * Time: O(log n) average, O(n) worst-case
     */
    public boolean remove(T value) {
        root = removeRec(root, value);
        return true;
    }

    private Node<T> removeRec(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = value.compareTo(node.value);
        if (cmp < 0) node.left = removeRec(node.left, value);
        else if (cmp > 0) node.right = removeRec(node.right, value);
        else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<T> min = findMin(node.right);
            node.value = min.value;
            node.right = removeRec(node.right, min.value);
        }
        return node;
    }

    private Node<T> findMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /* first
     * Purpose: Return smallest element.
     * Time: O(log n) average
     */
    public T first() {
        if (root == null) throw new NoSuchElementException();
        Node<T> cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.value;
    }

    /* last
     * Purpose: Return largest element.
     * Time: O(log n) average
     */
    public T last() {
        if (root == null) throw new NoSuchElementException();
        Node<T> cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.value;
    }

    /* size
     * Purpose: Return number of elements.
     * Time: O(1)
     */
    public int size() { return size; }

    /* isEmpty
     * Purpose: True if no elements.
     * Time: O(1)
     */
    public boolean isEmpty() { return size == 0; }

    /* clear
     * Purpose: Remove all elements.
     * Time: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /* inorderTraversal
     * Purpose: Print elements in sorted order.
     * Time: O(n)
     */
    public void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<T> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.value + " ");
        inorder(node.right);
    }

    // Demo
    public static void main(String[] args) {
        MyTreeSet<Integer> set = new MyTreeSet<>();
        set.add(50);
        set.add(20);
        set.add(70);
        set.add(10);
        set.add(30);
        set.add(60);
        set.add(80);

        System.out.print("Inorder (sorted): ");
        set.inorderTraversal(); // 10 20 30 50 60 70 80

        System.out.println("Contains 30? " + set.contains(30));
        System.out.println("First: " + set.first());
        System.out.println("Last: " + set.last());

        set.remove(20);
        System.out.print("After remove(20): ");
        set.inorderTraversal();

        System.out.println("Size: " + set.size());
    }
}
