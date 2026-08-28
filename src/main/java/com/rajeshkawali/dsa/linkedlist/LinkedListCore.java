package com.rajeshkawali.dsa.linkedlist;
/**
 * LinkedListCore
 *
 * Single-file Java implementation of core singly-linked-list operations with
 * clear comments and complexity notes for each method.
 *
 * Supported operations:
 *  - access by index (get)
 *  - insert at head
 *  - insert at tail (O(1) using maintained tail pointer)
 *  - delete first occurrence by value
 *  - delete node given pointer (special O(1) trick; cannot delete tail)
 *  - search value (contains)
 *  - reverse list (iterative)
 *  - detect cycle (Floyd)
 *  - find middle (slow/fast)
 *
 * Notes:
 *  - Methods that modify the list return void and update the LinkedListCore instance.
 *  - Methods that need to return a new head (if used standalone) are implemented as instance methods here.
 *  - Complexity comments are placed above each method.
 */
public class LinkedListCore {

    /** Basic singly linked list node */
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int v) { val = v; }
    }

    /* Head and tail pointers maintained for O(1) tail insert */
    private ListNode head;
    private ListNode tail;
    private int size;

    public LinkedListCore() {
        head = null;
        tail = null;
        size = 0;
    }

    /* -------------------------
     * Utility / factory methods
     * ------------------------- */

    /** Build a list from an int array (returns a new LinkedListCore) */
    public static LinkedListCore fromArray(int[] arr) {
        LinkedListCore list = new LinkedListCore();
        for (int v : arr) list.insertTail(v);
        return list;
    }

    /** Convert list to string for printing */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = head;
        while (cur != null) {
            sb.append(cur.val);
            if (cur.next != null) sb.append(" -> ");
            cur = cur.next;
        }
        return sb.toString();
    }

    /* -------------------------
     * Core operations
     * ------------------------- */

    /**
     * Access by index
     * Time: O(n)  (must traverse up to index)
     * Space (aux): O(1)
     *
     * Returns the value at index (0-based). Throws IndexOutOfBoundsException if invalid.
     */
    public int get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException();
        ListNode cur = head;
        for (int i = 0; i < index; i++) cur = cur.next;
        return cur.val;
    }

    /**
     * Insert at head
     * Time: O(1)
     * Space (aux): O(1)
     *
     * Inserts a new node with value val at the beginning of the list.
     */
    public void insertHead(int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        head = node;
        if (tail == null) tail = node; // first node
        size++;
    }

    /**
     * Insert at tail (O(1) because tail pointer is maintained)
     * Time: O(1)
     * Space (aux): O(1)
     *
     * Appends a new node with value val at the end of the list.
     */
    public void insertTail(int val) {
        ListNode node = new ListNode(val);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /**
     * Delete first occurrence by value
     * Time: O(n) (must search for value)
     * Space (aux): O(1)
     *
     * Removes the first node whose value equals val. If not found, list unchanged.
     */
    public void deleteByValue(int val) {
        if (head == null) return;
        if (head.val == val) {
            head = head.next;
            if (head == null) tail = null;
            size--;
            return;
        }
        ListNode prev = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                if (cur == tail) tail = prev;
                size--;
                return;
            }
            prev = cur;
            cur = cur.next;
        }
    }

    /**
     * Delete node given pointer (special O(1) trick)
     * Time: O(1)
     * Space (aux): O(1)
     *
     * Given a direct reference to a node in the list, delete it in O(1) by copying
     * the next node's value and bypassing it. This cannot delete the tail node.
     *
     * Returns true if deletion succeeded, false if node is null or tail (cannot delete).
     */
    public boolean deleteNodeGivenPointer(ListNode node) {
        if (node == null || node.next == null) {
            // cannot delete tail or null using this trick
            return false;
        }
        node.val = node.next.val;
        node.next = node.next.next;
        if (node.next == null) {
            // we deleted the old tail; update tail reference
            tail = node;
        }
        size--;
        return true;
    }

    /**
     * Search value (contains)
     * Time: O(n)
     * Space (aux): O(1)
     *
     * Returns true if value exists in the list.
     */
    public boolean contains(int val) {
        ListNode cur = head;
        while (cur != null) {
            if (cur.val == val) return true;
            cur = cur.next;
        }
        return false;
    }

    /**
     * Reverse list iteratively
     * Time: O(n)
     * Space (aux): O(1)
     *
     * Reverses the list in-place and updates head/tail.
     */
    public void reverse() {
        ListNode prev = null;
        ListNode cur = head;
        tail = head; // old head becomes new tail
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        head = prev;
    }

    /**
     * Detect cycle (Floyd's algorithm)
     * Time: O(n)
     * Space (aux): O(1)
     *
     * Returns true if a cycle exists.
     */
    public boolean hasCycle() {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Find cycle start (if any)
     * Time: O(n)
     * Space (aux): O(1)
     *
     * Returns the node where the cycle begins, or null if no cycle.
     */
    public ListNode detectCycleStart() {
        ListNode slow = head, fast = head;
        boolean found = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { found = true; break; }
        }
        if (!found) return null;
        ListNode ptr = head;
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }
        return ptr;
    }

    /**
     * Find middle node (returns second middle for even length)
     * Time: O(n)
     * Space (aux): O(1)
     *
     * Uses slow/fast pointers. For list of length 2k returns node at index k (0-based).
     */
    public ListNode findMiddle() {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    /* -------------------------
     * Additional helpers for testing
     * ------------------------- */

    /** Return current size (O(1)) */
    public int size() { return size; }

    /** Return head node (for advanced operations/tests) */
    public ListNode getHead() { return head; }

    /** Return tail node (for advanced operations/tests) */
    public ListNode getTail() { return tail; }

    /* -------------------------
     * Demonstration main
     * ------------------------- */
    public static void main(String[] args) {
        LinkedListCore list = new LinkedListCore();
        System.out.println("=================================================================");
        // Insert at head and tail
        list.insertHead(3); // 3
        list.insertHead(2); // 2 -> 3
        list.insertTail(4); // 2 -> 3 -> 4
        list.insertHead(1); // 1 -> 2 -> 3 -> 4
        System.out.println("List after inserts: " + list); // 1 -> 2 -> 3 -> 4
        System.out.println("Size: " + list.size()); // 4
        System.out.println("=================================================================");
        // Access by index
        System.out.println("Element at index 2: " + list.get(2)); // 3
        System.out.println("=================================================================");
        // Search
        System.out.println("Contains 3? " + list.contains(3)); // true
        System.out.println("Contains 5? " + list.contains(5)); // false
        System.out.println("=================================================================");
        // Delete by value
        list.deleteByValue(3); // removes first 3 -> list: 1 -> 2 -> 4
        System.out.println("After deleteByValue(3): " + list);
        System.out.println("=================================================================");
        // Delete node given pointer (O(1) trick)
        ListNode node = list.getHead().next; // node with value 2
        boolean deleted = list.deleteNodeGivenPointer(node); // deletes node 2 by copying next (4) into it
        System.out.println("deleteNodeGivenPointer succeeded? " + deleted);
        System.out.println("After deleteNodeGivenPointer: " + list); // 1 -> 4
        System.out.println("=================================================================");
        // Reverse
        list.reverse();
        System.out.println("After reverse: " + list); // 4 -> 1
        System.out.println("=================================================================");
        // Find middle
        System.out.println("Middle node value: " + (list.findMiddle() != null ? list.findMiddle().val : "null"));
        System.out.println("=================================================================");
        // Cycle detection demo
        // create a cycle: tail.next -> head
        list.getTail().next = list.getHead();
        System.out.println("Has cycle? " + list.hasCycle()); // true
        System.out.println("Cycle start node value: " + (list.detectCycleStart() != null ? list.detectCycleStart().val : "null"));
        System.out.println("=================================================================");
        // IMPORTANT: break cycle to avoid infinite loops in further operations
        // For demo only: manually break by setting tail.next = null (we know tail is node with value 4)
        // In general, careful handling is required.
        list.getTail().next = null; // break cycle
        System.out.println("Cycle broken. Has cycle? " + list.hasCycle());
        System.out.println("=================================================================");
    }
}
