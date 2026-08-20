package com.rajeshkawali.dsa.sorting;

public class BubbleSort {

    /*
     * Bubble Sort Variations:
     * -----------------------
     * 1. Standard Bubble Sort
     * 2. Optimized Bubble Sort (stop early if no swaps)
     * 3. Recursive Bubble Sort
     *
     * Time Complexity:
     * - Worst case: O(n^2)
     * - Best case (optimized): O(n)
     * - Average case: O(n^2)
     *
     * Space Complexity: O(1) → in-place sorting
     */

    // Utility method to print array
    public static void printArray(int[] arr) {
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    /*
     * 1. Standard Bubble Sort
     * - Traverse array multiple times.
     * - Compare adjacent elements and swap if needed.
     * - Largest element "bubbles up" to the end in each pass.
     */
    public static void bubbleSortStandard(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /*
     * 2. Optimized Bubble Sort
     * - Same as standard, but uses a flag.
     * - If no swaps occur in a pass → array is already sorted → stop early.
     */
    public static void bubbleSortOptimized(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // stop if already sorted
        }
    }

    /*
     * 3. Recursive Bubble Sort
     * - Perform one pass to bubble up the largest element.
     * - Then recursively sort the remaining array (n-1 elements).
     */
    public static void bubbleSortRecursive(int[] arr, int n) {
        // Base case
        if (n == 1) return;

        // One pass of bubble sort
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        // Recursive call for remaining array
        bubbleSortRecursive(arr, n - 1);
    }

    public static void main(String[] args) {
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};
        int[] arr3 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("================================================");
        System.out.println("Original Array:");
        printArray(arr1);
        System.out.println("================================================");
        bubbleSortStandard(arr1);
        System.out.println("Sorted Array (Standard):");
        printArray(arr1);
        System.out.println("================================================");
        bubbleSortOptimized(arr2);
        System.out.println("Sorted Array (Optimized):");
        printArray(arr2);
        System.out.println("================================================");
        bubbleSortRecursive(arr3, arr3.length);
        System.out.println("Sorted Array (Recursive):");
        printArray(arr3);
        System.out.println("================================================");
    }
}
/*
 * Bubble Sort Variations:
 * - Standard → simple, always O(n^2).
 * - Optimized → stops early if sorted, best case O(n).
 * - Recursive → demonstrates recursion, same complexity but elegant.
 *
 * Key Point:
 * - Bubble Sort is easy to implement but inefficient for large datasets.
 * - Useful for teaching sorting basics and algorithm optimization.
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
- Memory Trick: Soap bubble rises → largest goes to end.

Selection Sort:
- Logic: Repeatedly select the minimum element from unsorted part and place it at start.
- Key Idea: Select smallest card each time.
- Time Complexity: Best O(n^2), Avg O(n^2), Worst O(n^2)
- Space Complexity: O(1)
- Stability: Not stable
- Memory Trick: Picking the smallest card from a deck.

Insertion Sort:
- Logic: Build sorted array one element at a time by inserting into correct position.
- Key Idea: Insert like arranging playing cards in hand.
- Time Complexity: Best O(n), Avg O(n^2), Worst O(n^2)
- Space Complexity: O(1)
- Stability: Stable
- Memory Trick: Insert each card into the right place in your hand.

Quick Summary:
- Bubble → swap neighbors, largest goes to end.
- Selection → pick min, put at front.
- Insertion → insert element into correct position in sorted part.

*/