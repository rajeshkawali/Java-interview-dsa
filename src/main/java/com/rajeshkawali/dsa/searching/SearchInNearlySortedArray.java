package com.rajeshkawali.dsa.searching;

public class SearchInNearlySortedArray {

    /*
     * Problem:
     * --------
     * Given a nearly sorted array (each element may be misplaced
     * by at most one position), search for a target element.
     *
     * Example:
     * Input: arr = [10, 3, 40, 20, 50, 80, 70], target = 40
     * Output: 2
     *
     * Logic:
     * 1. Use modified Binary Search.
     * 2. At each step, check mid, mid-1, and mid+1.
     *    - If arr[mid] == target → return mid.
     *    - If arr[mid-1] == target → return mid-1.
     *    - If arr[mid+1] == target → return mid+1.
     * 3. If target < arr[mid] → search left half (skip two indices).
     * 4. Else → search right half (skip two indices).
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
	public static int searchNearlySorted(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			// Check mid, mid-1, mid+1
			if (arr[mid] == target) {
				return mid;
			}
			if (mid - 1 >= left && arr[mid - 1] == target) {
				return mid - 1;
			}
			if (mid + 1 <= right && arr[mid + 1] == target) {
				return mid + 1;
			}
			// Adjust search space
			if (target < arr[mid]) {
				right = mid - 2; // skip two indices
			} else {
				left = mid + 2; // skip two indices
			}
		}
		return -1;
	}

    public static void main(String[] args) {
        int[] arr = {10, 3, 40, 20, 50, 80, 70};
        int target = 40;

        System.out.println("Nearly Sorted Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("\n==============================");

        int result = searchNearlySorted(arr, target);
        if (result != -1) {
            System.out.println("Target " + target + " found at index: " + result);
        } else {
            System.out.println("Target " + target + " not found.");
        }
    }
}

/*

Search in Nearly Sorted Array:
- Each element may be misplaced by at most one position.
- Modified Binary Search:
  - Check mid, mid-1, mid+1.
  - Narrow search skipping two indices.
- Time Complexity: O(log n)
- Space Complexity: O(1)

*/