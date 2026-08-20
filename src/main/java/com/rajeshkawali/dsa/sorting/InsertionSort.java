package com.rajeshkawali.dsa.sorting;

public class InsertionSort {

    /*
     * Insertion Sort Algorithm:
     * -------------------------
     * Problem:
     *   Sort an array using Insertion Sort.
     *
     * Logic:
     * 1. Divide array into sorted and unsorted parts.
     * 2. Pick the first element from unsorted part.
     * 3. Insert it into the correct position in the sorted part.
     * 4. Repeat until the array is sorted.
     *
     * Time Complexity:
     * - Worst case: O(n^2) (array in reverse order)
     * - Best case: O(n) (already sorted)
     * - Average case: O(n^2)
     *
     * Space Complexity: O(1) → in-place sorting
     *
     * Key Point:
     * - Stable sort (preserves order of equal elements).
     * - Efficient for small arrays or nearly sorted arrays.
     */

    // Utility method to print array
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /*
     * 1. Standard Iterative Insertion Sort
     * - Traverse from left to right.
     * - For each element, shift larger elements to the right.
     * - Insert current element at correct position.
     */
    public static void insertionSortStandard(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements greater than key to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Place key at correct position
            arr[j + 1] = key;
        }
    }

    /*
     * 2. Optimized Insertion Sort (Binary Search for position)
     * - Use binary search to find correct position of key.
     * - Then shift elements and insert key.
     * - Reduces comparisons, but shifting still O(n).
     */
    public static void insertionSortBinarySearch(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int left = 0, right = i - 1;

            // Binary search for insertion position
            while (left <= right) {
                int mid = (left + right) / 2;
                if (arr[mid] > key) right = mid - 1;
                else left = mid + 1;
            }

            // Shift elements to make space
            for (int j = i - 1; j >= left; j--) {
                arr[j + 1] = arr[j];
            }
            arr[left] = key;
        }
    }

    /*
     * 3. Recursive Insertion Sort
     * - Base case: if n <= 1 → already sorted.
     * - Recursively sort first n-1 elements.
     * - Insert nth element into sorted array.
     */
    public static void insertionSortRecursive(int[] arr, int n) {
        if (n <= 1) return;

        // Sort first n-1 elements
        insertionSortRecursive(arr, n - 1);

        // Insert nth element at correct position
        int key = arr[n - 1];
        int j = n - 2;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 25, 12, 22, 11};
        int[] arr2 = {64, 25, 12, 22, 11};
        int[] arr3 = {64, 25, 12, 22, 11};
        System.out.println("================================================");
        System.out.println("Original Array:");
        printArray(arr1);
        System.out.println("================================================");
        insertionSortStandard(arr1);
        System.out.println("Sorted Array (Standard):");
        printArray(arr1);
        System.out.println("================================================");
        insertionSortBinarySearch(arr2);
        System.out.println("Sorted Array (Binary Search Optimized):");
        printArray(arr2);
        System.out.println("================================================");
        insertionSortRecursive(arr3, arr3.length);
        System.out.println("Sorted Array (Recursive):");
        printArray(arr3);
        System.out.println("================================================");
    }
}
/*
 * Insertion Sort:
 * - Builds sorted array one element at a time.
 * - Good for small or nearly sorted arrays.
 * - Stable sort (preserves order of equal elements).
 *
 * Variations:
 * 1. Standard Iterative → simple, O(n^2).
 * 2. Binary Search Optimized → fewer comparisons, still O(n^2).
 * 3. Recursive → elegant, same complexity.
 *
 * Time: O(n^2) worst/average, O(n) best
 * Space: O(1)
 */


/*
Cheat Note: Bubble vs Selection vs Insertion Sort
-------------------------------------------------

Bubble Sort:
- Logic: Repeatedly swap adjacent elements if out of order.
- Key Idea: Largest element "bubbles" to the end in each pass.
- Time Complexity: Best O(n), Avg O(n^2), Worst O(n^2)
- Space Complexity: O(1)
- Stability: Stable
- Memory Trick: Think soap bubbles rising → largest goes to top (end).

Selection Sort:
- Logic: Repeatedly select the minimum element from unsorted part and place it at start.
- Key Idea: Select smallest card each time.
- Time Complexity: Best O(n^2), Avg O(n^2), Worst O(n^2)
- Space Complexity: O(1)
- Stability: Not stable
- Memory Trick: Imagine picking the smallest card from a deck each round.

Insertion Sort:
- Logic: Build sorted array one element at a time by inserting into correct position.
- Key Idea: Insert like arranging playing cards in hand.
- Time Complexity: Best O(n), Avg O(n^2), Worst O(n^2)
- Space Complexity: O(1)
- Stability: Stable
- Memory Trick: Think of inserting each card into the right place in your hand.

Quick Summary:
- Bubble → swap neighbors, largest goes to end.
- Selection → pick min, put at front.
- Insertion → insert element into correct position in sorted part.
*/