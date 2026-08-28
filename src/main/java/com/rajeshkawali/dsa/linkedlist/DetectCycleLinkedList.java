package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to check if a singly linked list contains a cycle.
 *
 * Logic Explanation:
 * ------------------
 * - A cycle exists if a node’s next pointer eventually points back to a previous node.
 * - Floyd’s Cycle Detection (Tortoise and Hare):
 *   1. Use two pointers: slow (moves 1 step) and fast (moves 2 steps).
 *   2. If there is a cycle, slow and fast will eventually meet.
 *   3. If fast reaches null (end of list), there is no cycle.
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (each pointer traverses at most n nodes)
 * - Space: O(1) (constant extra space)
 */

public class DetectCycleLinkedList {

    // Node class
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;

    /** Add element at end */
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) { head = newNode; return; }
        Node cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = newNode;
    }

    /** Create a cycle manually for testing (connect tail to given index) */
    public void createCycle(int index) {
        if (head == null) return;
        Node cycleNode = head;
        for (int i = 0; i < index && cycleNode != null; i++) {
            cycleNode = cycleNode.next;
        }
        if (cycleNode == null) return;
        Node tail = head;
        while (tail.next != null) tail = tail.next;
        tail.next = cycleNode; // create cycle
    }

    /**
     * Detect cycle using Floyd’s algorithm.
     * Time: O(n), Space: O(1)
     */
    public boolean hasCycle() {
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;         // move 1 step
            fast = fast.next.next;    // move 2 steps
            if (slow == fast) return true; // cycle detected
        }
        return false;
    }

    // Demo
    public static void main(String[] args) {
        DetectCycleLinkedList list = new DetectCycleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println("Has cycle? " + list.hasCycle()); // false

        list.createCycle(2); // create cycle at node with value 3
        System.out.println("Has cycle after creating? " + list.hasCycle()); // true
    }
}
