package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to delete N nodes after M nodes in a singly linked list.
 *
 * Logic Explanation:
 * ------------------
 * - Traverse the list, skipping M nodes.
 * - Then delete the next N nodes by adjusting pointers.
 * - Continue until the end of the list.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, M = 2, N = 2
 * Output: 1 -> 2 -> 5 -> 6
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (single traversal of the list)
 * - Space: O(1) (in-place deletion)
 */

public class DeleteNAfterM {

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
     * Delete N nodes after M nodes.
     * Time: O(n), Space: O(1)
     */
    public void deleteNAfterM(int M, int N) {
        Node cur = head;

        while (cur != null) {
            // Step 1: Skip M nodes
            for (int i = 1; i < M && cur != null; i++) {
                cur = cur.next;
            }

            if (cur == null) break;

            // Step 2: Delete next N nodes
            Node temp = cur.next;
            for (int i = 0; i < N && temp != null; i++) {
                temp = temp.next;
            }

            // Step 3: Connect current node to remaining list
            cur.next = temp;

            // Step 4: Move current pointer forward
            cur = temp;
        }
    }

    // Demo
    public static void main(String[] args) {
        DeleteNAfterM list = new DeleteNAfterM();
        for (int i = 1; i <= 8; i++) list.add(i);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8

        list.deleteNAfterM(2, 2);
        System.out.print("After deleting 2 nodes after every 2 nodes: ");
        list.printList(); // 1 -> 2 -> 5 -> 6
    }
}
