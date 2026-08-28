package com.rajeshkawali.dsa.linkedlist;
/**
 * CircularSinglyLinkedList
 *
 * Single-file implementation of a Circular Singly Linked List (CSLL) with core operations.
 * - Each node points to the next node; tail.next -> head.
 * - All methods include short purpose, time complexity, space complexity, and brief explanation.
 *
 * Supported operations:
 *  - isEmpty, size
 *  - addFirst, addLast, addAt
 *  - removeFirst, removeLast, removeAt
 *  - get, indexOf, contains
 *  - traverse (print), toArray, clear
 *  - rotate (advance head), reverse (in-place), findMiddle, kthFromEnd
 *  - detectCycle (Floyd) and breakCycle (safeguard)
 *  - sort (merge sort adapted for circular list)
 *
 * Notes:
 *  - For sorting and comparisons, T should implement Comparable<T> or a fallback toString() comparison is used.
 *  - The list is maintained circularly: when non-empty, tail.next always points to head.
 */
public class CircularSinglyLinkedList<T> {

    // Node class (inner static)
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    private Node<T> tail; // tail.next is head; tail == null means empty
    private int size;

    // Constructor
    public CircularSinglyLinkedList() {
        tail = null;
        size = 0;
    }

    /* isEmpty
     * Purpose: Check whether list has no elements.
     * Time: O(1)  Space: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /* size
     * Purpose: Return number of elements.
     * Time: O(1)  Space: O(1)
     */
    public int size() {
        return size;
    }

    /* head
     * Purpose: Return head node value (null if empty).
     * Time: O(1)  Space: O(1)
     */
    public T head() {
        return (tail == null) ? null : tail.next.value;
    }

    /* addFirst
     * Purpose: Insert value at the beginning (head).
     * Time: O(1)  Space: O(1)
     * Explanation: Create new node; if empty, node.next -> node and tail = node.
     *              Otherwise node.next -> oldHead and tail.next -> node.
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
     * Purpose: Insert value at the end (tail).
     * Time: O(1)  Space: O(1)
     * Explanation: Equivalent to addFirst then advance tail to new node.
     */
    public void addLast(T value) {
        addFirst(value);
        tail = tail.next;
    }

