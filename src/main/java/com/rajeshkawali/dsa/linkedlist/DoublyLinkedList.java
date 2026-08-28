package com.rajeshkawali.dsa.linkedlist;
/**
 * DoublyLinkedList
 *
 * Single-file implementation of a doubly linked list with common and advanced operations.
 * Each method contains:
 *  - short description
 *  - time complexity
 *  - space complexity
 *  - explanation of behavior
 *
 * Features included:
 *  - addFirst, addLast, addAt
 *  - removeFirst, removeLast, removeAt
 *  - get, indexOf, contains
 *  - toArray, printList, clear
 *  - reverseIterative, reverseRecursive
 *  - findMiddle, kthFromEnd
 *  - detectCycle, removeCycle
 *  - insertSorted, removeDuplicatesSorted
 *  - mergeSort (for list), mergeTwoSorted
 *
 * Note: Generic type T is used. For sorting/merge operations, T should implement Comparable<T>
 *       or the compare method will fall back to toString comparison.
 */
public class DoublyLinkedList<T> {

    // Node class for doubly linked list
    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;
        Node(T data) { this.data = data; }
    }

    private Node<T> head; // first node
    private Node<T> tail; // last node
    private int size;     // number of elements

    // Constructor
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // -------------------------
    // Basic properties
    // -------------------------

    /**
     * isEmpty
     * Purpose: Check if list has no elements.
     * Time: O(1)  Space: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * size
     * Purpose: Return number of elements.
     * Time: O(1)  Space: O(1)
     */
    public int size() {
        return size;
    }

    // -------------------------
    // Insertion operations
    // -------------------------

    /**
     * addFirst
     * Purpose: Insert value at the beginning of the list.
     * Time: O(1)  Space: O(1)
     * Explanation: Create new node, set its next to current head, update head.prev,
     *              update tail if list was empty, increment size.
     */
    public void addFirst(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;
        if (head != null) head.prev = node;
        head = node;
        if (tail == null) tail = node; // first element
        size++;
    }

    /**
     * addLast
     * Purpose: Insert value at the end of the list.
     * Time: O(1)  Space: O(1)
     * Explanation: Use tail pointer to append in constant time. If empty, head and tail set.
     */
    public void addLast(T data) {
        Node<T> node = new Node<>(data);
        node.prev = tail;
        if (tail != null) tail.next = node;
        tail = node;
        if (head == null) head = node;
        size++;
    }

    /**
     * addAt
     * Purpose: Insert value at specified index (0-based).
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     * Explanation: If index==0 -> addFirst; if index==size -> addLast;
     *              otherwise traverse from nearer end (head or tail) to index and splice node.
     */
    public void addAt(int index, T data) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (index == 0) { addFirst(data); return; }
        if (index == size) { addLast(data); return; }

        Node<T> cur;
        if (index <= size / 2) {
            cur = head;
            for (int i = 0; i < index - 1; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i >= index; i--) cur = cur.prev;
        }
        Node<T> node = new Node<>(data);
        node.next = cur.next;
        node.prev = cur;
        cur.next.prev = node;
        cur.next = node;
        size++;
    }

    // -------------------------
    // Removal operations
    // -------------------------

    /**
     * removeFirst
     * Purpose: Remove and return the first element.
     * Time: O(1)  Space: O(1)
     * Explanation: Move head to head.next, update prev pointer, update tail if list becomes empty.
     */
    public T removeFirst() {
        if (isEmpty()) return null;
        T val = head.data;
        head = head.next;
        size--;
        if (head != null) head.prev = null;
        else tail = null; // list became empty
        return val;
    }

    /**
     * removeLast
     * Purpose: Remove and return the last element.
     * Time: O(1)  Space: O(1)
     * Explanation: Use tail pointer to remove in constant time, update head if list becomes empty.
     */
    public T removeLast() {
        if (isEmpty()) return null;
        T val = tail.data;
        tail = tail.prev;
        size--;
        if (tail != null) tail.next = null;
        else head = null;
        return val;
    }

    /**
     * removeAt
     * Purpose: Remove and return element at index.
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     * Explanation: Traverse from nearer end, unlink node by updating prev.next and next.prev.
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
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        T val = cur.data;
        cur.prev.next = cur.next;
        cur.next.prev = cur.prev;
        size--;
        return val;
    }

    /**
     * clear
     * Purpose: Remove all elements.
     * Time: O(1)  Space: O(1)
     * Explanation: Drop head/tail references and let GC reclaim nodes.
     */
    public void clear() {
        head = tail = null;
        size = 0;
    }

    // -------------------------
    // Access and search
    // -------------------------

    /**
     * get
     * Purpose: Return value at index without removing.
     * Time: O(min(index, n-index)) ~ O(n) worst  Space: O(1)
     * Explanation: Traverse from nearer end for efficiency.
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur;
        if (index <= size / 2) {
            cur = head;
            for (int i = 0; i < index; i++) cur = cur.next;
        } else {
            cur = tail;
            for (int i = size - 1; i > index; i--) cur = cur.prev;
        }
        return cur.data;
    }

    /**
     * indexOf
     * Purpose: Return first index of value (equals), or -1 if not found.
     * Time: O(n)  Space: O(1)
     */
    public int indexOf(T data) {
        Node<T> cur = head;
        int idx = 0;
        while (cur != null) {
            if ((data == null && cur.data == null) || (data != null && data.equals(cur.data))) return idx;
            cur = cur.next; idx++;
        }
        return -1;
    }

    /**
     * contains
     * Purpose: Return true if value exists in list.
     * Time: O(n)  Space: O(1)
     */
    public boolean contains(T data) {
        return indexOf(data) != -1;
    }

    // -------------------------
    // Utility operations
    // -------------------------

    /**
     * toArray
     * Purpose: Convert list to array.
     * Time: O(n)  Space: O(n)
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> cur = head;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.data;
            cur = cur.next;
        }
        return arr;
    }

    /**
     * printList
     * Purpose: Print elements from head to tail.
     * Time: O(n)  Space: O(1)
     */
    public void printList() {
        Node<T> cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.data);
            if (cur.next != null) sb.append(" <-> ");
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    // -------------------------
    // Advanced operations
    // -------------------------

    /**
     * reverseIterative
     * Purpose: Reverse list in-place using iteration.
     * Time: O(n)  Space: O(1)
     * Explanation: Swap next and prev for each node, then swap head and tail.
     */
    public void reverseIterative() {
        Node<T> cur = head;
        Node<T> temp = null;
        while (cur != null) {
            // swap prev and next
            temp = cur.prev;
            cur.prev = cur.next;
            cur.next = temp;
            cur = cur.prev; // move to original next
        }
        // after loop, temp points to previous node of null, i.e., old head
        if (temp != null) {
            head = temp.prev; // new head
            // recompute tail
            tail = head;
            while (tail != null && tail.next != null) tail = tail.next;
        }
    }

    /**
     * reverseRecursive
     * Purpose: Reverse list using recursion.
     * Time: O(n)  Space: O(n) recursion stack
     * Explanation: Recursively swap next/prev and return new head.
     */
    public void reverseRecursive() {
        head = reverseRecursiveHelper(head);
        // recompute tail
        tail = head;
        if (tail != null) {
            while (tail.next != null) tail = tail.next;
        }
    }

    private Node<T> reverseRecursiveHelper(Node<T> node) {
        if (node == null) return null;
        // swap prev and next
        Node<T> temp = node.prev;
        node.prev = node.next;
        node.next = temp;
        if (node.prev == null) return node; // new head
        return reverseRecursiveHelper(node.prev);
    }

    /**
     * findMiddle
     * Purpose: Return middle node's value (for even length returns first middle).
     * Time: O(n)  Space: O(1)
     * Explanation: Use slow-fast pointer: slow moves 1 step, fast moves 2 steps.
     */
    public T findMiddle() {
        if (isEmpty()) return null;
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    /**
     * kthFromEnd
     * Purpose: Return k-th element from end (1-based: k=1 returns last).
     * Time: O(n)  Space: O(1)
     * Explanation: Advance fast by k steps, then move both until fast reaches end.
     */
    public T kthFromEnd(int k) {
        if (k <= 0 || k > size) throw new IllegalArgumentException("k out of range");
        Node<T> fast = head, slow = head;
        for (int i = 0; i < k; i++) fast = fast.next;
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }

    /**
     * detectCycle
     * Purpose: Detect if list has a cycle using Floyd's algorithm.
     * Time: O(n)  Space: O(1)
     * Explanation: If slow and fast meet, cycle exists.
     */
    public boolean detectCycle() {
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * removeCycle
     * Purpose: If cycle exists, remove it and return true; otherwise return false.
     * Time: O(n)  Space: O(1)
     * Explanation: After detection, reset one pointer to head and move both until they meet at cycle start.
     */
    public boolean removeCycle() {
        Node<T> slow = head, fast = head;
        boolean found = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { found = true; break; }
        }
        if (!found) return false;

        slow = head;
        // If cycle starts at head, find last node in cycle
        if (slow == fast) {
            while (fast.next != slow) fast = fast.next;
            fast.next = null;
            // fix prev pointers by recomputing tail
            tail = head;
            while (tail != null && tail.next != null) tail = tail.next;
            return true;
        }

        while (slow.next != fast.next) {
            slow = slow.next;
            fast = fast.next;
        }
        // fast.next is start of cycle; break it
        fast.next = null;
        // fix prev pointers by recomputing from head
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            cur.prev = prev;
            prev = cur;
            cur = cur.next;
        }
        tail = prev;
        return true;
    }

    /**
     * insertSorted
     * Purpose: Insert value into a sorted list maintaining order.
     * Time: O(n)  Space: O(1)
     * Explanation: Traverse to find correct position and splice node.
     * Note: T should be Comparable for meaningful ordering.
     */
    public void insertSorted(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) { head = tail = node; size++; return; }
        Node<T> cur = head;
        while (cur != null && compare(cur.data, data) < 0) cur = cur.next;
        if (cur == head) { // insert at front
            node.next = head;
            head.prev = node;
            head = node;
        } else if (cur == null) { // insert at end
            tail.next = node;
            node.prev = tail;
            tail = node;
        } else { // insert before cur
            node.next = cur;
            node.prev = cur.prev;
            cur.prev.next = node;
            cur.prev = node;
        }
        size++;
    }

    /**
     * removeDuplicatesSorted
     * Purpose: Remove duplicates from a sorted list (in-place).
     * Time: O(n)  Space: O(1)
     * Explanation: Single pass, skip nodes with same value, update tail at end.
     */
    public void removeDuplicatesSorted() {
        if (isEmpty()) return;
        Node<T> cur = head;
        while (cur != null && cur.next != null) {
            if ((cur.data == null && cur.next.data == null) ||
                (cur.data != null && cur.data.equals(cur.next.data))) {
                // remove cur.next
                cur.next = cur.next.next;
                if (cur.next != null) cur.next.prev = cur;
                size--;
            } else {
                cur = cur.next;
            }
        }
        // update tail
        tail = head;
        if (tail != null) {
            while (tail.next != null) tail = tail.next;
        }
    }

    /**
     * sort
     * Purpose: Sort list using merge sort (stable).
     * Time: O(n log n)  Space: O(log n) recursion stack
     * Explanation: Split list into halves using slow-fast, recursively sort halves, merge.
     * Note: T should be Comparable for meaningful ordering.
     */
    public void sort() {
        head = mergeSort(head);
        // recompute prev pointers and tail
        Node<T> prev = null;
        Node<T> cur = head;
        while (cur != null) {
            cur.prev = prev;
            prev = cur;
            cur = cur.next;
        }
        tail = prev;
    }

    private Node<T> mergeSort(Node<T> node) {
        if (node == null || node.next == null) return node;
        Node<T> mid = getMiddleNode(node);
        Node<T> rightHead = mid.next;
        mid.next = null;
        Node<T> left = mergeSort(node);
        Node<T> right = mergeSort(rightHead);
        return mergeTwoSorted(left, right);
    }

    private Node<T> mergeTwoSorted(Node<T> a, Node<T> b) {
        Node<T> dummy = new Node<>(null);
        Node<T> tailLocal = dummy;
        while (a != null && b != null) {
            if (compare(a.data, b.data) <= 0) {
                tailLocal.next = a;
                a.prev = tailLocal;
                a = a.next;
            } else {
                tailLocal.next = b;
                b.prev = tailLocal;
                b = b.next;
            }
            tailLocal = tailLocal.next;
        }
        if (a != null) {
            tailLocal.next = a;
            a.prev = tailLocal;
        } else if (b != null) {
            tailLocal.next = b;
            b.prev = tailLocal;
        }
        Node<T> headResult = dummy.next;
        if (headResult != null) headResult.prev = null;
        return headResult;
    }

    // Helper to get middle node for merge sort (first middle for even length)
    private Node<T> getMiddleNode(Node<T> start) {
        Node<T> slow = start, fast = start;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Generic compare helper: uses Comparable if available, otherwise toString fallback
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

    // -------------------------
    // Example usage (main)
    // -------------------------
    public static void main(String[] args) {
        DoublyLinkedList<Integer> dll = new DoublyLinkedList<>();

        // Basic insertions
        dll.addFirst(2);            // 2
        dll.addFirst(1);            // 1 <-> 2
        dll.addLast(4);             // 1 <-> 2 <-> 4
        dll.addAt(2, 3);            // 1 <-> 2 <-> 3 <-> 4
        System.out.print("After inserts: ");
        dll.printList();

        // Access and search
        System.out.println("Size: " + dll.size());          // 4
        System.out.println("Index of 3: " + dll.indexOf(3)); // 2
        System.out.println("Contains 5? " + dll.contains(5));// false

        // Removals
        System.out.println("removeAt(2): " + dll.removeAt(2)); // removes 3
        System.out.print("After removeAt: ");
        dll.printList();

        System.out.println("removeFirst: " + dll.removeFirst()); // removes 1
        System.out.println("removeLast: " + dll.removeLast());   // removes 4
        System.out.print("After removeFirst/removeLast: ");
        dll.printList();

        // Rebuild and advanced ops
        dll.addLast(5); dll.addLast(6); dll.addFirst(0); // 0 <-> 2 <-> 5 <-> 6
        System.out.print("Rebuilt: ");
        dll.printList();

        System.out.println("Middle: " + dll.findMiddle());
        System.out.println("2nd from end: " + dll.kthFromEnd(2));

        // Sorting
        DoublyLinkedList<Integer> s = new DoublyLinkedList<>();
        s.addLast(4); s.addLast(1); s.addLast(3); s.addLast(2);
        System.out.print("Before sort: ");
        s.printList();
        s.sort();
        System.out.print("After sort: ");
        s.printList();

        // Remove duplicates (sorted)
        s.addLast(2); s.addLast(2);
        System.out.print("With duplicates: ");
        s.printList();
        s.removeDuplicatesSorted();
        System.out.print("Duplicates removed: ");
        s.printList();
    }
}


