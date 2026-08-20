package com.rajeshkawali.dsa.searching;

public class BinarySearch {

    /*
     * Binary Search Algorithm:
     * ------------------------
     * Problem:
     *   Search for an element in a sorted array.
     *
     * Logic:
     * 1. Compare target with middle element.
     * 2. If equal → found.
     * 3. If target < mid → search left half.
     * 4. If target > mid → search right half.
     * 5. Repeat until found or range is empty.
     *
     * Time Complexity:
     * - Best case: O(1) (element at mid)
     * - Worst case: O(log n)
     * - Average case: O(log n)
     *
     * Space Complexity:
     * - Iterative: O(1)
     * - Recursive: O(log n) (stack space)
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
     * 1. Iterative Binary Search
     * - Uses while loop.
     * - Efficient, O(1) space.
     */
	public static int binarySearchIterative(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2; // avoid overflow
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}

    /*
     * 2. Recursive Binary Search
     * - Uses recursion.
     * - Simpler to understand, but uses stack space.
     */
    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {
        if (left > right) return -1;

        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;

        if (arr[mid] > target) {
            return binarySearchRecursive(arr, left, mid - 1, target);
        } else {
            return binarySearchRecursive(arr, mid + 1, right, target);
        }
    }

    /*
     * 3. Binary Search using Java's built-in Arrays.binarySearch()
     * - Library method.
     * - Returns index if found, else (-(insertion point) - 1).
     */
    public static void binarySearchLibrary(int[] arr, int target) {
        int index = java.util.Arrays.binarySearch(arr, target);
        if (index >= 0) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found. Insertion point: " + (-index - 1));
        }
    }
    
    /*
     * 4. Binary Search
     * - Uses while loop.
     * - Time: O(log n).
     */
	public static int binarySearch(int[] arr, int target) {
		int n = arr.length;
		int start = 0;
		int end = n - 1;
		int mid = start + (end - start) / 2;
		while (start <= end) {
			if (arr[mid] == target) {
				return mid;
			} else if (arr[mid] < target) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
			mid = start + (end - start) / 2;
		}
		return -1;
	}

    public static void main(String[] args) {
        int[] arr = {11, 22, 25, 34, 64, 90};
        System.out.println("================================================");
        System.out.println("Original Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
        System.out.println("================================================");
        // Iterative
        int result1 = binarySearchIterative(arr, 25);
        System.out.println("Iterative Binary Search:");
        printResult(result1);
        System.out.println("================================================");
        // Recursive
        int result2 = binarySearchRecursive(arr, 0, arr.length - 1, 64);
        System.out.println("Recursive Binary Search:");
        printResult(result2);
        System.out.println("================================================");
        // Library
        System.out.println("Library Binary Search:");
        binarySearchLibrary(arr, 22);
        System.out.println("================================================");
        System.out.println("Binary Search:");
        int result3 = binarySearch(arr, 64);
        printResult(result3);
        System.out.println("================================================");
    }
}
/*
 * Binary Search:
 * - Works only on sorted arrays.
 * - Divide and conquer approach.
 *
 * Variations:
 * 1. Iterative → efficient, O(1) space.
 * 2. Recursive → elegant, O(log n) stack space.
 * 3. Library → quick, but returns insertion point if not found.
 *
 * Time: O(log n)
 * Space: O(1) iterative, O(log n) recursive
 */
