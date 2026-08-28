package com.rajeshkawali.dsa.linkedlist;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * AdvancedLinkedListVariants
 *
 * Single Java class demonstrating several advanced linked-list techniques:
 *  1) Flatten Multilevel Linked List (iterative DFS using stack)
 *  2) LRU Cache using Doubly Linked List + HashMap (O(1) get/put)
 *  3) XOR Linked List (simulated approach for managed languages)
 *  4) Persistent (immutable) Linked List with structural sharing
 *  5) Concurrent Linked List (thread-safe wrapper using ConcurrentLinkedQueue)
 *
 * Each section contains:
 *  - inner node/type definitions
 *  - core methods
 *  - complexity notes (time / auxiliary space)
 *
 * Notes:
 *  - XOR linked list is not directly portable to Java because Java does not expose raw pointers.
 *    The implementation below simulates XOR behavior using integer indices in an ArrayList.
 *  - Concurrent linked list here demonstrates a practical thread-safe approach using
 *    java.util.concurrent collections rather than a low-level lock-free implementation.
 *
 * Use this class as a reference and learning resource. Each inner type is self-contained.
 */
public class AdvancedLinkedListVariants {

    /* ---------------------------------------------------------------------
     * 1) Flatten Multilevel Linked List
     * Idea: iterative DFS using a stack to splice child lists into the main list.
     *
     * Complexity:
     *  - Time: O(n) where n is total number of nodes across all levels.
     *  - Space (aux): O(n) worst-case stack (when every node has a child).
     * --------------------------------------------------------------------- */
    public static class MultiNode {
        public int val;
        public MultiNode next;
        public MultiNode child;
        public MultiNode(int v) { val = v; }
    }

    /**
     * Flatten a multilevel linked list into a single-level list in DFS order.
     * The method modifies the list in-place and returns the head of flattened list.
     */
    public static MultiNode flattenMultilevel(MultiNode head) {
        if (head == null) return null;
        Deque<MultiNode> stack = new ArrayDeque<>();
        stack.push(head);
        MultiNode dummy = new MultiNode(0);
        MultiNode prev = dummy;

        while (!stack.isEmpty()) {
            MultiNode node = stack.pop();
            // attach node
            prev.next = node;
            prev = node;

            // push next first so child is processed before next (DFS)
            if (node.next != null) stack.push(node.next);
            if (node.child != null) {
                stack.push(node.child);
                node.child = null; // detach child pointer after pushing
            }
        }
        // ensure tail.next is null
        prev.next = null;
        return dummy.next;
    }

    /* ---------------------------------------------------------------------
     * 2) LRU Cache (Doubly Linked List + HashMap)
     *
     * Idea:
     *  - Maintain a doubly linked list of cache entries in recency order (head = most recent).
     *  - HashMap maps keys to nodes for O(1) access.
     *
     * Complexity:
     *  - get/put: O(1) average time
     *  - Space (aux): O(capacity) for map + O(capacity) for nodes
     * --------------------------------------------------------------------- */
    public static class LRUCache {
        private static class Node {
            int key, val;
            Node prev, next;
            Node(int k, int v) { key = k; val = v; }
        }

        private final int capacity;
        private final Map<Integer, Node> map;
        private final Node head; // dummy head (most recent after head)
        private final Node tail; // dummy tail (least recent before tail)

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        // Move node to front (right after head)
        private void moveToFront(Node node) {
            removeNode(node);
            addAfterHead(node);
        }

