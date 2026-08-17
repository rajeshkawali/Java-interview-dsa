package com.rajeshkawali.dsa.array;
/*
 * Problem: Sort an array containing only 0s, 1s, and 2s.
 *
 * Approaches:
 * 1. Counting Method:
 *    - Count number of 0s, 1s, and 2s.
 *    - Overwrite array with that many 0s, then 1s, then 2s.
 *    - Simple but requires two passes.
 *
 * 2. Dutch National Flag Algorithm (Three Pointers):
 *    - Maintain three pointers: low, mid, high.
 *    - low: boundary for 0s
 *    - mid: current element
 *    - high: boundary for 2s
 *    - Traverse once, swap elements into correct regions.
 *    - Most efficient, single pass, in-place.
 *
 * 3. Sorting (not recommended):
 *    - Just use Arrays.sort().
 *    - Works but not optimal (O(n log n)).
 *
 * Complexity:
 * - Counting: Time O(n), Space O(1)
 * - Dutch Flag: Time O(n), Space O(1) → Best
 * - Sorting: Time O(n log n), Space O(1)
 */

import java.util.Arrays;

public class DutchFlagProblem {

	// Method 1: Counting approach
	public static void sortByCounting(int[] arr) {
		int count0 = 0, count1 = 0, count2 = 0;
		for (int num : arr) {
			if (num == 0) {
				count0++;
			} else if (num == 1) {
				count1++;
			} else {
				count2++;
			}
		}
		int index = 0;
		while (count0 > 0) {
			arr[index++] = 0;
			count0--;
		}
		while (count1 > 0) {
			arr[index++] = 1;
			count1--;
		}
		while (count2 > 0) {
			arr[index++] = 2;
			count2--;
		}
	}

	// Method 2: Dutch National Flag Algorithm
	public static void sortDutchFlag(int[] arr) {
		int low = 0, mid = 0, high = arr.length - 1;
		while (mid <= high) {
			switch (arr[mid]) {
			case 0:
				// Swap arr[low] and arr[mid]
				int temp0 = arr[low];
				arr[low] = arr[mid];
				arr[mid] = temp0;
				low++;
				mid++;
				break;
			case 1:
				mid++;
				break;
			case 2:
				// Swap arr[mid] and arr[high]
				int temp2 = arr[mid];
				arr[mid] = arr[high];
				arr[high] = temp2;
				high--;
				break;
			}
		}
	}

	// Method 3: Using built-in sort (not optimal)
	public static void sortByLibrary(int[] arr) {
		Arrays.sort(arr);
	}

	// Utility method to print array
	public static void printArray(int[] arr) {
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) {
		int[] arr = { 2, 0, 1, 2, 1, 0, 1, 2 };
		System.out.println("==================================================================");
		System.out.println("Original Array:");
		printArray(arr.clone());
		System.out.println("==================================================================");
		System.out.println("Using Counting Method:");
		int[] arr1 = arr.clone();
		sortByCounting(arr1);
		printArray(arr1);
		System.out.println("==================================================================");
		System.out.println("Using Dutch National Flag Algorithm:");
		int[] arr2 = arr.clone();
		sortDutchFlag(arr2);
		printArray(arr2);
		System.out.println("==================================================================");
		System.out.println("Using Library Sort:");
		int[] arr3 = arr.clone();
		sortByLibrary(arr3);
		printArray(arr3);
		System.out.println("==================================================================");
	}
}
