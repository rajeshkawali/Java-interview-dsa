package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to detect the starting node of a cycle in a singly linked list.
 *
 * Logic Explanation:
 * ------------------
 * 1. Use Floyd’s Cycle Detection:
 *    - Move slow pointer by 1 step, fast pointer by 2 steps.
 *    - If they meet, a cycle exists.
 * 2. To find the starting node:
 *    - Keep one pointer at meeting point, reset the other to head.
 *    - Move both one step at a time.
 *    - The node where they meet again is the start of the cycle.
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (each pointer traverses at most n nodes)
 * - Space: O(1) (constant extra space)
 */

public class DetectCycleStartLinkedList {

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
     * Detect cycle and return starting node if exists.
     * Time: O(n), Space: O(1)
     */
    public Node detectCycleStart() {
        Node slow = head, fast = head;

        // Step 1: Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) { // cycle detected
                // Step 2: Find start of cycle
                Node ptr1 = head;
                Node ptr2 = slow;
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
                return ptr1; // start of cycle
            }
        }
        return null; // no cycle
    }

    // Demo
    public static void main(String[] args) {
        DetectCycleStartLinkedList list = new DetectCycleStartLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        list.createCycle(2); // create cycle at node with value 3

        Node cycleStart = list.detectCycleStart();
        if (cycleStart != null) {
            System.out.println("Cycle starts at node with value: " + cycleStart.data);
        } else {
            System.out.println("No cycle detected.");
        }
    }
}
