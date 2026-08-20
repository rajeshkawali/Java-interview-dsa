package com.rajeshkawali.dsa.sorting;

public class SelectionSort {

    /*
     * Selection Sort Algorithm:
     * -------------------------
     * Problem:
     *  Sort an array using Selection Sort.
     *  	- Simple comparison-based sorting algorithm.
     * 	- Works by repeatedly selecting the minimum element
     *   from the unsorted part and placing it at the beginning.
     *
     * Logic:
     * 1. Divide array into sorted and unsorted parts.
     * 2. Repeatedly find the minimum element from unsorted part.
     * 3. Swap it with the first element of unsorted part.
     * 4. Continue until entire array is sorted.
     *
     * Time Complexity:
     * - Worst case: O(n^2)
     * - Best case: O(n^2) (still scans all elements)
     * - Average case: O(n^2)
     *
     * Space Complexity: O(1) → in-place sorting
     *
     * Key Point:
     * - Fewer swaps than Bubble Sort.
     * - Not stable (relative order of equal elements may change).
     */

    // Utility method to print array
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /*
     * 1. Standard Selection Sort
     * - Find minimum element in unsorted part.
     * - Swap with first element of unsorted part.
     */
    public static void selectionSortStandard(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap minimum with first element of unsorted part
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    /*
     * 2. Optimized Selection Sort
     * - Find both minimum and maximum in each pass.
     * - Place minimum at start and maximum at end.
     * - Reduces number of passes by half.
     */
    public static void selectionSortOptimized(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int minIndex = left;
            int maxIndex = left;
            for (int i = left; i <= right; i++) {
                if (arr[i] < arr[minIndex]) minIndex = i;
                if (arr[i] > arr[maxIndex]) maxIndex = i;
            }
            // Swap min with left
            int temp = arr[left];
            arr[left] = arr[minIndex];
            arr[minIndex] = temp;

            // If maxIndex was at left, update it
            if (maxIndex == left) maxIndex = minIndex;

            // Swap max with right
            temp = arr[right];
            arr[right] = arr[maxIndex];
            arr[maxIndex] = temp;

            left++;
            right--;
        }
    }

    /*
     * 3. Recursive Selection Sort
     * - Base case: if start index == n-1 → array sorted.
     * - Find minimum in remaining array.
     * - Swap with current index.
     * - Recurse for next index.
     */
    public static void selectionSortRecursive(int[] arr, int start) {
        int n = arr.length;
        if (start >= n - 1) return;

        int minIndex = start;
        for (int i = start + 1; i < n; i++) {
            if (arr[i] < arr[minIndex]) {
                minIndex = i;
            }
        }

        // Swap minimum with current index
        int temp = arr[start];
        arr[start] = arr[minIndex];
        arr[minIndex] = temp;

        // Recursive call for next index
        selectionSortRecursive(arr, start + 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 25, 12, 22, 11};
        int[] arr2 = {64, 25, 12, 22, 11};
        int[] arr3 = {64, 25, 12, 22, 11};
        System.out.println("================================================");
        System.out.println("Original Array:");
        printArray(arr1);
        System.out.println("================================================");
        selectionSortStandard(arr1);
        System.out.println("Sorted Array (Standard):");
        printArray(arr1);
        System.out.println("================================================");
        selectionSortOptimized(arr2);
        System.out.println("Sorted Array (Optimized):");
        printArray(arr2);
        System.out.println("================================================");
        selectionSortRecursive(arr3, 0);
        System.out.println("Sorted Array (Recursive):");
        printArray(arr3);
        System.out.println("================================================");
    }
}

/*
Short Description:
Standard Selection Sort → repeatedly selects the minimum element and places it at the correct position.
Optimized Selection Sort → selects both minimum and maximum in each pass, reducing passes.
Recursive Selection Sort → uses recursion to select minimum and sort progressively.
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