    /* addAt
     * Purpose: Insert value at index (0-based).
     * Time: O(min(index, n)) ~ O(n) worst  Space: O(1)
     * Explanation: index==0 -> addFirst; index==size -> addLast; otherwise traverse to (index-1) and splice.
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
     * Purpose: Remove and return head value.
     * Time: O(1)  Space: O(1)
     * Explanation: If single node, tail becomes null. Otherwise tail.next = head.next.
     */
    public T removeFirst() {
        if (tail == null) return null;
        Node<T> head = tail.next;
        T val = head.value;
        if (tail == head) { // single node
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return val;
    }

    /* removeLast
     * Purpose: Remove and return tail value.
     * Time: O(n)  Space: O(1)
     * Explanation: Must find node before tail (O(n)). If single node, clear tail.
     */
    public T removeLast() {
        if (tail == null) return null;
        Node<T> head = tail.next;
        if (tail == head) { // single node
            T val = tail.value;
            tail = null;
            size = 0;
            return val;
        }
        Node<T> cur = head;
        while (cur.next != tail) cur = cur.next; // find previous
        T val = tail.value;
        cur.next = head;
        tail = cur;
        size--;
        return val;
    }

    /* removeAt
     * Purpose: Remove and return value at index.
     * Time: O(n)  Space: O(1)
     * Explanation: index==0 -> removeFirst; otherwise traverse to (index-1) and unlink.
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
        return target.value;
    }

    /* get
     * Purpose: Return value at index without removing.
     * Time: O(n)  Space: O(1)
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur = tail.next; // head
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.value;
    }

    /* indexOf
     * Purpose: Return first index of value or -1 if not found.
     * Time: O(n)  Space: O(1)
     */
    public int indexOf(T value) {
        if (tail == null) return -1;
        Node<T> cur = tail.next; // head
        for (int i = 0; i < size; i++) {
            if ((value == null && cur.value == null) || (value != null && value.equals(cur.value))) return i;
            cur = cur.next;
        }
        return -1;
    }

    /* contains
     * Purpose: True if value exists.
     * Time: O(n)  Space: O(1)
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    /* traverse
     * Purpose: Print elements from head to tail in order.
     * Time: O(n)  Space: O(1)
     */
    public void traverse() {
        if (tail == null) {
            System.out.println("[]");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> cur = tail.next; // head
        for (int i = 0; i < size; i++) {
            sb.append(cur.value);
            if (i < size - 1) sb.append(", ");
            cur = cur.next;
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
        if (tail == null) return arr;
        Node<T> cur = tail.next; // head
        for (int i = 0; i < size; i++) {
            arr[i] = cur.value;
            cur = cur.next;
        }
        return arr;
    }

    /* clear
     * Purpose: Remove all elements (drop references).
     * Time: O(1)  Space: O(1)
     */
    public void clear() {
        tail = null;
        size = 0;
    }

    /* rotate
     * Purpose: Advance head by k positions (k may be > n).
     * Time: O(k mod n) worst O(n)  Space: O(1)
     * Explanation: Achieved by advancing tail by k steps.
     */
    public void rotate(int k) {
        if (tail == null || k % size == 0) return;
        int steps = k % size;
        for (int i = 0; i < steps; i++) tail = tail.next;
    }

    /* reverse
     * Purpose: Reverse list in-place while preserving circular property.
     * Time: O(n)  Space: O(1)
     * Explanation: Standard iterative reversal adapted for circular list:
     *  - Break circularity temporarily, reverse linear list, then re-circularize.
     */
    public void reverse() {
        if (tail == null || tail.next == tail) return; // empty or single
        // break circularity
        Node<T> head = tail.next;
        tail.next = null;

        // reverse linear list
        Node<T> prev = null, curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        // prev is new head; find new tail (old head)
        Node<T> newHead = prev;
        Node<T> newTail = newHead;
        while (newTail.next != null) newTail = newTail.next;

        // re-circularize
        tail = newTail;
        tail.next = newHead;
    }

    /* findMiddle
     * Purpose: Return middle element value (first middle for even length).
     * Time: O(n)  Space: O(1)
     * Explanation: Use slow-fast pointers on linearized traversal (stop after n steps).
     */
    public T findMiddle() {
        if (tail == null) return null;
        Node<T> slow = tail.next; // head
        Node<T> fast = tail.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // stop condition: we've advanced more than size steps; to be safe, break when fast loops
            if (fast == tail.next || fast == null) break;
        }
        return slow.value;
    }

    /* kthFromEnd
     * Purpose: Return k-th element from end (k=1 -> last).
     * Time: O(n)  Space: O(1)
     * Explanation: Two-pointer technique: advance fast by k, then move both until fast reaches head again.
     */
    public T kthFromEnd(int k) {
        if (k <= 0 || k > size) throw new IllegalArgumentException("k out of range");
        Node<T> head = tail.next;
        Node<T> fast = head, slow = head;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast != head) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.value;
    }

    /* detectCycle
     * Purpose: Detect whether a cycle exists (should be true for a well-formed circular list).
     * Time: O(n)  Space: O(1)
     * Explanation: Floyd's cycle detection; returns true if slow==fast at some point.
     */
    public boolean detectCycle() {
        if (tail == null) return false;
        Node<T> slow = tail.next, fast = tail.next;
        for (int i = 0; i < size * 2; i++) { // bounded loop to avoid infinite loops on malformed lists
            if (fast == null || fast.next == null) return false;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /* breakCycleIfMalformed
     * Purpose: If list has a malformed cycle (not exactly one circular loop), attempt to linearize safely.
     * Time: O(n)  Space: O(1)
     * Explanation: This is a safeguard; for well-formed CSLL this is a no-op.
     */
    public void breakCycleIfMalformed() {
        if (tail == null) return;
        // Ensure exactly size nodes are reachable from head; if more, truncate.
        Node<T> head = tail.next;
        Node<T> cur = head;
        int count = 1;
        while (cur.next != null && cur.next != head && count <= size) {
            cur = cur.next;
            count++;
        }
        // If cur.next != head, we have a malformed structure; force circularity at count nodes
        if (cur.next != head) {
            cur.next = head;
            tail = cur;
            size = count;
        }
    }

    /* sort
     * Purpose: Sort list using merge sort adapted for circular list.
     * Time: O(n log n)  Space: O(log n) recursion stack + O(1) extra
     * Explanation:
     *  - Convert circular list to linear by breaking tail.next.
     *  - Apply standard merge sort on linked list.
     *  - Re-circularize result and update tail.
     * Note: T should be Comparable<T> or fallback toString() comparison is used.
     */
    public void sort() {
        if (tail == null || tail.next == tail) return;
        // break circularity
        Node<T> head = tail.next;
        tail.next = null;

        // merge sort on linear list
        Node<T> sortedHead = mergeSort(head);

        // find new tail and re-circularize
        Node<T> newTail = sortedHead;
        int newSize = 1;
        while (newTail.next != null) {
            newTail = newTail.next;
            newSize++;
        }
        newTail.next = sortedHead;
        tail = newTail;
        size = newSize;
    }

    // merge sort helpers for linear linked list
    private Node<T> mergeSort(Node<T> node) {
        if (node == null || node.next == null) return node;
        Node<T> mid = getMiddle(node);
        Node<T> right = mid.next;
        mid.next = null;
        Node<T> leftSorted = mergeSort(node);
        Node<T> rightSorted = mergeSort(right);
        return mergeTwo(leftSorted, rightSorted);
    }

    private Node<T> getMiddle(Node<T> start) {
        Node<T> slow = start, fast = start.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node<T> mergeTwo(Node<T> a, Node<T> b) {
        Node<T> dummy = new Node<>(null);
        Node<T> tailLocal = dummy;
        while (a != null && b != null) {
            if (compare(a.value, b.value) <= 0) {
                tailLocal.next = a;
                a = a.next;
            } else {
                tailLocal.next = b;
                b = b.next;
            }
            tailLocal = tailLocal.next;
        }
        tailLocal.next = (a != null) ? a : b;
        return dummy.next;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private int compare(T x, T y) {
        if (x == null && y == null) return 0;
        if (x == null) return -1;
        if (y == null) return 1;
        if (x instanceof Comparable && y instanceof Comparable) {
            return ((Comparable) x).compareTo(y);
        }
        return x.toString().compareTo(y.toString());
    }

    /* toString override for quick representation */
    @Override
    public String toString() {
        if (tail == null) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node<T> cur = tail.next;
        for (int i = 0; i < size; i++) {
            sb.append(cur.value);
            if (i < size - 1) sb.append(", ");
            cur = cur.next;
        }
        sb.append("]");
        return sb.toString();
    }

    /* Example usage (main) */
    public static void main(String[] args) {
        CircularSinglyLinkedList<Integer> csll = new CircularSinglyLinkedList<>();
        csll.addLast(3);
        csll.addFirst(2);
        csll.addFirst(1);
        csll.addLast(4);
        csll.addAt(2, 99); // [1,2,99,3,4]
        System.out.println("Initial: " + csll);

        System.out.println("removeAt(2): " + csll.removeAt(2)); // removes 99
        System.out.println("After removeAt: " + csll);

        System.out.println("removeFirst: " + csll.removeFirst());
        System.out.println("removeLast: " + csll.removeLast());
        System.out.println("After removes: " + csll);

        csll.addLast(5); csll.addLast(0); csll.addLast(7);
        System.out.println("Before rotate: " + csll);
        csll.rotate(2);
        System.out.println("After rotate(2): " + csll);

        System.out.println("Contains 5? " + csll.contains(5));
        System.out.println("Index of 0: " + csll.indexOf(0));
        System.out.println("kthFromEnd(2): " + csll.kthFromEnd(2));
        System.out.println("Middle: " + csll.findMiddle());

        System.out.println("Reversing...");
        csll.reverse();
        System.out.println("After reverse: " + csll);

        System.out.println("Sorting...");
        csll.sort();
        System.out.println("After sort: " + csll);

        System.out.println("Traverse:");
        csll.traverse();

        System.out.println("Clearing...");
        csll.clear();
        System.out.println("Empty? " + csll.isEmpty());
    }
}
