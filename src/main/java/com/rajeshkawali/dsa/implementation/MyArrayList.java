package com.rajeshkawali.dsa.implementation;

import java.util.Arrays;

public class MyArrayList<T> {

    private Object[] data; // backing array
    private int size;      // number of elements

    // Default capacity
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public MyArrayList() {
        data = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public MyArrayList(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be > 0");
        data = new Object[capacity];
        size = 0;
    }

    /* add
     * Purpose: Append element at end.
     * Time: O(1) amortized (O(n) if resize)  Space: O(1)
     */
    public void add(T element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    /* addAt
     * Purpose: Insert element at index.
     * Time: O(n) (shift elements)  Space: O(1)
     */
    public void addAt(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        ensureCapacity(size + 1);
        System.arraycopy(data, index, data, index + 1, size - index);
        data[index] = element;
        size++;
    }

    /* get
     * Purpose: Return element at index.
     * Time: O(1)  Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        return (T) data[index];
    }

    /* set
     * Purpose: Replace element at index.
     * Time: O(1)  Space: O(1)
     */
    public void set(int index, T element) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        data[index] = element;
    }

    /* remove
     * Purpose: Remove element at index.
     * Time: O(n) (shift elements)  Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T remove(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        T removed = (T) data[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) System.arraycopy(data, index + 1, data, index, numMoved);
        data[--size] = null; // help GC
        return removed;
    }

    /* removeElement
     * Purpose: Remove first occurrence of element.
     * Time: O(n)  Space: O(1)
     */
    public boolean removeElement(T element) {
        int idx = indexOf(element);
        if (idx == -1) return false;
        remove(idx);
        return true;
    }

    /* indexOf
     * Purpose: Return index of first occurrence.
     * Time: O(n)  Space: O(1)
     */
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
                return i;
            }
        }
        return -1;
    }

    /* contains
     * Purpose: True if element exists.
     * Time: O(n)  Space: O(1)
     */
    public boolean contains(T element) {
        return indexOf(element) != -1;
    }

    /* clear
     * Purpose: Remove all elements.
     * Time: O(n) (null out)  Space: O(1)
     */
    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    /* size
     * Purpose: Return number of elements.
     * Time: O(1)  Space: O(1)
     */
    public int size() { return size; }

    /* isEmpty
     * Purpose: True if no elements.
     * Time: O(1)  Space: O(1)
     */
    public boolean isEmpty() { return size == 0; }

    /* ensureCapacity
     * Purpose: Grow backing array if needed.
     * Time: O(n) when resize, amortized O(1) per add  Space: O(n)
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /* trimToSize
     * Purpose: Shrink backing array to current size.
     * Time: O(n)  Space: O(n)
     */
    public void trimToSize() {
        if (size < data.length) {
            data = Arrays.copyOf(data, size);
        }
    }

    /* toString
     * Purpose: String representation.
     */
    @Override
    public String toString() {
        Object[] arr = Arrays.copyOf(data, size);
        return Arrays.toString(arr);
    }

    // Demo
    public static void main(String[] args) {
        MyArrayList<Integer> arr = new MyArrayList<>();
        arr.add(10);
        arr.add(20);
        arr.add(30);
        System.out.println("Initial: " + arr);

        arr.addAt(1, 99);
        System.out.println("After insertAt(1,99): " + arr);

        arr.remove(2);
        System.out.println("After remove(2): " + arr);

        arr.set(1, 55);
        System.out.println("After set(1,55): " + arr);

        System.out.println("Contains 30? " + arr.contains(30));
        System.out.println("Index of 55: " + arr.indexOf(55));

        arr.clear();
        System.out.println("After clear: " + arr + ", size=" + arr.size());
    }
}

/*

Notes:
Dynamic resizing handled by ensureCapacity.
TrimToSize shrinks array to save memory.
Add, remove, set, get behave like java.util.ArrayList.
Search utilities (indexOf, contains) included.
Clear, isEmpty, size for housekeeping.

*/