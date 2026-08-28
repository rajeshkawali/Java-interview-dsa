package com.rajeshkawali.dsa.linkedlist;
/**
 * SinglyLinkedList
 *
 * Single-file implementation of a singly linked list with common operations.
 * Each method contains:
 *  - short description
 *  - time complexity
 *  - space complexity
 *  - explanation of behavior
 *
 * All code is self-contained in this single class.
 */
public class SinglyLinkedList<T> {

    // Node class (inner static)
    private static class Node<T> {
        T value;
        Node<T> next;
        Node(T value) { this.value = value; }
    }

    // Head and tail references and size counter
    private Node<T> head;
    private Node<T> tail;
    private int size;

    // Constructor
    public SinglyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // -------------------------
    // Basic properties
    // -------------------------

    /**
     * isEmpty
     * Returns true if list has no elements.
     * Time: O(1), Space: O(1)
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * size
     * Returns number of elements in the list.
     * Time: O(1), Space: O(1)
     */
    public int size() {
        return size;
    }

    // -------------------------
    // Insertion operations
    // -------------------------

    /**
     * addFirst
     * Insert value at the beginning (head) of the list.
     * Time: O(1), Space: O(1)
     * Explanation: Create new node, point its next to current head, update head.
     * If list was empty, tail also points to new node.
     */
	public void addFirst(T value) {
		Node<T> node = new Node<>(value);
		node.next = head;
		head = node;
		if (tail == null) {
			tail = node;
		}
		size++;
	}

