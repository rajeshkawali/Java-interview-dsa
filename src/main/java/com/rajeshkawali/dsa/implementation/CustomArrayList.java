package com.rajeshkawali.dsa.implementation;

/**
 * Custom implementation of ArrayList without using inbuilt methods like System.arraycopy or Arrays.copyOf.
 *
 * Logic Explanation:
 * ------------------
 * - We maintain a backing array (`data`) and track the number of elements (`size`) and current capacity (`capacity`).
 * - When adding elements, if the array is full, we manually create a new array with double capacity and copy elements.
 * - Insertions at a specific index require shifting elements to the right manually.
 * - Deletions require shifting elements to the left manually.
 * - Search operations (indexOf, contains) are linear scans.
 * - Utility methods like clear, size, isEmpty are straightforward.
 *
 * Time & Space Complexity Summary:
 * --------------------------------
 * - add(E e): O(1) amortized, O(n) when resize; Space O(1)
 * - addAt(int i, E e): O(n) (shift elements); Space O(1)
 * - get(int i): O(1); Space O(1)
 * - set(int i, E e): O(1); Space O(1)
 * - remove(int i): O(n) (shift elements); Space O(1)
 * - indexOf(E e): O(n); Space O(1)
 * - contains(E e): O(n); Space O(1)
 * - clear(): O(n); Space O(1)
 * - size(): O(1); Space O(1)
 * - isEmpty(): O(1); Space O(1)
 * - resize(): O(n); Space O(n)
 */

public class CustomArrayList<T> {

    private Object[] data;   // backing array
    private int size;        // number of elements
    private int capacity;    // current capacity

    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public CustomArrayList() {
        capacity = DEFAULT_CAPACITY;
        data = new Object[capacity];
        size = 0;
    }

    /**
     * Add element at the end.
     * Time: O(1) amortized, O(n) if resize occurs
     * Space: O(1)
     */
    public void add(T element) {
        if (size == capacity) resize();
        data[size++] = element;
    }

    /**
     * Insert element at a specific index.
     * Logic: Shift elements to the right manually, then insert.
     * Time: O(n) (due to shifting)
     * Space: O(1)
     */
    public void addAt(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (size == capacity) resize();
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    /**
     * Get element at index.
     * Time: O(1)
     * Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    /**
     * Set element at index.
     * Time: O(1)
     * Space: O(1)
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        data[index] = element;
    }

    /**
     * Remove element at index.
     * Logic: Shift elements to the left manually.
     * Time: O(n)
     * Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T removed = (T) data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        return removed;
    }

    /**
     * Return index of first occurrence of element, or -1 if not found.
     * Time: O(n)
     * Space: O(1)
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Check if element exists.
     * Time: O(n)
     * Space: O(1)
     */
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /**
     * Clear list (remove all elements).
     * Time: O(n)
     * Space: O(1)
     */
    public void clear() {
        for (int i = 0; i < size; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * Return number of elements.
     * Time: O(1)
     * Space: O(1)
     */
    public int size() { return size; }

    /**
     * Check if list is empty.
     * Time: O(1)
     * Space: O(1)
     */
    public boolean isEmpty() { return size == 0; }

    /**
     * Resize backing array (double capacity).
     * Logic: Create new array with double capacity and copy elements manually.
     * Time: O(n)
     * Space: O(n)
     */
    private void resize() {
        capacity = capacity * 2;
        Object[] newData = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Print elements in list.
     * Time: O(n)
     * Space: O(1)
     */
    public void printList() {
        System.out.print("[");
        for (int i = 0; i < size; i++) {
            System.out.print(data[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("]");
    }

    // Demo
    public static void main(String[] args) {
        CustomArrayList<Integer> list = new CustomArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.printList(); // [10, 20, 30]

        list.addAt(1, 99);
        list.printList(); // [10, 99, 20, 30]

        list.remove(2);
        list.printList(); // [10, 99, 30]

        System.out.println("Get index 1: " + list.get(1));
        list.set(1, 55);
        list.printList(); // [10, 55, 30]

        System.out.println("Contains 30? " + list.contains(30));
        System.out.println("Index of 55: " + list.indexOf(55));

        list.clear();
        list.printList(); // []
        System.out.println("IsEmpty? " + list.isEmpty());
    }
}
