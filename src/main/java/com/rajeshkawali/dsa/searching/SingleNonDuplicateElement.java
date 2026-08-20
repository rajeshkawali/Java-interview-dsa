package com.rajeshkawali.dsa.searching;

public class SingleNonDuplicateElement {

    /*
     * Problem:
     * --------
     * Given a sorted array where every element appears twice
     * except one element which appears only once,
     * find that single non-duplicate element.
     *
     * Example:
     * Input: arr = [1,1,2,3,3,4,4,8,8]
     * Output: 2
     *
     * Logic (Binary Search):
     * 1. Use mid index.
     * 2. Check pairing pattern:
     *    - If mid is even:
     *        - arr[mid] == arr[mid+1] → single lies on right.
     *        - else → single lies on left (including mid).
     *    - If mid is odd:
     *        - arr[mid] == arr[mid-1] → single lies on right.
     *        - else → single lies on left.
     * 3. Continue until left == right → single element.
     *
     * Time Complexity: O(log n)
     * Space Complexity: O(1)
     */
	public static int singleNonDuplicate(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			// Ensure mid is even for comparison
			if (mid % 2 == 1) {
				mid--;
			}
			if (arr[mid] == arr[mid + 1]) {
				// Pair is valid → single lies on right
				left = mid + 2;
			} else {
				// Pair is broken → single lies on left (including mid)
				right = mid;
			}
		}
		return arr[left];
	}

    public static void main(String[] args) {
        int[] arr = {1,1,2,3,3,4,4,8,8};

        System.out.println("Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println("\n==============================");

        int result = singleNonDuplicate(arr);
        System.out.println("Single Non-Duplicate Element: " + result);
    }
}

/*

Single Non-Duplicate Element:
- Sorted array, all elements appear twice except one.
- Binary Search checks pairing pattern.
- Time Complexity: O(log n)
- Space Complexity: O(1)

*/