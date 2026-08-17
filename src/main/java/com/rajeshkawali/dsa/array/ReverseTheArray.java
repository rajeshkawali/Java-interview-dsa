package com.rajeshkawali.dsa.array;

/*
 * Logic:
 * 1. Method 1 (reverseArray):
 *    - Create a new array of the same size.
 *    - Copy elements from the end of the original array to the new one.
 *    - This preserves the original array but uses extra space.
 *
 * 2. Method 2 (reverseArrayByTwoPointer):
 *    - Use two pointers: one at the start, one at the end.
 *    - Swap elements until both pointers meet in the middle.
 *    - This reverses the array in-place without extra memory.
 *
 * Complexity:
 * Time: O(n) — single traversal of the array
 * Space: O(n) for Method 1 (new array), O(1) for Method 2 (in-place)
 */

public class ReverseTheArray {

	// Method 1: Reverse using a new array
	public static int[] reverseArray(int arr[]) {
		int len = arr.length;
		int[] reversed = new int[len];

		// Copy elements from the end of original array to the new one
		for (int i = 0; i < len; i++) {
			reversed[i] = arr[len - 1 - i]; // mapping front index to back index
		}
		return reversed;
	}

	// Method 2: Reverse using two-pointer technique (in-place)
	public static int[] reverseArrayByTwoPointer(int arr[]) {
		int n = arr.length;
		int i = 0; // pointer at the start
		int j = n - 1; // pointer at the end

		// Swap elements until pointers meet in the middle
		while (i < j) {
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;

			i++; // move start pointer forward
			j--; // move end pointer backward
		}
		return arr;
	}

	public static void main(String[] args) {

		int arry[] = { 2, 4, 6, 8, 0 };
		System.out.println("===============================================");
		// Using a new array
		int reverseArray[] = reverseArray(arry.clone());
		System.out.print("Reversed Array: [");
		for (int i = 0; i < reverseArray.length; i++) {
			System.out.print(reverseArray[i]);
			if (i < reverseArray.length - 1) {
				System.out.print(", "); // add comma between elements
			}
		}
		System.out.println("]");
		System.out.println("===============================================");
		// Using two-pointer method
		int reversedArray[] = reverseArrayByTwoPointer(arry.clone());
		// Print array in a clean format
		System.out.print("Reversed Array: [");
		for (int i = 0; i < reversedArray.length; i++) {
			System.out.print(reversedArray[i]);
			if (i < reversedArray.length - 1) {
				System.out.print(", "); // add comma between elements
			}
		}
		System.out.println("]");
		System.out.println("===============================================");
	}
}
