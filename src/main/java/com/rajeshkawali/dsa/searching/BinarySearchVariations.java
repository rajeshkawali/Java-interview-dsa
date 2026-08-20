package com.rajeshkawali.dsa.searching;

public class BinarySearchVariations {

    /*
     * Binary Search Variations:
     * -------------------------
     * 1. Standard Binary Search (Iterative & Recursive)
     * 2. First Occurrence
     * 3. Last Occurrence
     * 4. Lower Bound (first index where arr[i] >= target)
     * 5. Upper Bound (first index where arr[i] > target)
     * 6. Number of Occurrences (upperBound - lowerBound)
     *
     * Time Complexity: O(log n)
     * Space Complexity:
     * - Iterative: O(1)
     * - Recursive: O(log n) (stack space)
     */

    // 1a. Standard Iterative Binary Search
    public static int binarySearchIterative(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    // 1b. Standard Recursive Binary Search
    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {
        if (left > right) return -1;
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] > target) return binarySearchRecursive(arr, left, mid - 1, target);
        return binarySearchRecursive(arr, mid + 1, right, target);
    }

    // 2. First Occurrence
    public static int firstOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result = mid;
                right = mid - 1; // keep searching left
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // 3. Last Occurrence
    public static int lastOccurrence(int[] arr, int target) {
        int left = 0, right = arr.length - 1, result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                result = mid;
                left = mid + 1; // keep searching right
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    // 4. Lower Bound: first index where arr[i] >= target
	public static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

    // 5. Upper Bound: first index where arr[i] > target
	public static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (arr[mid] <= target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		return left;
	}

    // 6. Number of Occurrences
    public static int countOccurrences(int[] arr, int target) {
        return upperBound(arr, target) - lowerBound(arr, target);
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 2, 2, 3, 4, 5, 5, 5, 6};
        System.out.println("================================================");
        System.out.println("Array:");
        for (int val : arr) System.out.print(val + " ");
        System.out.println();
        System.out.println("================================================");
        System.out.println("Iterative Binary Search (target=4): " + binarySearchIterative(arr, 4));
        System.out.println("================================================");
        System.out.println("Recursive Binary Search (target=5): " + binarySearchRecursive(arr, 0, arr.length - 1, 5));
        System.out.println("================================================");
        System.out.println("First Occurrence of 2: " + firstOccurrence(arr, 2));
        System.out.println("================================================");
        System.out.println("Last Occurrence of 2: " + lastOccurrence(arr, 2));
        System.out.println("================================================");
        System.out.println("Lower Bound of 5: " + lowerBound(arr, 5));
        System.out.println("================================================");
        System.out.println("Upper Bound of 5: " + upperBound(arr, 5));
        System.out.println("================================================");
        System.out.println("Number of Occurrences of 5: " + countOccurrences(arr, 5));
        System.out.println("================================================");
    }
}

/*
Binary Search Variations:
--------------------------------------
Standard Binary Search:
- Finds target element in sorted array.
- Iterative: O(log n) time, O(1) space.
- Recursive: O(log n) time, O(log n) space (stack).

First Occurrence:
- Finds the leftmost index of target.
- Keep searching left side when match found.
- Time: O(log n), Space: O(1).

Last Occurrence:
- Finds the rightmost index of target.
- Keep searching right side when match found.
- Time: O(log n), Space: O(1).

Lower Bound:
- First index where arr[i] >= target.
- Useful for insertion position.
- Time: O(log n), Space: O(1).

Upper Bound:
- First index where arr[i] > target.
- Useful for range queries.
- Time: O(log n), Space: O(1).

Number of Occurrences:
- Formula: upperBound - lowerBound.
- Gives count of target in sorted array.
- Time: O(log n), Space: O(1).

Quick Summary:
- Standard (Iterative/Recursive): O(log n), space O(1)/O(log n).
- First Occurrence: find leftmost index of target.
- Last Occurrence: find rightmost index of target.
- Lower Bound: first index where arr[i] >= target.
- Upper Bound: first index where arr[i] > target.
- Number of Occurrences: upperBound - lowerBound.
- All variations use O(log n) time, O(1) space (except recursion).

*/