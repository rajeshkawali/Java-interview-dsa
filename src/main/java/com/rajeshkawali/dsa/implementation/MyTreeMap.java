package com.rajeshkawali.dsa.implementation;

import java.util.NoSuchElementException;

public class MyTreeMap<K extends Comparable<K>, V> {

    // Node class for BST
    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> left, right;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node<K, V> root;
    private int size;

    public MyTreeMap() {
        root = null;
        size = 0;
    }

    /* put
     * Purpose: Insert or update key-value pair.
     * Time: O(log n) average, O(n) worst-case (unbalanced tree)
     * Space: O(1)
     */
    public void put(K key, V value) {
        root = putRec(root, key, value);
    }

    private Node<K, V> putRec(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = putRec(node.left, key, value);
        else if (cmp > 0) node.right = putRec(node.right, key, value);
        else node.value = value; // update
        return node;
    }

    /* get
     * Purpose: Retrieve value for a key.
     * Time: O(log n) average, O(n) worst-case
     */
    public V get(K key) {
        Node<K, V> cur = root;
        while (cur != null) {
            int cmp = key.compareTo(cur.key);
            if (cmp == 0) return cur.value;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    /* containsKey
     * Purpose: Check if key exists.
     * Time: O(log n) average
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* remove
     * Purpose: Remove key-value pair.
     * Time: O(log n) average, O(n) worst-case
     */
    public void remove(K key) {
        root = removeRec(root, key);
    }

    private Node<K, V> removeRec(Node<K, V> node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = removeRec(node.left, key);
        else if (cmp > 0) node.right = removeRec(node.right, key);
        else {
            size--;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<K, V> min = findMin(node.right);
            node.key = min.key;
            node.value = min.value;
            node.right = removeRec(node.right, min.key);
        }
        return node;
    }

    private Node<K, V> findMin(Node<K, V> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /* firstKey
     * Purpose: Return smallest key.
     * Time: O(log n) average
     */
    public K firstKey() {
        if (root == null) throw new NoSuchElementException();
        Node<K, V> cur = root;
        while (cur.left != null) cur = cur.left;
        return cur.key;
    }

    /* lastKey
     * Purpose: Return largest key.
     * Time: O(log n) average
     */
    public K lastKey() {
        if (root == null) throw new NoSuchElementException();
        Node<K, V> cur = root;
        while (cur.right != null) cur = cur.right;
        return cur.key;
    }

    /* size
     * Purpose: Return number of entries.
     * Time: O(1)
     */
    public int size() { return size; }

    /* isEmpty
     * Purpose: True if no entries.
     * Time: O(1)
     */
    public boolean isEmpty() { return size == 0; }

    /* clear
     * Purpose: Remove all entries.
     * Time: O(1)
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /* inorderTraversal
     * Purpose: Print entries in sorted order by key.
     * Time: O(n)
     */
    public void inorderTraversal() {
        inorder(root);
        System.out.println();
    }

    private void inorder(Node<K, V> node) {
        if (node == null) return;
        inorder(node.left);
        System.out.print(node.key + "=" + node.value + " ");
        inorder(node.right);
    }

    // Demo
    public static void main(String[] args) {
        MyTreeMap<String, Integer> map = new MyTreeMap<>();
        map.put("C", 3);
        map.put("A", 1);
        map.put("B", 2);
        map.put("D", 4);

        System.out.print("Inorder (sorted): ");
        map.inorderTraversal(); // A=1 B=2 C=3 D=4

        System.out.println("Get B: " + map.get("B"));
        System.out.println("Contains C? " + map.containsKey("C"));
        System.out.println("First key: " + map.firstKey());
        System.out.println("Last key: " + map.lastKey());

        map.remove("C");
        System.out.print("After remove(C): ");
        map.inorderTraversal();

        System.out.println("Size: " + map.size());
    }
}
