package com.rajeshkawali.dsa.heap;

import java.util.*;

/**
 * Heap implementation with both Max-Heap and Min-Heap operations.
 *
 * Core Operations:
 * - insert
 * - delete root
 * - heapify
 *
 * Applications:
 * - Heap Sort
 * - Top-K problems
 * - Median finding
 *
 * Time Complexity:
 * - Insert/Delete: O(log n)
 * - Heapify: O(log n)
 * - Heap Sort: O(n log n)
 */

public class HeapMinMax {

    // ---------------- Max-Heap Operations ----------------

    /** Heapify for Max-Heap */
    @SuppressWarnings("unused")
	private void maxHeapify(int[] arr, int n, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && arr[left] > arr[largest]) largest = left;
        if (right < n && arr[right] > arr[largest]) largest = right;

        if (largest != i) {
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            maxHeapify(arr, n, largest);
        }
    }

    /** Insert into Max-Heap */
    public void insertMaxHeap(List<Integer> heap, int val) {
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

    /** Delete root from Max-Heap */
    public int deleteMaxHeap(List<Integer> heap) {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            maxHeapifyList(heap, 0);
        }
        return root;
    }

    private void maxHeapifyList(List<Integer> heap, int i) {
        int n = heap.size();
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && heap.get(left) > heap.get(largest)) largest = left;
        if (right < n && heap.get(right) > heap.get(largest)) largest = right;

        if (largest != i) {
            Collections.swap(heap, i, largest);
            maxHeapifyList(heap, largest);
        }
    }

    // ---------------- Min-Heap Operations ----------------

    /** Heapify for Min-Heap */
    @SuppressWarnings("unused")
	private void minHeapify(int[] arr, int n, int i) {
        int smallest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && arr[left] < arr[smallest]) smallest = left;
        if (right < n && arr[right] < arr[smallest]) smallest = right;

        if (smallest != i) {
            int temp = arr[i];
            arr[i] = arr[smallest];
            arr[smallest] = temp;
            minHeapify(arr, n, smallest);
        }
    }

    /** Insert into Min-Heap */
    public void insertMinHeap(List<Integer> heap, int val) {
        heap.add(val);
        int i = heap.size() - 1;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap.get(i) < heap.get(parent)) {
                Collections.swap(heap, i, parent);
                i = parent;
            } else break;
        }
    }

    /** Delete root from Min-Heap */
    public int deleteMinHeap(List<Integer> heap) {
        if (heap.isEmpty()) throw new NoSuchElementException("Heap is empty");
        int root = heap.get(0);
        int last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            minHeapifyList(heap, 0);
        }
        return root;
    }

    private void minHeapifyList(List<Integer> heap, int i) {
        int n = heap.size();
        int smallest = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < n && heap.get(left) < heap.get(smallest)) smallest = left;
        if (right < n && heap.get(right) < heap.get(smallest)) smallest = right;

        if (smallest != i) {
            Collections.swap(heap, i, smallest);
            minHeapifyList(heap, smallest);
        }
    }

    // ---------------- Demo ----------------

    public static void main(String[] args) {
        HeapMinMax HeapMinMax = new HeapMinMax();

        // Max-Heap demo
        List<Integer> maxHeap = new ArrayList<>();
        HeapMinMax.insertMaxHeap(maxHeap, 10);
        HeapMinMax.insertMaxHeap(maxHeap, 20);
        HeapMinMax.insertMaxHeap(maxHeap, 5);
        System.out.println("Max-Heap after inserts: " + maxHeap); // [20, 10, 5]
        System.out.println("Deleted root (Max-Heap): " + HeapMinMax.deleteMaxHeap(maxHeap)); // 20
        System.out.println("Max-Heap after delete: " + maxHeap); // [10, 5]

        // Min-Heap demo
        List<Integer> minHeap = new ArrayList<>();
        HeapMinMax.insertMinHeap(minHeap, 10);
        HeapMinMax.insertMinHeap(minHeap, 20);
        HeapMinMax.insertMinHeap(minHeap, 5);
        System.out.println("Min-Heap after inserts: " + minHeap); // [5, 20, 10]
        System.out.println("Deleted root (Min-Heap): " + HeapMinMax.deleteMinHeap(minHeap)); // 5
        System.out.println("Min-Heap after delete: " + minHeap); // [10, 20]
    }
}
