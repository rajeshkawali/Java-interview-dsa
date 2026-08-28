package com.rajeshkawali.dsa.linkedlist;
/**
 * CircularLinkedListVariants.java
 *
 * Single-file collection of circular linked list variants and core operations.
 * Variants included:
 *  1) Circular Singly Linked List (CSLL)        - nodes point next; tail.next -> head
 *  2) Circular Doubly Linked List (CDLL)        - nodes have prev/next; tail.next -> head, head.prev -> tail
 *  3) Ring Buffer (Array-based Circular Buffer) - fixed-size circular queue (not pointer-based)
 *
 * Each variant implements core operations with short comments, time & space complexity,
 * and a brief explanation of behavior. Use these classes as reference or drop-in utilities.
 *
 * Notes:
 * - "n" denotes current number of elements.
 * - Methods return null or false for invalid operations where appropriate.
 * - These implementations favor clarity and correctness for interview / learning use.
 */

public class CircularLinkedListVariants {

    // -----------------------------
    // 1) Circular Singly Linked List
    // -----------------------------
    public static class CircularSinglyLinkedList<T> {
        private static class Node<T> {
            T val;
            Node<T> next;
            Node(T v) { val = v; }
        }

        private Node<T> tail; // tail.next is head
        private int size;

        public CircularSinglyLinkedList() { tail = null; size = 0; }

        /* isEmpty
         * Time: O(1)  Space: O(1)
         * Returns true if list has no elements.
         */
        public boolean isEmpty() { return size == 0; }

        /* size
         * Time: O(1)  Space: O(1)
         */
        public int size() { return size; }

        /* addFirst
         * Time: O(1)  Space: O(1)
         * Insert at head. If empty, tail points to new node and new.next -> itself.
         */
        public void addFirst(T value) {
            Node<T> node = new Node<>(value);
            if (tail == null) {
                tail = node;
                tail.next = tail;
            } else {
                node.next = tail.next; // old head
                tail.next = node;      // new head
            }
            size++;
        }

        /* addLast
         * Time: O(1)  Space: O(1)
         * Insert at tail. Equivalent to addFirst then move tail to new node.
         */
        public void addLast(T value) {
            addFirst(value);
            tail = tail.next; // advance tail to the newly inserted node
        }

        /* addAt
         * Time: O(min(index, n)) ~ O(n) worst  Space: O(1)
         * Insert at index (0-based). index==0 -> addFirst; index==size -> addLast.
         */
        public void addAt(int index, T value) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            if (index == 0) { addFirst(value); return; }
            if (index == size) { addLast(value); return; }
            Node<T> cur = tail.next; // head
            for (int i = 1; i < index; i++) cur = cur.next;
            Node<T> node = new Node<>(value);
            node.next = cur.next;
            cur.next = node;
            size++;
        }

        /* removeFirst
         * Time: O(1)  Space: O(1)
         * Remove and return head value. If single node, tail becomes null.
         */
        public T removeFirst() {
            if (tail == null) return null;
            Node<T> head = tail.next;
            T val = head.val;
            if (tail == head) { // single node
                tail = null;
            } else {
                tail.next = head.next;
            }
            size--;
            return val;
        }

        /* removeLast
         * Time: O(n)  Space: O(1)
         * Remove and return tail value. Must find node before tail.
         */
        public T removeLast() {
            if (tail == null) return null;
            Node<T> head = tail.next;
            if (tail == head) { // single node
                T val = tail.val;
                tail = null;
                size = 0;
                return val;
            }
            Node<T> cur = head;
            while (cur.next != tail) cur = cur.next; // O(n)
            T val = tail.val;
            cur.next = head;
            tail = cur;
            size--;
            return val;
        }

        /* removeAt
         * Time: O(n)  Space: O(1)
         * Remove element at index and return value.
         */
        public T removeAt(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            if (index == 0) return removeFirst();
            Node<T> cur = tail.next; // head
            for (int i = 1; i < index; i++) cur = cur.next;
            Node<T> target = cur.next;
            cur.next = target.next;
            if (target == tail) tail = cur;
            size--;
            return target.val;
        }

        /* get
         * Time: O(n)  Space: O(1)
         * Return value at index without removing.
         */
        public T get(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            Node<T> cur = tail.next; // head
            for (int i = 0; i < index; i++) cur = cur.next;
            return cur.val;
        }

