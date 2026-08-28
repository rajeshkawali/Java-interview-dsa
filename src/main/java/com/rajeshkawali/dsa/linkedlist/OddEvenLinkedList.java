package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to rearrange a singly linked list into odd-even order.
 *
 * Logic Explanation:
 * ------------------
 * - Odd nodes are those at positions 1, 3, 5, ...
 * - Even nodes are those at positions 2, 4, 6, ...
 * - We split the list into two lists: odd and even.
 * - Then we connect the odd list’s tail to the head of the even list.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5
 * Output: 1 -> 3 -> 5 -> 2 -> 4
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (single traversal)
 * - Space: O(1) (in-place rearrangement)
 */

public class OddEvenLinkedList {

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
     * Rearrange list into odd-even order.
     * Time: O(n), Space: O(1)
     */
    public void oddEvenRearrange() {
        if (head == null || head.next == null) return;

        Node odd = head;
        Node even = head.next;
        Node evenHead = even; // keep start of even list

        while (even != null && even.next != null) {
            odd.next = even.next;   // link odd to next odd
            odd = odd.next;         // move odd forward
            even.next = odd.next;   // link even to next even
            even = even.next;       // move even forward
        }

        odd.next = evenHead; // connect odd list to even list
    }

    // Demo
    public static void main(String[] args) {
        OddEvenLinkedList list = new OddEvenLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5

        list.oddEvenRearrange();
        System.out.print("Odd-Even Rearranged List: ");
        list.printList(); // 1 -> 3 -> 5 -> 2 -> 4
    }
}