/*
 * Doubly Linked List — Short Note
 *
 * Definition
 * A doubly linked list is a linear data structure where each node contains a value
 * and two references: prev (previous node) and next (next node).
 * Layout: head <-> node <-> node <-> ... <-> tail  (both directions)
 *
 * Key Properties
 * - Bidirectional traversal: can move forward and backward in O(1) per step.
 * - Maintains head and tail pointers for O(1) access to both ends.
 * - Dynamic size: grows/shrinks without resizing contiguous memory.
 *
 * Common Operations (time / extra space)
 * - addFirst(value): O(1) time, O(1) space
 * - addLast(value): O(1) time, O(1) space
 * - addAt(index, value): O(min(index, n-index)) time, O(1) space
 * - removeFirst(): O(1) time, O(1) space
 * - removeLast(): O(1) time, O(1) space
 * - removeAt(index): O(min(index, n-index)) time, O(1) space
 * - get(index): O(min(index, n-index)) time, O(1) space
 * - indexOf(value): O(n) time, O(1) space
 * - reverse (iterative): O(n) time, O(1) extra space
 * - find middle (slow/fast): O(n) time, O(1) space
 * - detect cycle (Floyd): O(n) time, O(1) space
 * - sort (merge sort): O(n log n) time, O(log n) recursion stack
 *
 * Advantages
 * - O(1) insertions/removals at both ends and O(1) removal given node reference.
 * - Efficient bidirectional traversal; easier to delete a node when you have its pointer.
 *
 * Drawbacks
 * - Extra memory per node for prev pointer.
 * - No O(1) random access (indexing is O(n)).
 * - Slightly more complex pointer management (prev/next updates).
 *
 * Use Cases
 * - Implementing deques, LRU caches, and data structures requiring fast insert/delete
 *   at both ends or quick removal given a node reference.
 */
