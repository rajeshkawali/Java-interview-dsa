package com.rajeshkawali.dsa.sorting;

public class MergeSort {

    /*
     * Merge Sort Variants:
     * --------------------
     * 1. Top-Down Recursive Merge Sort (standard way)
     * 2. Bottom-Up Iterative Merge Sort
     * 3. In-Place Merge Sort (space optimized, harder to implement)
     *
     * Notes:
     * - Merge Sort is a Divide & Conquer algorithm.
     * - Always O(n log n) time in best/avg/worst case.
     * - Space: O(n) for temporary arrays (except in-place variant).
     * - Stable sort (preserves order of equal elements).
     */

    // 1. Top-Down Recursive Merge Sort (standard)
    // Time: O(n log n), Space: O(n)
    public static void mergeSortRecursive(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSortRecursive(arr, left, mid);
            mergeSortRecursive(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    // Merge helper
    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1, n2 = right - mid;
        int[] L = new int[n1], R = new int[n2];
        for (int i = 0; i < n1; i++) L[i] = arr[left + i];
        for (int j = 0; j < n2; j++) R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) arr[k++] = L[i++];
            else arr[k++] = R[j++];
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    // 2. Bottom-Up Iterative Merge Sort
    // Time: O(n log n), Space: O(n)
    public static void mergeSortIterative(int[] arr) {
        int n = arr.length;
        for (int currSize = 1; currSize < n; currSize *= 2) {
            for (int left = 0; left < n - 1; left += 2 * currSize) {
                int mid = Math.min(left + currSize - 1, n - 1);
                int right = Math.min(left + 2 * currSize - 1, n - 1);
                merge(arr, left, mid, right);
            }
        }
    }

    // 3. In-Place Merge Sort (space optimized)
    // Time: O(n log^2 n), Space: O(1)
    // Logic: Merge without extra arrays by shifting elements.
    public static void inPlaceMergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            inPlaceMergeSort(arr, left, mid);
            inPlaceMergeSort(arr, mid + 1, right);
            inPlaceMerge(arr, left, mid, right);
        }
    }

    private static void inPlaceMerge(int[] arr, int left, int mid, int right) {
        int start2 = mid + 1;
        if (arr[mid] <= arr[start2]) return; // already sorted

        while (left <= mid && start2 <= right) {
            if (arr[left] <= arr[start2]) {
                left++;
            } else {
                int value = arr[start2];
                int index = start2;
                while (index != left) {
                    arr[index] = arr[index - 1];
                    index--;
                }
                arr[left] = value;
                left++; mid++; start2++;
            }
        }
    }

    // Driver
    public static void main(String[] args) {
        int[] arr1 = {12, 11, 13, 5, 6, 7};
        System.out.println("=================================================================");
        mergeSortRecursive(arr1, 0, arr1.length - 1);
        System.out.println("Recursive MergeSort: " + java.util.Arrays.toString(arr1));
        System.out.println("=================================================================");
        int[] arr2 = {12, 11, 13, 5, 6, 7};
        mergeSortIterative(arr2);
        System.out.println("Iterative MergeSort: " + java.util.Arrays.toString(arr2));
        System.out.println("=================================================================");
        int[] arr3 = {12, 11, 13, 5, 6, 7};
        inPlaceMergeSort(arr3, 0, arr3.length - 1);
        System.out.println("In-Place MergeSort: " + java.util.Arrays.toString(arr3));
        System.out.println("=================================================================");
    }
}

/*
Notes
Recursive Merge Sort → Standard, easy to implement, uses extra arrays.
Iterative Merge Sort → Bottom-up, avoids recursion, still uses extra arrays.
In-Place Merge Sort → Saves space, but slower (O(n log² n)) and harder to code.
 */