        /* indexOf / contains
         * Time: O(n)  Space: O(1)
         */
        public int indexOf(T value) {
            if (tail == null) return -1;
            Node<T> cur = tail.next; // head
            for (int i = 0; i < size; i++) {
                if ((value == null && cur.val == null) || (value != null && value.equals(cur.val))) return i;
                cur = cur.next;
            }
            return -1;
        }
        public boolean contains(T value) { return indexOf(value) != -1; }

        /* traverse
         * Time: O(n)  Space: O(1)
         * Apply action for each element (simple print here).
         */
        public void traverse() {
            if (tail == null) { System.out.println("[]"); return; }
            Node<T> cur = tail.next; // head
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            for (int i = 0; i < size; i++) {
                sb.append(cur.val);
                if (i < size - 1) sb.append(", ");
                cur = cur.next;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }

        /* rotate
         * Time: O(1)  Space: O(1)
         * Move head forward by k positions (k mod n). Implemented by advancing tail.
         */
        public void rotate(int k) {
            if (tail == null || k % size == 0) return;
            int steps = k % size;
            for (int i = 0; i < steps; i++) tail = tail.next;
        }

        /* clear
         * Time: O(1)  Space: O(1)
         * Drop references; GC reclaims nodes.
         */
        public void clear() { tail = null; size = 0; }
    }

    // --------------------------------
    // 2) Circular Doubly Linked List
    // --------------------------------
    public static class CircularDoublyLinkedList<T> {
        private static class Node<T> {
            T val;
            Node<T> prev, next;
            Node(T v) { val = v; }
        }

        private Node<T> head; // head.prev -> tail, tail.next -> head
        private int size;

        public CircularDoublyLinkedList() { head = null; size = 0; }

        /* isEmpty / size
         * Time: O(1)
         */
        public boolean isEmpty() { return size == 0; }
        public int size() { return size; }

        /* addFirst
         * Time: O(1)  Space: O(1)
         * Insert at head; maintain circular prev/next links.
         */
        public void addFirst(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                node.next = node.prev = node;
                head = node;
            } else {
                Node<T> tail = head.prev;
                node.next = head;
                node.prev = tail;
                tail.next = head.prev = node;
                head = node;
            }
            size++;
        }

        /* addLast
         * Time: O(1)  Space: O(1)
         * Insert at tail by adding at head and moving head reference.
         */
        public void addLast(T value) {
            addFirst(value);
            head = head.next; // new head was inserted; advance to keep head at original
        }

        /* addAt
         * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
         */
        public void addAt(int index, T value) {
            if (index < 0 || index > size) throw new IndexOutOfBoundsException();
            if (index == 0) { addFirst(value); return; }
            if (index == size) { addLast(value); return; }
            Node<T> cur = head;
            if (index <= size/2) {
                for (int i = 0; i < index; i++) cur = cur.next;
            } else {
                cur = head.prev;
                for (int i = size; i > index; i--) cur = cur.prev;
            }
            Node<T> node = new Node<>(value);
            Node<T> prev = cur.prev;
            prev.next = node; node.prev = prev;
            node.next = cur; cur.prev = node;
            if (index == 0) head = node;
            size++;
        }

        /* removeFirst
         * Time: O(1)  Space: O(1)
         */
        public T removeFirst() {
            if (head == null) return null;
            T val = head.val;
            if (head.next == head) { head = null; size = 0; return val; }
            Node<T> tail = head.prev;
            head = head.next;
            head.prev = tail;
            tail.next = head;
            size--;
            return val;
        }

        /* removeLast
         * Time: O(1)  Space: O(1)
         */
        public T removeLast() {
            if (head == null) return null;
            Node<T> tail = head.prev;
            T val = tail.val;
            if (tail == head) { head = null; size = 0; return val; }
            Node<T> newTail = tail.prev;
            newTail.next = head;
            head.prev = newTail;
            size--;
            return val;
        }

        /* removeAt
         * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
         */
        public T removeAt(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            if (index == 0) return removeFirst();
            Node<T> cur = head;
            if (index <= size/2) {
                for (int i = 0; i < index; i++) cur = cur.next;
            } else {
                cur = head.prev;
                for (int i = size-1; i > index; i--) cur = cur.prev;
            }
            T val = cur.val;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            size--;
            return val;
        }

