package com.rajeshkawali.dsa.implementation;

import java.util.EmptyStackException;
import java.util.Arrays;

public class MyStack<T> {

    private Object[] data; // backing array
    private int top;       // index of next insertion
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public MyStack() {
        data = new Object[DEFAULT_CAPACITY];
        top = 0;
    }

    /* push
     * Purpose: Add element to top of stack.
     * Time: O(1) amortized (O(n) if resize)
     * Space: O(1)
     */
    public void push(T element) {
        ensureCapacity(top + 1);
        data[top++] = element;
    }

    /* pop
     * Purpose: Remove and return top element.
     * Time: O(1)
     * Space: O(1)
     * Throws EmptyStackException if stack is empty.
     */
    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        T element = (T) data[--top];
        data[top] = null; // help GC
        return element;
    }

    /* peek
     * Purpose: Return top element without removing.
     * Time: O(1)
     * Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (T) data[top - 1];
    }

    /* isEmpty
     * Purpose: Check if stack has no elements.
     * Time: O(1)
     * Space: O(1)
     */
    public boolean isEmpty() {
        return top == 0;
    }

    /* size
     * Purpose: Return number of elements in stack.
     * Time: O(1)
     * Space: O(1)
     */
    public int size() {
        return top;
    }

    /* search
     * Purpose: Return 1-based position from top of stack of element, or -1 if not found.
     * Time: O(n)
     * Space: O(1)
     */
    public int search(T element) {
        for (int i = top - 1, pos = 1; i >= 0; i--, pos++) {
            if ((element == null && data[i] == null) || (element != null && element.equals(data[i]))) {
                return pos;
            }
        }
        return -1;
    }

    /* clear
     * Purpose: Remove all elements.
     * Time: O(n)
     * Space: O(1)
     */
    public void clear() {
        Arrays.fill(data, 0, top, null);
        top = 0;
    }

    /* ensureCapacity
     * Purpose: Grow backing array if needed.
     * Time: O(n) when resize, amortized O(1)
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = Math.max(data.length * 2, minCapacity);
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /* toString
     * Purpose: String representation (top shown last).
     */
    @Override
    public String toString() {
        Object[] arr = Arrays.copyOfRange(data, 0, top);
        return Arrays.toString(arr);
    }

    // Demo
    public static void main(String[] args) {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        System.out.println("Stack: " + stack); // [10, 20, 30]

        System.out.println("Peek: " + stack.peek()); // 30
        System.out.println("Pop: " + stack.pop());   // 30
        System.out.println("After pop: " + stack);   // [10, 20]

        System.out.println("Search 10: " + stack.search(10)); // position from top
        System.out.println("Size: " + stack.size());
        System.out.println("IsEmpty? " + stack.isEmpty());

        stack.clear();
        System.out.println("After clear: " + stack + ", size=" + stack.size());
    }
}

/*
Core Methods:
push(E e): Add element to top.
pop(): Remove and return top element.
peek(): Return top element without removing.
isEmpty(): Check if stack is empty.
size(): Return number of elements.
search(E e): Return position from top (1-based).
clear(): Remove all elements.
*/