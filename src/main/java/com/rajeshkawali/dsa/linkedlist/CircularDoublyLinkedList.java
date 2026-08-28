package com.rajeshkawali.dsa.linkedlist;
/**
 * CircularDoublyLinkedList
 *
 * - Generic circular doubly linked list: each node has prev and next.
 * - In a non-empty list: head.prev == tail and tail.next == head.
 * - All methods include short purpose, time complexity, space complexity, and explanation.
 *
 * Supported operations:
 *  - isEmpty, size
 *  - addFirst, addLast, addAt
 *  - removeFirst, removeLast, removeAt
 *  - get, indexOf, contains
 *  - traverseForward, traverseBackward, toArray, clear
 *  - rotate (advance head), reverse (in-place)
 *  - detectCycle (Floyd) and fixCircularity safeguard
 *
 * Note: For sorting or ordered inserts, T should implement Comparable<T>.
 */
public class CircularDoublyLinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head; // reference to head (null when empty)
    private int size;

    public CircularDoublyLinkedList() {
        head = null;
        size = 0;
    }

    /* isEmpty
     * Purpose: Check if list is empty.
     * Time: O(1)  Space: O(1)
     */
    public boolean isEmpty() { return size == 0; }

    /* size
     * Purpose: Return number of elements.
     * Time: O(1)  Space: O(1)
     */
    public int size() { return size; }

    /* addFirst
     * Purpose: Insert value at the beginning (head).
     * Time: O(1)  Space: O(1)
     * Explanation: Create node, link into circular prev/next; update head.
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
     * Purpose: Insert value at the end (tail).
     * Time: O(1)  Space: O(1)
     * Explanation: Insert at head then advance head to keep O(1).
     */
    public void addLast(T value) {
        addFirst(value);
        head = head.next; // new tail is previous head; advance head to preserve tail position
    }

    /* addAt
     * Purpose: Insert value at index (0-based).
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     * Explanation: Traverse from nearer end and splice node.
     */
    public void addAt(int index, T value) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) { addFirst(value); return; }
        if (index == size) { addLast(value); return; }

        Node<T> cur = head;
        if (index <= size / 2) {
            for (int i = 0; i < index - 1; i++) cur = cur.next;
        } else {
            cur = head.prev; // tail
            for (int i = size; i > index; i--) cur = cur.prev;
        }
        Node<T> node = new Node<>(value);
        Node<T> next = cur.next;
        cur.next = node; node.prev = cur;
        node.next = next; next.prev = node;
        size++;
    }

    /* removeFirst
     * Purpose: Remove and return head value.
     * Time: O(1)  Space: O(1)
     * Explanation: If single node, clear head; otherwise relink tail and new head.
     */
    public T removeFirst() {
        if (head == null) return null;
        T val = head.data;
        if (head.next == head) { // single node
            head = null;
        } else {
            Node<T> tail = head.prev;
            head = head.next;
            tail.next = head;
            head.prev = tail;
        }
        size--;
        return val;
    }

    /* removeLast
     * Purpose: Remove and return tail value.
     * Time: O(1)  Space: O(1)
     * Explanation: Use head.prev (tail) and unlink it.
     */
    public T removeLast() {
        if (head == null) return null;
        Node<T> tail = head.prev;
        T val = tail.data;
        if (tail == head) { head = null; }
        else {
            Node<T> newTail = tail.prev;
            newTail.next = head;
            head.prev = newTail;
        }
        size--;
        return val;
    }

    /* removeAt
     * Purpose: Remove and return element at index.
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     */
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<T> cur;
        if (index <= size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = head.prev;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        T val = cur.data;
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        return val;
    }

    /* get
     * Purpose: Return value at index without removing.
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur;
        if (index <= size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = head.prev;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur.data;
    }

    /* indexOf
     * Purpose: Return first index of value or -1 if not found.
     * Time: O(n)  Space: O(1)
     */
    public int indexOf(T value) {
        if (head == null) return -1;
        Node<T> cur = head;
        for (int i = 0; i < size; i++) {
            if ((value == null && cur.data == null) || (value != null && value.equals(cur.data))) return i;
            cur = cur.next;
        }
        return -1;
    }

    /* contains
     * Purpose: True if value exists.
     * Time: O(n)  Space: O(1)
     */
    public boolean contains(T value) { return indexOf(value) != -1; }

    /* traverseForward
     * Purpose: Print elements from head to tail.
     * Time: O(n)  Space: O(1)
     */
    public void traverseForward() {
        if (head == null) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder("[");
        Node<T> cur = head;
        for (int i = 0; i < size; i++) {
            sb.append(cur.data);
            if (i < size - 1) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /* traverseBackward
     * Purpose: Print elements from tail to head.
     * Time: O(n)  Space: O(1)
     */
    public void traverseBackward() {
        if (head == null) { System.out.println("[]"); return; }
        StringBuilder sb = new StringBuilder("[");
        Node<T> cur = head.prev; // tail
        for (int i = 0; i < size; i++) {
            sb.append(cur.data);
            if (i < size - 1) sb.append(", ");
            cur = cur.prev;
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    /* toArray
     * Purpose: Convert list to Object[].
     * Time: O(n)  Space: O(n)
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        if (head == null) return arr;
        Node<T> cur = head;
        for (int i = 0; i < size; i++) {
            arr[i] = cur.data;
            cur = cur.next;
        }
        return arr;
    }

    /* clear
     * Purpose: Remove all elements (drop references).
     * Time: O(1)  Space: O(1)
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /* rotate
     * Purpose: Advance head by k positions (k may be > n).
     * Time: O(k mod n) worst O(n)  Space: O(1)
     * Explanation: Move head forward; tail is head.prev automatically.
     */
    public void rotate(int k) {
        if (head == null || k % size == 0) return;
        int steps = ((k % size) + size) % size;
        for (int i = 0; i < steps; i++) head = head.next;
    }

    /* reverse
     * Purpose: Reverse list in-place while preserving circularity.
     * Time: O(n)  Space: O(1)
     * Explanation: Swap next and prev for each node, then move head to previous tail.
     */
    public void reverse() {
        if (head == null || head.next == head) return;
        Node<T> cur = head;
        for (int i = 0; i < size; i++) {
            Node<T> tmp = cur.next;
            cur.next = cur.prev;
            cur.prev = tmp;
            cur = tmp;
        }
        head = head.next; // old tail becomes new head after swapping
    }

    /* detectCycle
     * Purpose: Detect cycle using Floyd's algorithm.
     * Time: O(n)  Space: O(1)
     * Explanation: For a well-formed circular list this returns true; used as a safeguard.
     */
    public boolean detectCycle() {
        if (head == null) return false;
        Node<T> slow = head, fast = head;
        for (int i = 0; i < size * 2; i++) { // bounded to avoid infinite loops on malformed lists
            slow = slow.next;
            fast = fast.next != null ? fast.next.next : null;
            if (fast == null) return false;
            if (slow == fast) return true;
        }
        return false;
    }

    /* fixCircularityIfMalformed
     * Purpose: Ensure head.prev and tail.next are consistent for safety.
     * Time: O(n)  Space: O(1)
     * Explanation: Walk size nodes from head and re-link tail.next=head and head.prev=tail.
     */
    public void fixCircularityIfMalformed() {
        if (head == null) return;
        Node<T> cur = head;
        int count = 1;
        while (cur.next != null && cur.next != head && count < size) {
            cur = cur.next;
            count++;
        }
        // cur is last reachable node within size steps
        cur.next = head;
        head.prev = cur;
        // if count != size, adjust size to reachable count
        size = count;
    }

    /* toString
     * Purpose: Quick representation.
     * Time: O(n)  Space: O(1)
     */
    @Override
    public String toString() {
        if (head == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<T> cur = head;
        for (int i = 0; i < size; i++) {
            sb.append(cur.data);
            if (i < size - 1) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /* Example usage */
    public static void main(String[] args) {
        CircularDoublyLinkedList<Integer> cdll = new CircularDoublyLinkedList<>();
        cdll.addLast(2);
        cdll.addFirst(1);
        cdll.addLast(3);
        cdll.addAt(1, 99); // [1,99,2,3]
        System.out.println("Forward: " + cdll);
        System.out.print("Backward: "); cdll.traverseBackward();

        System.out.println("removeAt(1): " + cdll.removeAt(1)); // removes 99
        System.out.println("After remove: " + cdll);

        cdll.rotate(1);
        System.out.println("After rotate(1): " + cdll);

        cdll.reverse();
        System.out.println("After reverse: " + cdll);

        System.out.println("Contains 2? " + cdll.contains(2));
        System.out.println("Index of 3: " + cdll.indexOf(3));
        System.out.println("Size: " + cdll.size());

        cdll.clear();
        System.out.println("Cleared: " + cdll);
    }
}
