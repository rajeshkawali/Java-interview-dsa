package com.rajeshkawali.dsa.implementation;

import java.util.NoSuchElementException;
import java.util.Arrays;

public class MyQueue<T> {

    private Object[] data;   // backing array
    private int front;       // index of front element
    private int rear;        // index of next insertion
    private int size;        // number of elements
    private static final int DEFAULT_CAPACITY = 10;

    // Constructor
    public MyQueue() {
        data = new Object[DEFAULT_CAPACITY];
        front = 0;
        rear = 0;
        size = 0;
    }

    /* offer / add
     * Purpose: Insert element at rear of queue.
     * Time: O(1) amortized (O(n) if resize)
     * Space: O(1)
     */
    public void offer(T element) {
        ensureCapacity(size + 1);
        data[rear] = element;
        rear = (rear + 1) % data.length;
        size++;
    }

    /* poll
     * Purpose: Remove and return front element, or null if empty.
     * Time: O(1)
     * Space: O(1)
     */
    @SuppressWarnings("unchecked")
    public T poll() {
        if (isEmpty()) return null;
        T element = (T) data[front];
        data[front] = null; // help GC
        front = (front + 1) % data.length;
        size--;
        return element;
    }

    /* remove
     * Purpose: Remove and return front element, throws exception if empty.
     * Time: O(1)
     */
    public T remove() {
        if (isEmpty()) throw new NoSuchElementException();
        return poll();
    }

    /* peek
     * Purpose: Return front element without removing, or null if empty.
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) return null;
        return (T) data[front];
    }

    /* element
     * Purpose: Return front element without removing, throws exception if empty.
     * Time: O(1)
     */
    @SuppressWarnings("unchecked")
    public T element() {
        if (isEmpty()) throw new NoSuchElementException();
        return (T) data[front];
    }

    /* isEmpty
     * Purpose: Check if queue has no elements.
     * Time: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /* size
     * Purpose: Return number of elements in queue.
     * Time: O(1)
     */
    public int size() {
        return size;
    }

    /* clear
     * Purpose: Remove all elements.
     * Time: O(n)
     */
    public void clear() {
        Arrays.fill(data, null);
        front = rear = size = 0;
    }

    /* ensureCapacity
     * Purpose: Grow backing array if needed.
     * Time: O(n) when resize, amortized O(1)
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            int newCapacity = data.length * 2;
            Object[] newData = new Object[newCapacity];
            for (int i = 0; i < size; i++) {
                newData[i] = data[(front + i) % data.length];
            }
            data = newData;
            front = 0;
            rear = size;
        }
    }

    /* toString
     * Purpose: String representation (front shown first).
     */
    @Override
    public String toString() {
        Object[] arr = new Object[size];
        for (int i = 0; i < size; i++) {
            arr[i] = data[(front + i) % data.length];
        }
        return Arrays.toString(arr);
    }

    // Demo
    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();
        queue.offer(10);
        queue.offer(20);
        queue.offer(30);
        System.out.println("Queue: " + queue); // [10, 20, 30]

        System.out.println("Peek: " + queue.peek()); // 10
        System.out.println("Poll: " + queue.poll()); // 10
        System.out.println("After poll: " + queue);  // [20, 30]

        System.out.println("Remove: " + queue.remove()); // 20
        System.out.println("After remove: " + queue);    // [30]

        System.out.println("Size: " + queue.size());
        System.out.println("IsEmpty? " + queue.isEmpty());

        queue.clear();
        System.out.println("After clear: " + queue + ", size=" + queue.size());
    }
}

/*
Core Methods:
offer(E e): Insert element at rear (returns true).
poll(): Remove and return front element, or null if empty.
remove(): Remove and return front element, throws exception if empty.
peek(): Return front element without removing, or null if empty.
element(): Return front element without removing, throws exception if empty.
isEmpty(): Check if queue is empty.
size(): Return number of elements.
clear(): Remove all elements. 
*/
