package com.rajeshkawali.dsa.searching;

public class UnboundedSearch {

    /*
     * Problem:
     * --------
     * Search in an infinite (unbounded) sorted array.
     *
     * Logic:
     * 1. Start with range [0,1].
     * 2. Double right index until arr[right] >= target.
     * 3. Apply Binary Search in [left, right].
     *
     * Time Complexity:
     * - Finding range: O(log pos) where pos is target position.
     * - Binary Search: O(log pos).
     * - Overall: O(log pos).
     *
     * Space Complexity: O(1).
     */

    // Binary Search in given range
    public static int binarySearch(int[] arr, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // Unbounded Search
    public static int unboundedSearch(int[] arr, int target) {
        int left = 0, right = 1;

        // Expand range until arr[right] >= target
        while (right < arr.length && arr[right] < target) {
            left = right;
            right = right * 2; // double the range
            if (right >= arr.length) right = arr.length - 1; // avoid overflow
        }

        // Binary Search in found range
        return binarySearch(arr, left, right, target);
    }

    public static void main(String[] args) {
        int[] arr = {3, 5, 7, 9, 10, 13, 18, 21, 25, 30, 35, 40, 45, 50};
        int target = 25;

        System.out.println("Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("\n==============================");

        int result = unboundedSearch(arr, target);
        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }
}

/*
Unbounded Search:
- Array size unknown (infinite).
- Step 1: Expand range exponentially until arr[right] >= target.
- Step 2: Apply Binary Search in that range.
- Time Complexity: O(log pos)
- Space Complexity: O(1)
===================================================================================
Problem: Unbounded Search:
You are given a sorted array of infinite size (or size not known).
Task: Find the position of a target element.
Since the array length is unknown, you cannot directly apply binary search.
Trick: First find a search range where the target lies, then apply binary search.
*/