        /* get / indexOf / contains
         * Time: O(n)
         */
        public T get(int index) {
            if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
            Node<T> cur = head;
            if (index <= size/2) {
                for (int i = 0; i < index; i++) cur = cur.next;
            } else {
                cur = head.prev;
                for (int i = size-1; i > index; i--) cur = cur.prev;
            }
            return cur.val;
        }
        public int indexOf(T value) {
            if (head == null) return -1;
            Node<T> cur = head;
            for (int i = 0; i < size; i++) {
                if ((value == null && cur.val == null) || (value != null && value.equals(cur.val))) return i;
                cur = cur.next;
            }
            return -1;
        }
        public boolean contains(T value) { return indexOf(value) != -1; }

        /* traverseForward / traverseBackward
         * Time: O(n)
         */
        public void traverseForward() {
            if (head == null) { System.out.println("[]"); return; }
            Node<T> cur = head;
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(cur.val);
                if (i < size-1) sb.append(", ");
                cur = cur.next;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
        public void traverseBackward() {
            if (head == null) { System.out.println("[]"); return; }
            Node<T> cur = head.prev; // tail
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                sb.append(cur.val);
                if (i < size-1) sb.append(", ");
                cur = cur.prev;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }

        /* rotate
         * Time: O(1)
         * Move head forward by k positions (k mod n).
         */
        public void rotate(int k) {
            if (head == null || k % size == 0) return;
            int steps = k % size;
            for (int i = 0; i < steps; i++) head = head.next;
        }

        /* clear
         * Time: O(1)
         */
        public void clear() { head = null; size = 0; }
    }

    // -----------------------------
    // 3) Ring Buffer (Array-based)
    // -----------------------------
    public static class RingBuffer<T> {
        private Object[] buf;
        private int head;   // index of next element to read
        private int tail;   // index of next element to write
        private int count;  // number of elements stored

        public RingBuffer(int capacity) {
            if (capacity <= 0) throw new IllegalArgumentException("capacity > 0");
            buf = new Object[capacity];
            head = 0; tail = 0; count = 0;
        }

        /* capacity / size / isEmpty / isFull
         * Time: O(1)
         */
        public int capacity() { return buf.length; }
        public int size() { return count; }
        public boolean isEmpty() { return count == 0; }
        public boolean isFull() { return count == buf.length; }

        /* offer (enqueue)
         * Time: O(1)  Space: O(1)
         * Adds element if space available; returns true on success.
         */
        public boolean offer(T value) {
            if (isFull()) return false;
            buf[tail] = value;
            tail = (tail + 1) % buf.length;
            count++;
            return true;
        }

        /* poll (dequeue)
         * Time: O(1)  Space: O(1)
         * Removes and returns head element, or null if empty.
         */
        @SuppressWarnings("unchecked")
        public T poll() {
            if (isEmpty()) return null;
            T val = (T) buf[head];
            buf[head] = null; // help GC
            head = (head + 1) % buf.length;
            count--;
            return val;
        }

        /* peek
         * Time: O(1)
         */
        @SuppressWarnings("unchecked")
        public T peek() { return isEmpty() ? null : (T) buf[head]; }

        /* clear
         * Time: O(n) to null out references, O(1) to reset pointers (choose O(1) here)
         */
        public void clear() {
            // optional: null out for GC
            for (int i = 0; i < buf.length; i++) buf[i] = null;
            head = tail = count = 0;
        }
    }

    // -----------------------------
    // Example usage (main)
    // -----------------------------
    public static void main(String[] args) {
        System.out.println("=== Circular Singly Linked List ===");
        CircularSinglyLinkedList<Integer> csll = new CircularSinglyLinkedList<>();
        csll.addLast(1); csll.addLast(2); csll.addLast(3);
        csll.traverse();            // [1, 2, 3]
        csll.rotate(1); csll.traverse(); // [2, 3, 1]
        System.out.println("removeFirst: " + csll.removeFirst()); // 2
        csll.traverse();

        System.out.println("\n=== Circular Doubly Linked List ===");
        CircularDoublyLinkedList<String> cdll = new CircularDoublyLinkedList<>();
        cdll.addLast("a"); cdll.addLast("b"); cdll.addLast("c");
        cdll.traverseForward();     // [a, b, c]
        cdll.traverseBackward();    // [c, b, a]
        cdll.rotate(2); cdll.traverseForward(); // rotated

        System.out.println("\n=== Ring Buffer ===");
        RingBuffer<Integer> rb = new RingBuffer<>(3);
        rb.offer(10); rb.offer(20); rb.offer(30);
        System.out.println("isFull: " + rb.isFull()); // true
        System.out.println("poll: " + rb.poll());     // 10
        rb.offer(40);
        while (!rb.isEmpty()) System.out.println("poll: " + rb.poll());
    }
}
