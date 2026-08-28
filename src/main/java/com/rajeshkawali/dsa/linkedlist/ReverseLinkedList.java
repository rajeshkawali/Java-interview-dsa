package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to reverse a singly linked list.
 *
 * Logic Explanation:
 * ------------------
 * - Each node has data and a reference to the next node.
 * - To reverse, we need to rewire the `next` pointers so that they point backwards.
 * - Iterative approach:
 *   Traverse the list once, keep track of previous, current, and next nodes, and reverse links.
 * - Recursive approach:
 *   Recursively reverse the rest of the list, then fix the current node’s pointer.
 *
 * Time & Space Complexity:
 * ------------------------
 * - Iterative reverse: O(n) time, O(1) space
 * - Recursive reverse: O(n) time, O(n) space (recursion stack)
 */

public class ReverseLinkedList {

    // Node class
    static class Node {
        int data;
        Node next;
        Node(int data) { this.data = data; }
    }

    private Node head;

    /** Add element at end of list */
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = newNode;
    }

    /** Print list elements */
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
     * Reverse linked list iteratively.
     * Logic: Traverse once, rewire pointers.
     * Time: O(n), Space: O(1)
     */
    public void reverseIterative() {
        Node prev = null, cur = head, next;
        while (cur != null) {
            next = cur.next;   // store next
            cur.next = prev;   // reverse link
            prev = cur;        // move prev forward
            cur = next;        // move cur forward
        }
        head = prev;
    }

    /**
     * Reverse linked list recursively.
     * Logic: Reverse rest of list, then fix current node.
     * Time: O(n), Space: O(n) recursion stack
     */
    public void reverseRecursive() {
        head = reverseRec(head, null);
    }

    private Node reverseRec(Node cur, Node prev) {
        if (cur == null) return prev;
        Node next = cur.next;
        cur.next = prev;
        return reverseRec(next, cur);
    }

    // Demo
    public static void main(String[] args) {
        ReverseLinkedList list = new ReverseLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 4

        list.reverseIterative();
        System.out.print("Reversed Iterative: ");
        list.printList(); // 4 -> 3 -> 2 -> 1

        list.reverseRecursive();
        System.out.print("Reversed Recursive: ");
        list.printList(); // 1 -> 2 -> 3 -> 4
    }
}
