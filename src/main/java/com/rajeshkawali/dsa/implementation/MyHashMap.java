package com.rajeshkawali.dsa.implementation;

import java.util.LinkedList;

public class MyHashMap<K, V> {

    // Default capacity and load factor
    private static final int DEFAULT_CAPACITY = 16;
    private static final float LOAD_FACTOR = 0.75f;

    // Bucket array (each bucket is a linked list of entries)
    private LinkedList<Entry<K, V>>[] buckets;
    private int size;

    // Entry class to store key-value pairs
    static class Entry<K, V> {
        K key;
        V value;
        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Constructor
    @SuppressWarnings("unchecked")
    public MyHashMap() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    /* hash
     * Purpose: Compute bucket index for a key.
     * Time: O(1)
     */
    private int hash(K key) {
        return (key == null ? 0 : key.hashCode() & 0x7fffffff) % buckets.length;
    }

    /* put
     * Purpose: Insert or update key-value pair.
     * Time: O(1) average, O(n) worst-case (all keys in one bucket)
     * Space: O(1)
     */
    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) buckets[index] = new LinkedList<>();
        for (Entry<K, V> entry : buckets[index]) {
            if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
                entry.value = value; // update existing
                return;
            }
        }
        buckets[index].add(new Entry<>(key, value));
        size++;
        if (size > buckets.length * LOAD_FACTOR) resize();
    }

    /* get
     * Purpose: Retrieve value for a key.
     * Time: O(1) average, O(n) worst-case
     * Space: O(1)
     */
    public V get(K key) {
        int index = hash(key);
        if (buckets[index] == null) return null;
        for (Entry<K, V> entry : buckets[index]) {
            if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
                return entry.value;
            }
        }
        return null;
    }

    /* remove
     * Purpose: Remove key-value pair.
     * Time: O(1) average, O(n) worst-case
     * Space: O(1)
     */
    public boolean remove(K key) {
        int index = hash(key);
        if (buckets[index] == null) return false;
        for (Entry<K, V> entry : buckets[index]) {
            if ((key == null && entry.key == null) || (key != null && key.equals(entry.key))) {
                buckets[index].remove(entry);
                size--;
                return true;
            }
        }
        return false;
    }

    /* containsKey
     * Purpose: Check if key exists.
     * Time: O(1) average
     */
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /* size
     * Purpose: Return number of key-value pairs.
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
     * Time: O(n)
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
    }

    /* resize
     * Purpose: Double bucket array size and rehash entries.
     * Time: O(n)
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Entry<K, V>>[] oldBuckets = buckets;
        buckets = new LinkedList[oldBuckets.length * 2];
        size = 0;
        for (LinkedList<Entry<K, V>> bucket : oldBuckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    put(entry.key, entry.value); // rehash
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
        for (LinkedList<Entry<K, V>> bucket : buckets) {
            if (bucket != null) {
                for (Entry<K, V> entry : bucket) {
                    if (!first) sb.append(", ");
                    sb.append(entry.key).append("=").append(entry.value);
                    first = false;
                }
            }
        }
        sb.append("}");
        return sb.toString();
    }

    // Demo
    public static void main(String[] args) {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        System.out.println("Initial: " + map);

        map.put("B", 99); // update
        System.out.println("After update B: " + map);

        System.out.println("Get C: " + map.get("C"));
        System.out.println("Contains A? " + map.containsKey("A"));

        map.remove("A");
        System.out.println("After remove A: " + map);

        System.out.println("Size: " + map.size());
        map.clear();
        System.out.println("After clear: " + map + ", isEmpty=" + map.isEmpty());
    }
}
