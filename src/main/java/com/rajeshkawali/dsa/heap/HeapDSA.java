package com.rajeshkawali.dsa.heap;

import java.util.*;

/**
 * Heap (Min-Heap / Max-Heap) implementation with core operations and applications.
 *
 * Logic Explanation:
 * ------------------
 * - Heap is a complete binary tree stored as an array.
 * - Max-Heap: parent >= children
 * - Min-Heap: parent <= children
 * - Index relations:
 *   left = 2*i + 1, right = 2*i + 2, parent = (i-1)/2
 *
 * Core Operations:
 * - insert: add element at end, bubble up
 * - delete: remove root, replace with last element, bubble down
 * - heapify: maintain heap property
 *
 * Applications:
 * - Heap Sort: build heap, repeatedly extract max/min
 * - Top-K: use min-heap of size k
 * - Median Finding: use two heaps (max-heap for left half, min-heap for right half)
 *
 * Time Complexity:
 * - Insert/Delete: O(log n)
 * - Heapify: O(log n)
 * - Heap Sort: O(n log n)
 * - Top-K: O(n log k)
 * - Median Finding: O(log n) per insertion
 */

public class HeapDSA {

    // ---------------- Core Heap Operations ----------------

    /** Heapify (for max-heap) */
    private void heapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            heapify(arr, n, largest);
        }
    }

    /** Insert into max-heap */
    public void insert(List<Integer> heap, int val) {
        heap.add(val);
        int i = heap.size() - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) > heap.get(parent)) {
                Collections.swap(heap, i, parent);
                i = parent;
            } else break;
        }
    }

    /** Delete root from max-heap */
    public int delete(List<Integer> heap) {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            heapifyList(heap, 0);
        }
        return root;
    }

    /** Heapify helper for List */
    private void heapifyList(List<Integer> heap, int i) {
        int n = heap.size();
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && heap.get(left) > heap.get(largest)) largest = left;
        if (right < n && heap.get(right) > heap.get(largest)) largest = right;

        if (largest != i) {
            Collections.swap(heap, i, largest);
            heapifyList(heap, largest);
        }
    }

    // ---------------- Applications ----------------

    /** Heap Sort (using max-heap) */
    public void heapSort(int[] arr) {
        int n = arr.length;
        // Build heap
        for (int i = n/2 - 1; i >= 0; i--) heapify(arr, n, i);
        // Extract elements
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    /** Top-K largest elements using min-heap */
    public List<Integer> topK(int[] arr, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int num : arr) {
            minHeap.add(num);
            if (minHeap.size() > k) minHeap.poll();
        }
        return new ArrayList<>(minHeap);
    }

    /** Median finding using two heaps */
    static class MedianFinder {
        PriorityQueue<Integer> maxHeap; // left half
        PriorityQueue<Integer> minHeap; // right half

        public MedianFinder() {
            maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() == minHeap.size())
                return (maxHeap.peek() + minHeap.peek()) / 2.0;
            else
                return maxHeap.peek();
        }
    }

    // ---------------- Demo ----------------

    public static void main(String[] args) {
        HeapDSA heapDSA = new HeapDSA();

        // Heap Sort
        int[] arr = {4, 10, 3, 5, 1};
        heapDSA.heapSort(arr);
        System.out.println("Heap Sort: " + Arrays.toString(arr)); // [1, 3, 4, 5, 10]

        // Top-K
        int[] nums = {7, 10, 4, 3, 20, 15};
        System.out.println("Top 3 elements: " + heapDSA.topK(nums, 3)); // [10, 15, 20]

        // Median Finder
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median: " + mf.findMedian()); // 1.5
        mf.addNum(3);
        System.out.println("Median: " + mf.findMedian()); // 2.0

        // Heap Insert/Delete
        List<Integer> heap = new ArrayList<>();
        heapDSA.insert(heap, 10);
        heapDSA.insert(heap, 20);
        heapDSA.insert(heap, 5);
        System.out.println("Heap after inserts: " + heap); // [20, 10, 5]
        System.out.println("Deleted root: " + heapDSA.delete(heap)); // 20
        System.out.println("Heap after delete: " + heap); // [10, 5]
    }
}
