package com.rajeshkawali.dsa.implementation;
import java.util.LinkedHashMap;
import java.util.Iterator;

public class MyLinkedHashSet<T> implements Iterable<T> {
    private static final Object PRESENT = new Object();
    private LinkedHashMap<T, Object> map;

    public MyLinkedHashSet() {
        map = new LinkedHashMap<>();
    }

    // Add element
    public boolean add(T element) {
        return map.put(element, PRESENT) == null;
    }

    // Remove element
    public boolean remove(T element) {
        return map.remove(element) == PRESENT;
    }

    // Check if element exists
    public boolean contains(T element) {
        return map.containsKey(element);
    }

    // Size
    public int size() {
        return map.size();
    }

    // Is empty
    public boolean isEmpty() {
        return map.isEmpty();
    }

    // Clear
    public void clear() {
        map.clear();
    }

    // Iterator (preserves insertion order)
    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    // Print elements in insertion order
    public void printOrder() {
        for (T element : this) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    // Demo
    public static void main(String[] args) {
        MyLinkedHashSet<String> set = new MyLinkedHashSet<>();
        set.add("A");
        set.add("B");
        set.add("C");
        set.add("B"); // duplicate ignored

        System.out.print("Insertion order: ");
        set.printOrder(); // A B C

        System.out.println("Contains B? " + set.contains("B"));
        set.remove("A");
        System.out.print("After remove(A): ");
        set.printOrder(); // B C

        System.out.println("Size: " + set.size());
    }
}
/*
LinkedHashSet = HashSet + LinkedHashMap.
Preserves insertion order while ensuring uniqueness.
Operations are O(1) average, with predictable iteration order.
Useful when you need a set with stable iteration order (e.g., caching, ordered collections).
*/