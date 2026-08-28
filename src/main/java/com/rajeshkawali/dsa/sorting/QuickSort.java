package com.rajeshkawali.dsa.sorting;

public class QuickSort {

    /*
     * Quick Sort Variants:
     * --------------------
     * 1. Lomuto Partition (simple, but more swaps)
     * 2. Hoare Partition (efficient, fewer swaps)
     * 3. Randomized Quick Sort (avoids worst-case)
     * 4. Tail Call Optimization (reduces recursion depth)
     */

    // 1. Lomuto Partition
    // Time: O(n) for partition
    // Space: O(1) extra
    public static int lomutoPartition(int[] arr, int low, int high) {
        int pivot = arr[high]; // last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    // Quick Sort recursive
    // Time: O(n log n) average, O(n^2) worst
    // Space: O(log n) recursion stack
    public static void quickSortLomuto(int[] arr, int low, int high) {
        if (low < high) {
            int pi = lomutoPartition(arr, low, high);
            quickSortLomuto(arr, low, pi - 1);  // sort left
            quickSortLomuto(arr, pi + 1, high); // sort right
        }
    }

    // 2. Hoare Partition
    // Time: O(n log n) avg, O(n^2) worst
    // Space: O(log n)
    public static int hoarePartition(int[] arr, int low, int high) {
        int pivot = arr[low]; // first element as pivot
        int i = low - 1, j = high + 1;
        while (true) {
            do { i++; } while (arr[i] < pivot);
            do { j--; } while (arr[j] > pivot);
            if (i >= j) return j;
            swap(arr, i, j);
        }
    }

    public static void quickSortHoare(int[] arr, int low, int high) {
        if (low < high) {
            int pi = hoarePartition(arr, low, high);
            quickSortHoare(arr, low, pi);
            quickSortHoare(arr, pi + 1, high);
        }
    }

    // 3. Randomized Quick Sort
    // Random pivot reduces chance of worst-case
    public static int randomizedPartition(int[] arr, int low, int high) {
        int randomPivot = low + (int)(Math.random() * (high - low + 1));
        swap(arr, randomPivot, high);
        return lomutoPartition(arr, low, high);
    }

    public static void quickSortRandomized(int[] arr, int low, int high) {
        if (low < high) {
            int pi = randomizedPartition(arr, low, high);
            quickSortRandomized(arr, low, pi - 1);
            quickSortRandomized(arr, pi + 1, high);
        }
    }

    // 4. Tail Call Optimization (sort smaller side first)
    // Helps reduce recursion depth
    public static void quickSortTailOptimized(int[] arr, int low, int high) {
        while (low < high) {
            int pi = lomutoPartition(arr, low, high);
            if (pi - low < high - pi) {
                quickSortTailOptimized(arr, low, pi - 1);
                low = pi + 1; // tail recursion eliminated
            } else {
                quickSortTailOptimized(arr, pi + 1, high);
                high = pi - 1;
            }
        }
    }

    // Utility swap
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    // Driver
    public static void main(String[] args) {
        int[] arr1 = {10, 7, 8, 9, 1, 5};
        System.out.println("=================================================================");
        quickSortLomuto(arr1, 0, arr1.length - 1);
        System.out.println("Lomuto QuickSort: " + java.util.Arrays.toString(arr1));
        System.out.println("=================================================================");
        int[] arr2 = {10, 7, 8, 9, 1, 5};
        quickSortHoare(arr2, 0, arr2.length - 1);
        System.out.println("Hoare QuickSort: " + java.util.Arrays.toString(arr2));
        System.out.println("=================================================================");
        int[] arr3 = {10, 7, 8, 9, 1, 5};
        quickSortRandomized(arr3, 0, arr3.length - 1);
        System.out.println("Randomized QuickSort: " + java.util.Arrays.toString(arr3));
        System.out.println("=================================================================");
        int[] arr4 = {10, 7, 8, 9, 1, 5};
        quickSortTailOptimized(arr4, 0, arr4.length - 1);
        System.out.println("Tail Optimized QuickSort: " + java.util.Arrays.toString(arr4));
        System.out.println("=================================================================");
    }
}

/*
Notes:
Lomuto → simpler, but more swaps.
Hoare → fewer swaps, often faster.
Randomized → avoids worst-case by shuffling pivot.
Tail Optimized → reduces recursion depth, prevents stack overflow.


Notes:
Quick Sort (Lomuto Partition):
Pick a pivot (commonly the last element).

Partition the array so that:
Elements ≤ pivot go to the left.
Elements > pivot go to the right.
Recursively sort left and right partitions
===============================================
Pivot choice matters: last element is simple, but random pivot avoids worst-case.
Average case: O(n log n) → very efficient.
Worst case: O(n²) → happens if pivot is always smallest/largest (like sorted input).
Space: O(log n) recursion stack (in-place sorting, no extra arrays).
*/