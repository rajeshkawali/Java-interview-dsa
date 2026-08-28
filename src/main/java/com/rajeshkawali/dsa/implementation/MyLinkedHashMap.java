package com.rajeshkawali.dsa.implementation;
import java.util.*;

public class MyLinkedHashMap<K, V> {
    private static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;   // for hash bucket
        Entry<K, V> before; // for linked list
        Entry<K, V> after;  // for linked list
        Entry(K key, V value) { this.key = key; this.value = value; }
    }

    private Entry<K, V>[] table;
    private int capacity = 16;
    private int size = 0;
    private Entry<K, V> head, tail;

    @SuppressWarnings("unchecked")
    public MyLinkedHashMap() {
        table = new Entry[capacity];
    }

    private int hash(K key) {
        return (key == null ? 0 : key.hashCode() & 0x7fffffff) % capacity;
    }

    public void put(K key, V value) {
        int index = hash(key);
        Entry<K, V> cur = table[index];
        while (cur != null) {
            if ((key == null && cur.key == null) || (key != null && key.equals(cur.key))) {
                cur.value = value; // update
                return;
            }
            cur = cur.next;
        }
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        linkLast(newEntry);
        size++;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> cur = table[index];
        while (cur != null) {
            if ((key == null && cur.key == null) || (key != null && key.equals(cur.key))) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean containsKey(K key) { return get(key) != null; }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> cur = table[index], prev = null;
        while (cur != null) {
            if ((key == null && cur.key == null) || (key != null && key.equals(cur.key))) {
                if (prev == null) table[index] = cur.next;
                else prev.next = cur.next;
                unlink(cur);
                size--;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    public int size() { return size; }

    public void clear() {
        Arrays.fill(table, null);
        head = tail = null;
        size = 0;
    }

    // Maintain insertion order
    private void linkLast(Entry<K, V> e) {
        if (tail == null) head = tail = e;
        else {
            tail.after = e;
            e.before = tail;
            tail = e;
        }
    }

    private void unlink(Entry<K, V> e) {
        if (e.before != null) e.before.after = e.after;
        else head = e.after;
        if (e.after != null) e.after.before = e.before;
        else tail = e.before;
    }

    public void printOrder() {
        Entry<K, V> cur = head;
        while (cur != null) {
            System.out.print(cur.key + "=" + cur.value + " ");
            cur = cur.after;
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        MyLinkedHashMap<String, Integer> map = new MyLinkedHashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        map.put("B", 99); // update
        map.printOrder(); // A=1 B=99 C=3

        System.out.println("Get C: " + map.get("C"));
        map.remove("A");
        map.printOrder(); // B=99 C=3
        System.out.println("Size: " + map.size());
    }
}

/*

LinkedHashMap:
Underlying structure: Hash table + doubly linked list.
Order preservation: Maintains insertion order by default; can be configured for access order (useful for LRU caches).
Performance: Same as HashMap for basic operations (average O(1)).
Nulls: Allows one null key and multiple null values.
*/