        private void addAfterHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            moveToFront(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.val = value;
                moveToFront(node);
                return;
            }
            if (map.size() >= capacity) {
                // evict least recently used (node before tail)
                Node lru = tail.prev;
                removeNode(lru);
                map.remove(lru.key);
            }
            Node newNode = new Node(key, value);
            addAfterHead(newNode);
            map.put(key, newNode);
        }
    }

    /* ---------------------------------------------------------------------
     * 3) XOR Linked List (Simulated)
     *
     * Idea:
     *  - In languages with raw pointers, each node stores XOR(prevPtr, nextPtr).
     *  - In Java we cannot XOR pointers; instead we simulate using indices in a list.
     *
     * Notes:
     *  - This is a simulation for educational purposes only.
     *  - Real XOR linked lists require pointer arithmetic and are unsafe in managed runtimes.
     *
     * Complexity:
     *  - Access/traversal: O(n)
     *  - Space (aux): O(n) for storage of nodes and index mapping
     * --------------------------------------------------------------------- */
    public static class XorLinkedListSim {
        // Simulated node storing value and XOR of indices (prevIndex ^ nextIndex)
        private static class XNode {
            int val;
            int both; // XOR of prevIndex and nextIndex
            XNode(int v) { val = v; both = 0; }
        }

        // store nodes in an ArrayList; index 0 reserved as "null" sentinel
        private final List<XNode> nodes = new ArrayList<>();
        private int headIndex = 0; // index of head node (0 means null)
        private int tailIndex = 0; // index of tail node

        public XorLinkedListSim() {
            nodes.add(null); // index 0 sentinel
        }

        // helper to xor two indices
        private int xor(int a, int b) { return a ^ b; }

        // append value at tail
        public void add(int val) {
            XNode node = new XNode(val);
            nodes.add(node);
            int newIndex = nodes.size() - 1;
            if (headIndex == 0) {
                // empty list
                headIndex = tailIndex = newIndex;
                // both remains 0 (xor of 0 and 0)
            } else {
                // update old tail's both: oldBoth ^ 0 ^ newIndex => oldBoth ^ newIndex
                XNode tail = nodes.get(tailIndex);
                tail.both = xor(tail.both, newIndex);
                // new node both = tailIndex ^ 0
                node.both = xor(tailIndex, 0);
                tailIndex = newIndex;
            }
        }

        // traverse forward and return values
        public List<Integer> toListForward() {
            List<Integer> res = new ArrayList<>();
            int prev = 0;
            int curr = headIndex;
            while (curr != 0) {
                XNode node = nodes.get(curr);
                res.add(node.val);
                int next = xor(prev, node.both);
                prev = curr;
                curr = next;
            }
            return res;
        }

        // traverse backward and return values
        public List<Integer> toListBackward() {
            List<Integer> res = new ArrayList<>();
            int prev = 0;
            int curr = tailIndex;
            while (curr != 0) {
                XNode node = nodes.get(curr);
                res.add(node.val);
                int next = xor(prev, node.both);
                prev = curr;
                curr = next;
            }
            return res;
        }
    }

    /* ---------------------------------------------------------------------
     * 4) Persistent (Immutable) Linked List (Functional style)
     *
     * Idea:
     *  - Nodes are immutable. "Cons" creates a new head that shares tail with existing list.
     *  - Structural sharing makes many operations O(1) (e.g., cons), while others remain O(n).
     *
     * Complexity:
     *  - cons (prepend): O(1) time, O(1) space for new node
     *  - head/tail access: O(1)
     *  - append: O(n) (creates new nodes)
     * --------------------------------------------------------------------- */
    public static class PersistentList {
        public static final PersistentList EMPTY = new PersistentList(null, 0);

        private final Node head;
        private final int size;

        private static final class Node {
            final int val;
            final Node next;
            Node(int v, Node n) { val = v; next = n; }
        }

        private PersistentList(Node head, int size) {
            this.head = head;
            this.size = size;
        }

        // prepend (cons) returns a new list sharing the old tail
        public PersistentList cons(int val) {
            return new PersistentList(new Node(val, head), size + 1);
        }

        // get head value (throws if empty)
        public int head() {
            if (head == null) throw new NoSuchElementException("Empty list");
            return head.val;
        }

        // return tail list (without head)
        public PersistentList tail() {
            if (head == null) throw new NoSuchElementException("Empty list");
            return new PersistentList(head.next, size - 1);
        }

        public boolean isEmpty() { return head == null; }
        public int size() { return size; }

        // convert to Java List (O(n))
        public List<Integer> toList() {
            List<Integer> out = new ArrayList<>(size);
            Node cur = head;
            while (cur != null) {
                out.add(cur.val);
                cur = cur.next;
            }
            return out;
        }
    }

    /* ---------------------------------------------------------------------
     * 5) Concurrent Linked List (Thread-safe wrapper)
     *
     * Idea:
     *  - Use java.util.concurrent.ConcurrentLinkedQueue (non-blocking) for thread-safe FIFO operations.
     *  - Provide simple linked-list-like API (add, poll, peek, contains).
     *
     * Complexity:
     *  - add/poll/peek: O(1) amortized, thread-safe
     *  - contains: O(n)
     *  - Space: O(n) for stored elements
     *
     * Note:
     *  - Implementing a custom lock-free linked list (Harris-Michael) is advanced and error-prone.
     *    For production use prefer java.util.concurrent classes unless you need a custom algorithm.
     * --------------------------------------------------------------------- */
    public static class ConcurrentLinkedListWrapper<E> {
        private final ConcurrentLinkedQueue<E> queue = new ConcurrentLinkedQueue<>();

        // thread-safe add at tail
        public void add(E value) { queue.add(value); }

        // thread-safe poll from head (returns null if empty)
        public E poll() { return queue.poll(); }

        // thread-safe peek at head (returns null if empty)
        public E peek() { return queue.peek(); }

        // contains (O(n))
        public boolean contains(E value) { return queue.contains(value); }

        // convert to list snapshot
        public List<E> snapshot() { return new ArrayList<>(queue); }
    }

    public static void main(String[] args) {
    	System.out.println("=================================================================");
        System.out.println("=== Flatten Multilevel List Demo ===");
        MultiNode a = new MultiNode(1);
        MultiNode b = new MultiNode(2);
        MultiNode c = new MultiNode(3);
        MultiNode d = new MultiNode(4);
        a.next = b; b.next = c;
        b.child = d; // child of 2 is 4
        MultiNode flat = flattenMultilevel(a);
        MultiNode cur = flat;
        while (cur != null) { System.out.print(cur.val + " "); cur = cur.next; }
        System.out.println("Expected DFS order: 1 2 4 3");
        System.out.println("=================================================================");
        System.out.println("=== LRU Cache Demo ===");
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("get(1) -> " + lru.get(1)); // 1
        lru.put(3, 3); // evicts key 2
        System.out.println("get(2) -> " + lru.get(2)); // -1
        lru.put(4, 4); // evicts key 1
        System.out.println("get(1) -> " + lru.get(1)); // -1
        System.out.println("get(3) -> " + lru.get(3)); // 3
        System.out.println("get(4) -> " + lru.get(4)); // 4
        System.out.println("=================================================================");
        System.out.println("=== XOR Linked List (Simulated) Demo ===");
        XorLinkedListSim xorList = new XorLinkedListSim();
        xorList.add(10); xorList.add(20); xorList.add(30);
        System.out.println("Forward: " + xorList.toListForward());
        System.out.println("Backward: " + xorList.toListBackward());
        System.out.println("=================================================================");
        System.out.println("=== Persistent List Demo ===");
        PersistentList p = PersistentList.EMPTY;
        p = p.cons(3).cons(2).cons(1); // list is 1 -> 2 -> 3
        System.out.println("Persistent list size: " + p.size());
        System.out.println("Persistent list contents: " + p.toList());
        System.out.println("=================================================================");
        System.out.println("=== Concurrent Linked List Demo ===");
        ConcurrentLinkedListWrapper<Integer> concurrent = new ConcurrentLinkedListWrapper<>();
        concurrent.add(100); concurrent.add(200);
        System.out.println("Snapshot: " + concurrent.snapshot());
        System.out.println("Poll: " + concurrent.poll());
        System.out.println("Peek: " + concurrent.peek());
    }
}
