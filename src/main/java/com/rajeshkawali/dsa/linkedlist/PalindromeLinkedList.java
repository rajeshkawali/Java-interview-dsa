package com.rajeshkawali.dsa.linkedlist;

/**
 * Program to check if a singly linked list is a palindrome.
 *
 * Logic Explanation:
 * ------------------
 * - A palindrome reads the same forward and backward.
 * - Approach 1 (Iterative):
 *   1. Use fast/slow pointers to find the middle of the list.
 *   2. Reverse the second half of the list.
 *   3. Compare the first half and the reversed second half.
 *   4. Restore the list (optional).
 * - Approach 2 (Recursive):
 *   Use recursion to traverse to the end and compare symmetric nodes while unwinding.
 *
 * Time & Space Complexity:
 * ------------------------
 * - Iterative approach: O(n) time, O(1) space
 * - Recursive approach: O(n) time, O(n) space (recursion stack)
 */

public class PalindromeLinkedList {

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
     * Check palindrome using iterative approach.
     * Time: O(n), Space: O(1)
     */
    public boolean isPalindromeIterative() {
        if (head == null || head.next == null) return true;

        // Step 1: Find middle (slow will point to middle)
        Node slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node secondHalf = reverse(slow);

        // Step 3: Compare halves
        Node firstHalf = head;
        Node tempSecond = secondHalf;
        boolean palindrome = true;
        while (tempSecond != null) {
            if (firstHalf.data != tempSecond.data) {
                palindrome = false;
                break;
            }
            firstHalf = firstHalf.next;
            tempSecond = tempSecond.next;
        }

        // Step 4: Restore list (optional)
        reverse(secondHalf);

        return palindrome;
    }

    // Helper: Reverse linked list
    private Node reverse(Node node) {
        Node prev = null, cur = node, next;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    /**
     * Check palindrome using recursion.
     * Time: O(n), Space: O(n) recursion stack
     */
    private Node leftPointer;

    public boolean isPalindromeRecursive() {
        leftPointer = head;
        return checkRec(head);
    }

    private boolean checkRec(Node right) {
        if (right == null) return true;
        boolean res = checkRec(right.next);
        if (!res) return false;
        boolean match = (leftPointer.data == right.data);
        leftPointer = leftPointer.next;
        return match;
    }

    // Demo
    public static void main(String[] args) {
        PalindromeLinkedList list = new PalindromeLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(2);
        list.add(1);

        System.out.print("List: ");
        list.printList(); // 1 -> 2 -> 3 -> 2 -> 1

        System.out.println("Palindrome (Iterative)? " + list.isPalindromeIterative()); // true
        System.out.println("Palindrome (Recursive)? " + list.isPalindromeRecursive()); // true

        PalindromeLinkedList list2 = new PalindromeLinkedList();
        list2.add(1);
        list2.add(2);
        list2.add(3);

        System.out.print("List2: ");
        list2.printList(); // 1 -> 2 -> 3

        System.out.println("Palindrome (Iterative)? " + list2.isPalindromeIterative()); // false
        System.out.println("Palindrome (Recursive)? " + list2.isPalindromeRecursive()); // false
    }
}
