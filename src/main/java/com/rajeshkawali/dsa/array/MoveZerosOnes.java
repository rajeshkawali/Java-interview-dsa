package com.rajeshkawali.dsa.array;
/*
 * Problem: Move all 0s to the left and all 1s to the right in a binary array/string.
 *
 * Approaches:
 * 1. Counting Method:
 *    - Count number of zeros and ones.
 *    - Fill array: first zeros, then ones.
 *    - Simple and efficient.
 *
 * 2. Two-Pointer Method (In-place):
 *    - Use two pointers: left (start), right (end).
 *    - Swap when left points to 1 and right points to 0.
 *    - Continue until pointers meet.
 *
 * 3. Partition Method (like QuickSort partition):
 *    - Maintain index for next zero position.
 *    - Traverse array, whenever a zero is found, place it at correct position.
 *
 * Complexity:
 * Time: O(n) for all methods
 * Space: O(1) for Two-Pointer & Partition, O(n) for Counting (if new array used)
 */

public class MoveZerosOnes {

	// Method 1: Counting approach
	public static int[] moveByCounting(int[] arr) {
		int countZero = 0;
		for (int num : arr) {
			if (num == 0)
				countZero++;
		}
		// Fill zeros first, then ones
		for (int i = 0; i < arr.length; i++) {
			if (i < countZero)
				arr[i] = 0;
			else
				arr[i] = 1;
		}
		return arr;
	}

	// Method 2: Two-pointer approach (in-place)
	public static int[] moveByTwoPointer(int[] arr) {
		int left = 0;
		int right = arr.length - 1;
		while (left < right) {
			if (arr[left] == 1 && arr[right] == 0) {
				// Swap
				int temp = arr[left];
				arr[left] = arr[right];
				arr[right] = temp;
				left++;
				right--;
			} else {
				if (arr[left] == 0) {
					left++;
				}
				if (arr[right] == 1) {
					right--;
				}
			}
		}
		return arr;
	}

	// Method 3: Partition method
	public static int[] moveByPartition(int[] arr) {
		int index = 0; // position for next zero
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == 0) {
				// Swap zero to its correct position
				int temp = arr[i];
				arr[i] = arr[index];
				arr[index] = temp;
				index++;
			}
		}
		return arr;
	}

	// Utility method to print array
	public static void printArray(int[] arr) {
		System.out.print("[");
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			if (i < arr.length - 1)
				System.out.print(", ");
		}
		System.out.println("]");
	}

	public static void main(String[] args) {
		int[] input = { 1, 0, 1, 0, 1, 1, 0, 1 };
		System.out.println("==================================================================");
		System.out.println("Original Array:");
		printArray(input.clone());
		System.out.println("==================================================================");
		System.out.println("Using Counting Method:");
		printArray(moveByCounting(input.clone()));
		System.out.println("==================================================================");
		System.out.println("Using Two-Pointer Method:");
		printArray(moveByTwoPointer(input.clone()));
		System.out.println("==================================================================");
		System.out.println("Using Partition Method:");
		printArray(moveByPartition(input.clone()));
	}
}
