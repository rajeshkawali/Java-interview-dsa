package com.rajeshkawali.dsa.searching;

public class SearchInRotatedSortedArray {

    /*
     * Problem:
     * --------
     * Given a rotated sorted array and a target,
     * return the index of the target if found, else -1.
     *
     * Example:
     * Input: arr = [4,5,6,7,0,1,2], target = 0
     * Output: 4
     *
     * Logic:
     * 1. Use modified Binary Search.
     * 2. At each step, check which half is sorted.
     * 3. If left half is sorted:
     *    - Check if target lies in left half.
     *    - Else search right half.
     * 4. If right half is sorted:
     *    - Check if target lies in right half.
     *    - Else search left half.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */

    public static int search(int[] arr, int target) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
            		return mid;
            }
            // Check if left half is sorted
            if (arr[left] <= arr[mid]) {
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1; // target in left half
                } else {
                    left = mid + 1;  // target in right half
                }
            }
            // Else right half is sorted
            else {
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;  // target in right half
                } else {
                    right = mid - 1; // target in left half
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {4, 5, 6, 7, 0, 1, 2};
        int target = 0;

        System.out.println("Rotated Sorted Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("\n==============================");

        int result = search(arr, target);
        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }
}

/*

Search in Rotated Sorted Array:
- Modified Binary Search.
- At each step, check which half is sorted.
- Narrow search to the half where target lies.
- Time Complexity: O(log n)
- Space Complexity: O(1)

*/