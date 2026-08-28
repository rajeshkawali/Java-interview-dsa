package com.rajeshkawali.dsa.implementation;

import java.util.LinkedList;

public class MyHashSet<T> {

    // Default capacity and load factor
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    private LinkedList<T>[] buckets; // array of chains
    private int size;                // number of elements

    // Constructor
    @SuppressWarnings("unchecked")
    public MyHashSet() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    /* hash
     * Purpose: Compute bucket index for element.
     * Time: O(1)
     */
    private int hash(T element) {
        return (element == null ? 0 : element.hashCode() & 0x7fffffff) % buckets.length;
    }

    /* add
     * Purpose: Insert element if not already present.
     * Time: O(1) average, O(n) worst-case (all elements in one bucket)
     * Space: O(1)
     */
    public boolean add(T element) {
        int index = hash(element);
        if (buckets[index] == null) buckets[index] = new LinkedList<>();
        if (buckets[index].contains(element)) return false; // no duplicates
        buckets[index].add(element);
        size++;
        if (size > buckets.length * LOAD_FACTOR) resize();
        return true;
    }

    /* contains
     * Purpose: Check if element exists.
     * Time: O(1) average, O(n) worst-case
     * Space: O(1)
     */
    public boolean contains(T element) {
        int index = hash(element);
        return buckets[index] != null && buckets[index].contains(element);
    }

    /* remove
     * Purpose: Remove element if present.
     * Time: O(1) average, O(n) worst-case
     * Space: O(1)
     */
    public boolean remove(T element) {
        int index = hash(element);
        if (buckets[index] == null) return false;
        boolean removed = buckets[index].remove(element);
        if (removed) size--;
        return removed;
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
     * Time: O(n)
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    /* resize
     * Purpose: Double bucket array size and rehash elements.
     * Time: O(n)
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<T>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;
        for (LinkedList<T> bucket : oldBuckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    add(element); // rehash
                }
            }
        }
    }

    /* toString
     * Purpose: String representation.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;
        for (LinkedList<T> bucket : buckets) {
            if (bucket != null) {
                for (T element : bucket) {
                    if (!first) sb.append(", ");
                    sb.append(element);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // Demo
    public static void main(String[] args) {
        MyHashSet<Integer> set = new MyHashSet<>();
        set.add(10);
        set.add(20);
        set.add(30);
        System.out.println("Initial: " + set);

        set.add(20); // duplicate ignored
        System.out.println("After adding duplicate 20: " + set);

        System.out.println("Contains 30? " + set.contains(30));
        System.out.println("Size: " + set.size());

        set.remove(10);
        System.out.println("After remove(10): " + set);

        set.clear();
        System.out.println("After clear: " + set + ", isEmpty=" + set.isEmpty());
    }
}

/*
Notes:
No duplicates allowed (like HashSet).
Separate chaining with linked lists handles collisions.
Resize doubles capacity when load factor exceeded.
Average time complexity: O(1) for add, remove, contains.
Worst-case: O(n) if all elements hash to same bucket.

*/