    /**
     * addLast
     * Insert value at the end (tail) of the list.
     * Time: O(1), Space: O(1)
     * Explanation: Use tail pointer to append in constant time.
     * If list empty, head and tail become the new node.
     */
	public void addLast(T value) {
		Node<T> node = new Node<>(value);
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}
		size++;
	}

    /**
     * addAt
     * Insert value at specified index (0-based).
     * Time: O(min(index, n-index)) ~ O(n) worst, Space: O(1)
     * Explanation: If index==0 -> addFirst; if index==size -> addLast;
     * otherwise traverse to (index-1) and splice node.
     */
	public void addAt(int index, T value) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
		if (index == 0) {
			addFirst(value);
			return;
		}
		if (index == size) {
			addLast(value);
			return;
		}
		Node<T> prev = head;
		for (int i = 1; i < index; i++) {
			prev = prev.next;
		}
		Node<T> node = new Node<>(value);
		node.next = prev.next;
		prev.next = node;
		size++;
	}

    // -------------------------
    // Removal operations
    // -------------------------

    /**
     * removeFirst
     * Remove and return the first element.
     * Time: O(1), Space: O(1)
     * Explanation: Move head to head.next. If list becomes empty, tail = null.
     */
    public T removeFirst() {
        if (isEmpty()) return null;
        T val = head.value;
        head = head.next;
        size--;
        if (head == null) tail = null;
        return val;
    }

    /**
     * removeLast
     * Remove and return the last element.
     * Time: O(n) (must find previous node), Space: O(1)
     * Explanation: If single element, clear head/tail. Otherwise traverse to node before tail.
     */
    public T removeLast() {
        if (isEmpty()) return null;
        if (head == tail) { // single element
            T val = head.value;
            head = tail = null;
            size = 0;
            return val;
        }
        Node<T> cur = head;
        while (cur.next != tail) cur = cur.next;
        T val = tail.value;
        tail = cur;
        tail.next = null;
        size--;
        return val;
    }

    /**
     * removeAt
     * Remove and return element at index.
     * Time: O(n), Space: O(1)
     * Explanation: If index==0 -> removeFirst; else traverse to (index-1) and unlink.
     */
    public T removeAt(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<T> prev = head;
        for (int i = 1; i < index; i++) prev = prev.next;
        T val = prev.next.value;
        prev.next = prev.next.next;
        size--;
        return val;
    }

    /**
     * clear
     * Remove all elements.
     * Time: O(1) (just drop references), Space: O(1)
     * Explanation: Let GC reclaim nodes by removing head/tail references.
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
     * Return value at index (0-based) without removing.
     * Time: O(n), Space: O(1)
     * Explanation: Traverse from head to index.
     */
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        Node<T> cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.value;
    }

    /**
     * indexOf
     * Return first index of value (equals), or -1 if not found.
     * Time: O(n), Space: O(1)
     */
    public int indexOf(T value) {
        Node<T> cur = head;
        int idx = 0;
        while (cur != null) {
            if ((value == null && cur.value == null) || (value != null && value.equals(cur.value))) return idx;
            cur = cur.next;
            idx++;
        }
        return -1;
    }

    /**
     * contains
     * Return true if value exists in list.
     * Time: O(n), Space: O(1)
     */
    public boolean contains(T value) {
        return indexOf(value) != -1;
    }

    // -------------------------
    // Utility operations
    // -------------------------

    /**
     * toArray
     * Convert list to array.
     * Time: O(n), Space: O(n)
     */
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node<T> cur = head;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.value;
            cur = cur.next;
        }
        return arr;
    }

    /**
     * printList
     * Print elements in order.
     * Time: O(n), Space: O(1)
     */
    public void printList() {
        Node<T> cur = head;
        StringBuilder sb = new StringBuilder();
        while (cur != null) {
            sb.append(cur.value);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        System.out.println(sb.toString());
    }

    // -------------------------
    // Advanced operations
    // -------------------------

    /**
     * reverseIterative
     * Reverse list in-place using iteration.
     * Time: O(n), Space: O(1)
     * Explanation: Standard three-pointer reversal (prev, curr, next).
     */
    public void reverseIterative() {
        Node<T> prev = null;
        Node<T> curr = head;
        tail = head; // after reversal old head becomes tail
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
    }

    /**
     * reverseRecursive
     * Reverse list using recursion.
     * Time: O(n), Space: O(n) recursion stack
     * Explanation: Recursively reverse sublist and fix pointers on unwind.
     */
    public void reverseRecursive() {
        tail = head;
        head = reverseRecursiveHelper(head);
    }

    private Node<T> reverseRecursiveHelper(Node<T> node) {
        if (node == null || node.next == null) return node;
        Node<T> newHead = reverseRecursiveHelper(node.next);
        node.next.next = node;
        node.next = null;
        return newHead;
    }

    /**
     * findMiddle
     * Return middle node's value (for even length returns first middle).
     * Time: O(n), Space: O(1)
     * Explanation: Use slow-fast pointer: slow moves 1 step, fast moves 2 steps.
     */
    public T findMiddle() {
        if (isEmpty()) return null;
        Node<T> slow = head, fast = head;
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.value;
    }

    /**
     * kthFromEnd
     * Return k-th element from end (1-based: k=1 returns last).
     * Time: O(n), Space: O(1)
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
        return slow.value;
    }

    /**
     * detectCycle
     * Detect if list has a cycle using Floyd's algorithm.
     * Time: O(n), Space: O(1)
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
     * If cycle exists, remove it and return true; otherwise return false.
     * Time: O(n), Space: O(1)
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
        Node<T> prev = null; // to find node before meeting point
        while (slow != fast) {
            prev = fast;
            slow = slow.next;
            fast = fast.next;
        }
        // 'fast' (or slow) is at cycle start; prev is node before cycle start
        // Break the cycle
        if (prev != null) prev.next = null;
        // Recompute tail (optional)
        tail = head;
        if (tail != null) {
            while (tail.next != null) tail = tail.next;
        }
        return true;
    }

    /**
     * removeDuplicatesSorted
     * Remove duplicates from a sorted list (in-place).
     * Time: O(n), Space: O(1)
     * Explanation: Single pass, skip nodes with same value.
     */
    public void removeDuplicatesSorted() {
        if (isEmpty()) return;
        Node<T> cur = head;
        while (cur != null && cur.next != null) {
            if ((cur.value == null && cur.next.value == null) ||
                (cur.value != null && cur.value.equals(cur.next.value))) {
                cur.next = cur.next.next;
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
     * mergeSort (for linked list)
     * Sort list using merge sort (stable).
     * Time: O(n log n), Space: O(log n) recursion stack + O(1) extra for merging nodes
     * Explanation: Split list into halves using slow-fast, recursively sort halves, merge.
     */
    public void sort() {
        head = mergeSort(head);
        // recompute tail
        tail = head;
        if (tail != null) {
            while (tail.next != null) tail = tail.next;
        }
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
            // We need to compare generics; assume T implements Comparable or use toString fallback
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
        // Fallback: compare string representations
        return x.toString().compareTo(y.toString());
    }

    private Node<T> getMiddleNode(Node<T> start) {
        Node<T> slow = start, fast = start;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // -------------------------
    // Example usage (main)
    // -------------------------
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        // Insertions
        list.addLast(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addAt(2, 99); // list: 1 -> 2 -> 99 -> 3 -> 4
        System.out.print("Initial: ");
        list.printList();

        // Access
        System.out.println("Size: " + list.size()); // 5
        System.out.println("Index of 99: " + list.indexOf(99)); // 2
        System.out.println("Contains 5? " + list.contains(5)); // false

        // Removal
        System.out.println("removeAt(2): " + list.removeAt(2)); // removes 99
        System.out.print("After removeAt: ");
        list.printList();

        // Reverse
        list.reverseIterative();
        System.out.print("Reversed: ");
        list.printList();
        list.reverseRecursive(); // back to original
        System.out.print("Reversed back (recursive): ");
        list.printList();

        // kth from end
        System.out.println("2nd from end: " + list.kthFromEnd(2));

        // Middle
        System.out.println("Middle: " + list.findMiddle());

        // Sort
        list.addLast(0);
        list.addLast(7);
        System.out.print("Before sort: ");
        list.printList();
        list.sort();
        System.out.print("After sort: ");
        list.printList();

        // Remove duplicates (requires sorted list)
        list.addLast(7);
        list.addLast(7);
        System.out.print("With duplicates: ");
        list.printList();
        list.removeDuplicatesSorted();
        System.out.print("Duplicates removed: ");
        list.printList();

        // Cycle detection demo (create a cycle manually)
        // WARNING: creating cycle will break printList/demonstrations if not removed.
        // For demonstration only:
        // list.tail.next = list.head.next; // create cycle
        // System.out.println("Has cycle? " + list.detectCycle());
        // list.removeCycle();
        // System.out.println("Cycle removed. Has cycle? " + list.detectCycle());
    }
}


/*
 * Singly Linked List — Short Note
 *
 * Definition
 * A singly linked list is a linear data structure composed of nodes where each node
 * contains a value and a reference (pointer) to the next node. The list has a head
 * (first node) and optionally a tail (last node).
 *
 * Structure
 * Node { value, next }         // head -> node -> node -> ... -> tail -> null
 *
 * Key Properties
 * - Sequential access only (no O(1) random indexing).
 * - Dynamic size: grows/shrinks at runtime without resizing.
 * - Optional tail pointer enables O(1) append.
 *
 * Common Operations (time / extra space)
 * - addFirst(value): O(1) time, O(1) space
 * - addLast(value): O(1) time if tail maintained, otherwise O(n); O(1) space
 * - addAt(index, value): O(n) time, O(1) space
 * - removeFirst(): O(1) time, O(1) space
 * - removeLast(): O(n) time (unless doubly linked or prev pointer), O(1) space
 * - removeAt(index): O(n) time, O(1) space
 * - get(index): O(n) time, O(1) space
 * - indexOf(value): O(n) time, O(1) space
 * - reverse (iterative): O(n) time, O(1) extra space
 * - find middle (slow/fast): O(n) time, O(1) space
 * - kth from end (two-pointer): O(n) time, O(1) space
 * - detect cycle (Floyd): O(n) time, O(1) space
 * - sort (merge sort for linked list): O(n log n) time, O(log n) recursion stack
 *
 * Advantages
 * - Fast O(1) insert/delete at head (and at tail if tail pointer exists).
 * - Flexible memory usage; no contiguous block required.
 * - Easy to splice or move sublists by pointer changes.
 *
 * Drawbacks
 * - No constant-time random access (indexing is O(n)).
 * - Extra memory per element for the next pointer.
 * - Pointer manipulation is error-prone (null checks, cycles).
 *
 * Typical Use Cases
 * - Implementing stacks and queues (when frequent head/tail ops required).
 * - When frequent insertions/removals at known positions are needed and random access is not.
 * - When memory fragmentation or resizing of arrays is undesirable.
 */
