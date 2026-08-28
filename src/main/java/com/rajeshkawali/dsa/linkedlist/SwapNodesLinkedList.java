package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to swap two nodes in a singly linked list.
 *
 * Logic Explanation:
 * ------------------
 * - We are swapping nodes, not just data.
 * - Steps:
 *   1. Find the previous and current references for both nodes (x and y).
 *   2. If either node is not found, return.
 *   3. Adjust the previous nodes’ next pointers to point to the opposite node.
 *   4. Swap the next pointers of the two nodes.
 *
 * Example:
 * Input: 1 -> 2 -> 3 -> 4 -> 5, swap(2, 4)
 * Output: 1 -> 4 -> 3 -> 2 -> 5
 *
 * Time & Space Complexity:
 * ------------------------
 * - Time: O(n) (single traversal to find nodes)
 * - Space: O(1) (in-place swap)
 */

public class SwapNodesLinkedList {

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
     * Swap two nodes by value (not just data).
     * Time: O(n), Space: O(1)
     */
    public void swapNodes(int x, int y) {
        if (x == y) return; // no need to swap same values

        // Search for x (keep track of prevX and currX)
        Node prevX = null, currX = head;
        while (currX != null && currX.data != x) {
            prevX = currX;
            currX = currX.next;
        }

        // Search for y (keep track of prevY and currY)
        Node prevY = null, currY = head;
        while (currY != null && currY.data != y) {
            prevY = currY;
            currY = currY.next;
        }

        // If either x or y is not present, return
        if (currX == null || currY == null) return;

        // If x is not head
        if (prevX != null) prevX.next = currY;
        else head = currY;

        // If y is not head
        if (prevY != null) prevY.next = currX;
        else head = currX;

        // Swap next pointers
        Node temp = currX.next;
        currX.next = currY.next;
        currY.next = temp;
    }

    // Demo
    public static void main(String[] args) {
        SwapNodesLinkedList list = new SwapNodesLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.print("Original List: ");
        list.printList(); // 1 -> 2 -> 3 -> 4 -> 5

        list.swapNodes(2, 4);
        System.out.print("After swapping 2 and 4: ");
        list.printList(); // 1 -> 4 -> 3 -> 2 -> 5

        list.swapNodes(1, 5);
        System.out.print("After swapping 1 and 5: ");
        list.printList(); // 5 -> 4 -> 3 -> 2 -> 1
    }
}
