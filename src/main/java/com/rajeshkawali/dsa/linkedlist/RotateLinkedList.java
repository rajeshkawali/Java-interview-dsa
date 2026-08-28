package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to rotate a singly linked list by k positions.
 *
 * Logic Explanation:
 * ------------------
 * - Rotating means moving the last k nodes to the front.
 * - Steps:
 *   1. Find the length of the list.
 *   2. Connect the tail to the head (make it circular).
 *   3. Find the new head: (length - k % length)th node.
 *   4. Break the circle at that point.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5, k = 2
 * Output: 4 -> 5 -> 1 -> 2 -> 3
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (single traversal)
 * - Space: O(1) (in-place rotation)
 */

public class RotateLinkedList {

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

    /** Print list */
    public void printList() {
        Node cur = head;
        while (cur != null) {
            System.out.print(cur.data);
            cur = cur.next;
            if (cur != null) System.out.print(" -> ");
        }
        System.out.println();
    }

    /**
     * Rotate list by k positions.
     * Time: O(n), Space: O(1)
     */
    public void rotate(int k) {
        if (head == null || head.next == null || k == 0) return;

        // Step 1: Find length and tail
        Node tail = head;
        int length = 1;
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }

        // Step 2: Make it circular
        tail.next = head;

        // Step 3: Find new head (length - k % length steps from start)
        k = k % length;
        int stepsToNewHead = length - k;
        Node newTail = head;
        for (int i = 1; i < stepsToNewHead; i++) {
            newTail = newTail.next;
        }

        // Step 4: Break the circle
        head = newTail.next;
        newTail.next = null;
    }

    // Demo
    public static void main(String[] args) {
        RotateLinkedList list = new RotateLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5

        list.rotate(2);
        System.out.print("After rotating by 2: ");
        list.printList(); // 4 -> 5 -> 1 -> 2 -> 3

        list.rotate(3);
        System.out.print("After rotating by 3: ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5 (back to original)
    }
}
