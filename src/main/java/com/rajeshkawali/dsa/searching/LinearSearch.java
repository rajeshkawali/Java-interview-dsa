package com.rajeshkawali.dsa.searching;

public class LinearSearch {

    /*
     * Linear Search Algorithm:
     * ------------------------
     * Problem:
     *   Search for an element in an array (sorted or unsorted).
     *
     * Logic:
     * 1. Traverse array from start to end.
     * 2. Compare each element with target.
     * 3. If found → return index.
     * 4. If not found → return -1.
     *
     * Time Complexity:
     * - Best case: O(1) (element at first position)
     * - Worst case: O(n) (element not present or at end)
     * - Average case: O(n)
     *
     * Space Complexity: O(1)
     */

    // Utility method to print result
    public static void printResult(int index) {
        if (index == -1) {
            System.out.println("Element not found.");
        } else {
            System.out.println("Element found at index: " + index);
        }
    }

    /*
     * 1. Standard Iterative Linear Search
     * - Traverse array using for loop.
     */
    public static int linearSearchIterative(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }

    /*
     * 2. Recursive Linear Search
     * - Base case: if index >= length → not found.
     * - If arr[index] == target → return index.
     * - Else → recurse with next index.
     */
    public static int linearSearchRecursive(int[] arr, int index, int target) {
        if (index >= arr.length) return -1;
        if (arr[index] == target) return index;
        return linearSearchRecursive(arr, index + 1, target);
    }

    /*
     * 3. Linear Search for Multiple Occurrences
     * - Print all indices where target occurs.
     */
    public static void linearSearchMultiple(int[] arr, int target) {
        boolean found = false;
        System.out.print("Occurrences at indices: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                System.out.print(i + " ");
                found = true;
            }
        }
        if (!found) System.out.print("None");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] arr = {11, 22, 25, 34, 25, 64, 90};

        System.out.println("Original Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("\n==============================");

        // Iterative
        int result1 = linearSearchIterative(arr, 25);
        System.out.println("Iterative Linear Search:");
        printResult(result1);

        // Recursive
        int result2 = linearSearchRecursive(arr, 0, 64);
        System.out.println("Recursive Linear Search:");
        printResult(result2);

        // Multiple Occurrences
        System.out.println("Linear Search Multiple Occurrences:");
        linearSearchMultiple(arr, 25);
    }
}

/*
Linear Search:
- Traverse array sequentially, compare each element with target.
- Works on both sorted and unsorted arrays.
- Variations:
  1. Iterative → simple loop.
  2. Recursive → elegant, but less efficient.
  3. Multiple Occurrences → find all positions.
- Time: O(n) worst/avg, O(1) best
- Space: O(1) iterative, O(n) recursive (stack)

*/