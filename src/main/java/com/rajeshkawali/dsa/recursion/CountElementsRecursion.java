package com.rajeshkawali.dsa.recursion;

public class CountElementsRecursion {

    /*
     * Problem:
     * --------
     * Count the number of elements in an array using recursion.
     *
     * Approach:
     * - Base case: if index reaches end of array, return 0.
     * - Recursive case: count current element (1) + recurse for rest.
     *
     * Time Complexity: O(n) (each element visited once)
     * Space Complexity: O(n) (recursion stack depth)
     */

    public static int countElements(int[] arr, int index) {
        // Base case: end of array
        if (index >= arr.length) {
            return 0;
        }
        // Count current element + recurse for rest
        return 1 + countElements(arr, index + 1);
    }

    public static void main(String[] args) {
        int[] arr = {12, 45, 7, 89, 23, 56};
        System.out.println("Array elements: ");
        for (int num : arr) System.out.print(num + " ");
        System.out.println();
        System.out.println("============================================");
        int count = countElements(arr, 0);
        System.out.println("Total elements in array: " + count);
        System.out.println("============================================");
    }
}
