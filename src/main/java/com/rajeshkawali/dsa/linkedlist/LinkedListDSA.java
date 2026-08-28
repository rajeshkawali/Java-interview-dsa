package com.rajeshkawali.dsa.linkedlist;
import java.util.*;

/**
 * LinkedListDSA
 *
 * Single class containing:
 * - ListNode definition
 * - Basic operations (insert, delete, traverse)
 * - Classic problems and patterns:
 *   reverse list, detect cycle, find middle, merge sorted lists,
 *   remove Nth from end, palindrome check, add two numbers,
 *   partition, reorder list, reverse in k-groups, merge k lists,
 *   copy list with random pointer (separate Node class), flatten multilevel list,
 *   and utility helpers.
 *
 * Complexity notes are provided above or inside each method.
 */
public class LinkedListDSA {

    /* ---------------------------
     * Node definitions
     * ---------------------------
     */

    // Basic singly linked list node
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int v) { val = v; }
    }

    // Node with random pointer (for copyRandomList problem)
    public static class RandomNode {
        public int val;
        public RandomNode next;
        public RandomNode random;
        public RandomNode(int v) { val = v; }
    }

    /* ---------------------------
     * Basic operations
     * ---------------------------
     */

    // Insert at head
    // Time O(1) Space O(1)
    public static ListNode insertHead(ListNode head, int val) {
        ListNode node = new ListNode(val);
        node.next = head;
        return node;
    }

    // Insert at tail (returns new head)
    // Time O(n) unless tail reference maintained; Space O(1)
    public static ListNode insertTail(ListNode head, int val) {
        ListNode node = new ListNode(val);
        if (head == null) return node;
        ListNode cur = head;
        while (cur.next != null) cur = cur.next;
        cur.next = node;
        return head;
    }

    // Delete first occurrence of value
    // Time O(n) Space O(1)
    public static ListNode deleteByValue(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                prev.next = cur.next;
                break;
            }
            prev = cur;
            cur = cur.next;
        }
        return dummy.next;
    }

    // Print list values
    // Time O(n) Space O(1)
    public static void printList(ListNode head) {
        ListNode cur = head;
        StringJoiner sj = new StringJoiner(" -> ");
        while (cur != null) {
            sj.add(String.valueOf(cur.val));
            cur = cur.next;
        }
        System.out.println(sj.toString());
    }

    /* ---------------------------
     * Classic algorithms
     * ---------------------------
     */

    // Reverse linked list iteratively
    // Time O(n) Space O(1)
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = prev;
            prev = cur;
            cur = nxt;
        }
        return prev;
    }

    // Detect cycle using Floyd's algorithm
    // Time O(n) Space O(1)
    public static boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Find cycle start node if cycle exists
    // Time O(n) Space O(1)
    public static ListNode detectCycleStart(ListNode head) {
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

    // Find middle node (for even length returns second middle)
    // Time O(n) Space O(1)
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Merge two sorted lists (iterative)
    // Time O(n + m) Space O(1)
    public static ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1; l1 = l1.next;
            } else {
                tail.next = l2; l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // Remove Nth node from end (one-pass two-pointer)
    // Time O(n) Space O(1)
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy, second = dummy;
        // advance first by n+1
        for (int i = 0; i <= n; i++) {
            if (first == null) return head; // n larger than length
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // second.next is node to remove
        second.next = second.next.next;
        return dummy.next;
    }

    // Check if list is palindrome (reverse second half)
    // Time O(n) Space O(1)
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // find middle
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; fast = fast.next.next;
        }
        // reverse second half
        ListNode second = reverseList(slow);
        ListNode first = head;
        boolean ok = true;
        while (second != null) {
            if (first.val != second.val) { ok = false; break; }
            first = first.next;
            second = second.next;
        }
        // optional: restore second half (not done here)
        return ok;
    }

    // Add two numbers represented by linked lists (digits stored in reverse)
    // Time O(max(n,m)) Space O(max(n,m))
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0), tail = dummy;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int sum = carry;
            if (l1 != null) { sum += l1.val; l1 = l1.next; }
            if (l2 != null) { sum += l2.val; l2 = l2.next; }
            tail.next = new ListNode(sum % 10);
            carry = sum / 10;
            tail = tail.next;
        }
        return dummy.next;
    }

    // Partition list around x (all < x before >= x), stable relative order
    // Time O(n) Space O(1)
    public static ListNode partition(ListNode head, int x) {
        ListNode lessDummy = new ListNode(0), greaterDummy = new ListNode(0);
        ListNode less = lessDummy, greater = greaterDummy;
        while (head != null) {
            if (head.val < x) { less.next = head; less = less.next; }
            else { greater.next = head; greater = greater.next; }
            head = head.next;
        }
        greater.next = null;
        less.next = greaterDummy.next;
        return lessDummy.next;
    }

    // Reorder list L0→Ln→L1→Ln-1...
    // Time O(n) Space O(1)
    public static void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        // find middle
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next; fast = fast.next.next;
        }
        // reverse second half
        ListNode second = reverseList(slow.next);
        slow.next = null;
        // merge alternately
        ListNode first = head;
        while (second != null) {
            ListNode t1 = first.next, t2 = second.next;
            first.next = second;
            second.next = t1;
            first = t1;
            second = t2;
        }
    }

    // Reverse nodes in k-group
    // Time O(n) Space O(1)
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy;
        while (true) {
            ListNode kth = prevGroup;
            for (int i = 0; i < k && kth != null; i++) kth = kth.next;
            if (kth == null) break;
            ListNode groupStart = prevGroup.next;
            ListNode cur = groupStart.next;
            // reverse group in-place
            for (int i = 1; i < k; i++) {
                groupStart.next = cur.next;
                cur.next = prevGroup.next;
                prevGroup.next = cur;
                cur = groupStart.next;
            }
            prevGroup = groupStart;
        }
        return dummy.next;
    }

    // Merge k sorted lists using priority queue
    // Time O(N log k) where N total nodes, Space O(k)
    public static ListNode mergeKLists(List<ListNode> lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
        for (ListNode node : lists) if (node != null) pq.offer(node);
        ListNode dummy = new ListNode(0), tail = dummy;
        while (!pq.isEmpty()) {
            ListNode node = pq.poll();
            tail.next = node;
            tail = tail.next;
            if (node.next != null) pq.offer(node.next);
        }
        return dummy.next;
    }

    // Copy list with random pointer (O(n) time O(1) extra space using interleaving)
    // Time O(n) Space O(1) extra (excluding output)
    public static RandomNode copyRandomList(RandomNode head) {
        if (head == null) return null;
        // Step 1: interleave copied nodes
        RandomNode cur = head;
        while (cur != null) {
            RandomNode copy = new RandomNode(cur.val);
            copy.next = cur.next;
            cur.next = copy;
            cur = copy.next;
        }
        // Step 2: set random pointers
        cur = head;
        while (cur != null) {
            if (cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // Step 3: detach lists
        RandomNode dummy = new RandomNode(0);
        RandomNode tail = dummy;
        cur = head;
        while (cur != null) {
            RandomNode copy = cur.next;
            tail.next = copy;
            tail = tail.next;
            cur.next = copy.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    // Flatten multilevel linked list where each node may have child pointer
    // (Assumes Node {int val; Node next; Node child;})
    // Time O(n) Space O(n) stack worst-case
    public static class MultiNode {
        public int val;
        public MultiNode next;
        public MultiNode child;
        public MultiNode(int v) { val = v; }
    }

    // Flatten using stack (iterative DFS)
    public static MultiNode flattenMultilevel(MultiNode head) {
        if (head == null) return null;
        MultiNode dummy = new MultiNode(0);
        MultiNode prev = dummy;
        Deque<MultiNode> stack = new ArrayDeque<>();
        stack.push(head);
        while (!stack.isEmpty()) {
            MultiNode node = stack.pop();
            prev.next = node;
            prev = node;
            if (node.next != null) stack.push(node.next);
            if (node.child != null) {
                stack.push(node.child);
                node.child = null;
            }
        }
        dummy.next = null; // optional: detach dummy
        return dummy.next;
    }

    /* ---------------------------
     * Utility helpers
     * ---------------------------
     */

    // Build list from array
    public static ListNode buildList(int[] arr) {
        ListNode head = null, tail = null;
        for (int v : arr) {
            ListNode node = new ListNode(v);
            if (head == null) { head = tail = node; }
            else { tail.next = node; tail = node; }
        }
        return head;
    }

    // Convert list to array (for testing)
    public static int[] toArray(ListNode head) {
        List<Integer> vals = new ArrayList<>();
        while (head != null) { vals.add(head.val); head = head.next; }
        return vals.stream().mapToInt(i -> i).toArray();
    }

    /* ---------------------------
     * Demonstration main
     * ---------------------------
     */
    public static void main(String[] args) {
        // Build list 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = buildList(new int[]{1,2,3,4,5});
        System.out.println("=================================================================");
        System.out.print("Original: ");
        printList(head);
        System.out.println("=================================================================");
        // Reverse
        ListNode rev = reverseList(head);
        System.out.print("Reversed: ");
        printList(rev);
        System.out.println("=================================================================");
        // Find middle
        ListNode mid = findMiddle(rev);
        System.out.println("Middle value: " + (mid != null ? mid.val : "null"));
        System.out.println("=================================================================");
        // Partition example
        ListNode part = partition(buildList(new int[]{1,4,3,2,5,2}), 3);
        System.out.print("Partitioned around 3: ");
        printList(part);
        System.out.println("=================================================================");
        // Remove nth from end
        ListNode removed = removeNthFromEnd(buildList(new int[]{1,2,3,4,5}), 2);
        System.out.print("After removing 2nd from end: ");
        printList(removed);
        System.out.println("=================================================================");
        // Reorder list
        ListNode r = buildList(new int[]{1,2,3,4});
        reorderList(r);
        System.out.print("Reordered: ");
        printList(r);
        System.out.println("=================================================================");
        // Reverse k-group
        ListNode kgrp = reverseKGroup(buildList(new int[]{1,2,3,4,5}), 2);
        System.out.print("Reverse in k=2 groups: ");
        printList(kgrp);
        System.out.println("=================================================================");
        // Merge two sorted lists
        ListNode m = mergeTwoSortedLists(buildList(new int[]{1,3,5}), buildList(new int[]{2,4,6}));
        System.out.print("Merged sorted lists: ");
        printList(m);
        System.out.println("=================================================================");
        // Palindrome check
        System.out.println("Is palindrome [1,2,2,1]? " + isPalindrome(buildList(new int[]{1,2,2,1})));
        System.out.println("=================================================================");
        
        System.out.println("=================================================================");
    }
}


/**
 * Linked List Methods Summary
 *
 * Format:  Method | Purpose | Time | Space (aux)
 *
 * <pre>
 * insertHead            | Insert at head                         | O(1)         | O(1)
 * insertTail            | Insert at tail                         | O(n)         | O(1)
 * deleteByValue         | Delete first occurrence                | O(n)         | O(1)
 * reverseList           | Reverse list iteratively               | O(n)         | O(1)
 * hasCycle              | Detect cycle (Floyd)                   | O(n)         | O(1)
 * detectCycleStart      | Find cycle start                       | O(n)         | O(1)
 * findMiddle            | Middle node                            | O(n)         | O(1)
 * mergeTwoSortedLists   | Merge two sorted lists                 | O(n + m)     | O(1)
 * removeNthFromEnd      | Remove nth from end                    | O(n)         | O(1)
 * isPalindrome          | Check palindrome in-place              | O(n)         | O(1)
 * addTwoNumbers         | Add two number lists                   | O(max(n,m))  | O(max(n,m))
 * partition             | Partition around x                     | O(n)         | O(1)
 * reorderList           | Reorder L0→Ln→L1...                    | O(n)         | O(1)
 * reverseKGroup         | Reverse in groups of k                 | O(n)         | O(1)
 * mergeKLists           | Merge k sorted lists                   | O(N log k)   | O(k)
 * copyRandomList        | Copy list with random pointer          | O(n)         | O(1) extra
 * flattenMultilevel     | Flatten multilevel list                | O(n)         | O(n) stack
 * </pre>
 *
 * Notes:
 * - n, m denote lengths of involved lists; k denotes group size; N denotes total nodes across k lists.
 * - "Space (aux)" excludes output storage; it refers to additional auxiliary space used by the algorithm.
 */