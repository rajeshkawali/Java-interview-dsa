package com.rajeshkawali.dsa.linkedlist;

import java.util.HashSet;

/**
 * Program to remove duplicate nodes from a singly linked list.
 *
 * Logic Explanation:
 * ------------------
 * - Approach 1 (HashSet):
 *   Traverse the list, keep track of seen values in a HashSet.
 *   If a node’s value is already seen, remove it by skipping the node.
 *   Time: O(n), Space: O(n).
 *
 * - Approach 2 (No extra space):
 *   Use two loops: outer loop picks each node, inner loop checks all subsequent nodes and removes duplicates.
 *   Time: O(n²), Space: O(1).
 */

public class RemoveDuplicatesLinkedList {

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
     * Remove duplicates using HashSet.
     * Time: O(n), Space: O(n)
     */
    public void removeDuplicatesWithHashSet() {
        HashSet<Integer> seen = new HashSet<>();
        Node cur = head, prev = null;
        while (cur != null) {
            if (seen.contains(cur.data)) {
                prev.next = cur.next; // skip duplicate
            } else {
                seen.add(cur.data);
                prev = cur;
            }
            cur = cur.next;
        }
    }

    /**
     * Remove duplicates without extra space (nested loops).
     * Time: O(n²), Space: O(1)
     */
    public void removeDuplicatesNoExtraSpace() {
        Node cur = head;
        while (cur != null) {
            Node runner = cur;
            while (runner.next != null) {
                if (runner.next.data == cur.data) {
                    runner.next = runner.next.next; // skip duplicate
                } else {
                    runner = runner.next;
                }
            }
            cur = cur.next;
        }
    }

    // Demo
    public static void main(String[] args) {
        RemoveDuplicatesLinkedList list = new RemoveDuplicatesLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(4);
        list.add(3);
        list.add(5);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 2 -> 4 -> 3 -> 5

        list.removeDuplicatesWithHashSet();
        System.out.print("After removing duplicates (HashSet): ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5

        // Reset list for second demo
        RemoveDuplicatesLinkedList list2 = new RemoveDuplicatesLinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);
        list2.add(2);
        list2.add(4);
        list2.add(3);
        list2.add(5);

        list2.removeDuplicatesNoExtraSpace();
        System.out.print("After removing duplicates (No Extra Space): ");
        list2.printList(); // 1 -> 2 -> 3 -> 4 -> 5
    }
}
