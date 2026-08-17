package com.rajeshkawali.dsa.array;

public class ShiftTheElementOfArray {

	// https://www.youtube.com/watch?v=qB781Qqi4Cg
	public static void main(String[] args) {
		int[] arr1 = { 10, 5, 25, 8, 15, 7, 19 };
		int[] shiftedArrayElem = shiftArrayElementByOne(arr1.clone());
		// Print array in a clean format
		System.out.print("Shifted By One: [");
		for (int i = 0; i < shiftedArrayElem.length; i++) { // iterate over each element of the shifted array
			System.out.print(shiftedArrayElem[i]);
			if (i < shiftedArrayElem.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		System.out.println("==========================================");
		int k = 2; // number of shifts (rotate right by 2 positions)
		int[] result1 = shiftArrayElementByK(arr1.clone(), k);
		System.out.print("Shifted by k postion: [");
		for (int i = 0; i < result1.length; i++) {
			System.out.print(result1[i]);
			if (i < result1.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		System.out.println("==========================================");
		int[] result2 = shiftArrayByKTemp(arr1.clone(), k);
		System.out.print("Shifted by Temp: [");
		for (int i = 0; i < result2.length; i++) { // iterate over each element of the shifted array
			System.out.print(result2[i]);
			if (i < result2.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		System.out.println("==========================================");
		int[] result3 = shiftArrayByKNaive(arr1.clone(), k);
		System.out.print("Shifted by Naive: [");
		for (int i = 0; i < result3.length; i++) { // iterate over each element of the shifted array
			System.out.print(result3[i]);
			if (i < result3.length - 1) {
				System.out.print(", ");
			}
		}
		System.out.println("]");
		System.out.println("==========================================");
	}

	// method to shift array elements to the right by one position
	private static int[] shiftArrayElementByOne(int[] arr1) {
		int n = arr1.length; // store array length in local variable
		int lastElement = arr1[n - 1]; // save last element because it will be overwritten during shifting
		for (int i = n - 1; i > 0; i--) { // loop from last index down to index 1
			arr1[i] = arr1[i - 1]; // move element from left neighbor into current position
		}
		arr1[0] = lastElement; // place previously saved last element into the first position
		return arr1; // return the modified array (shifted in-place)
	}

	public static int[] shiftArrayByKNaive(int[] arr, int k) {
		int n = arr.length;
		k = k % n; // handle k > n

		for (int shift = 0; shift < k; shift++) {
			int last = arr[n - 1]; // save last element
			for (int i = n - 1; i > 0; i--) {
				arr[i] = arr[i - 1]; // shift each element right
			}
			arr[0] = last; // put last element at front
		}
		return arr;
	}

	private static int[] shiftArrayElementByK(int[] arr1, int k) {
		int n = arr1.length; // length of array
		k = k % n; // handle cases where k > n
		// Step 1: Reverse entire array
		reverse(arr1, 0, n - 1);
		// Step 2: Reverse first k elements
		reverse(arr1, 0, k - 1);
		// Step 3: Reverse remaining n-k elements
		reverse(arr1, k, n - 1);
		return arr1; // return shifted array
	}

	// Helper method to reverse part of array
	private static void reverse(int[] arr, int start, int end) {
		while (start < end) {
			int temp = arr[start];
			arr[start] = arr[end];
			arr[end] = temp;
			start++;
			end--;
		}
	}

	public static int[] shiftArrayByKTemp(int[] arr, int k) {
		int n = arr.length;
		k = k % n;
		int[] temp = new int[k]; // store last k elements
		for (int i = 0; i < k; i++) {
			temp[i] = arr[n - k + i];
		}
		// shift remaining elements to the right
		for (int i = n - 1; i >= k; i--) {
			arr[i] = arr[i - k];
		}
		// put temp elements at the front
		for (int i = 0; i < k; i++) {
			arr[i] = temp[i];
		}
		return arr;
	}
}
/*
Shift The Element Of Array (Single Shift):
Logic:
Save the last element in a temporary variable.
Move each element one step to the right (arr[i] = arr[i-1]).
Place the saved last element at the first position.

Result: Array rotated right by 1 position.
Complexity: O(n) time, O(1) space.
=================================================================
Shift The Element Of Array of K (K Shifts):
Logic:
Normalize k (k = k % n) to handle cases where k > n.
Use reversal technique:
Reverse the entire array.
Reverse the first k elements.
Reverse the remaining n-k elements.
This rotates the array right by k positions.

Result: Efficient rotation for any k.
Complexity: O(n) time, O(1) space.
*/

//Single shift → save last, shift right, put last at front.
//K shifts → reverse method (whole array, then parts).

